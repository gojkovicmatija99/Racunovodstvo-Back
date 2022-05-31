package raf.si.racunovodstvo.nabavka.converters.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import raf.si.racunovodstvo.nabavka.converters.IConverter;
import raf.si.racunovodstvo.nabavka.model.IstorijaProdajneCene;
import raf.si.racunovodstvo.nabavka.model.Kalkulacija;
import raf.si.racunovodstvo.nabavka.model.Konverzija;
import raf.si.racunovodstvo.nabavka.requests.ArtikalRequest;
import raf.si.racunovodstvo.nabavka.services.IArtikalService;
import raf.si.racunovodstvo.nabavka.services.IKalkulacijaService;
import raf.si.racunovodstvo.nabavka.services.IKonverzijaService;
import raf.si.racunovodstvo.nabavka.model.Artikal;
import raf.si.racunovodstvo.nabavka.model.KalkulacijaArtikal;
import raf.si.racunovodstvo.nabavka.model.KonverzijaArtikal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class ArtikalConverter implements IConverter<ArtikalRequest, Artikal> {

    private final ModelMapper modelMapper;
    private final IKonverzijaService iKonverzijaService;
    private final IKalkulacijaService iKalkulacijaService;
    private final IArtikalService iArtikalService;

    public ArtikalConverter(ModelMapper modelMapper,
                            IKonverzijaService iKonverzijaService,
                            IKalkulacijaService iKalkulacijaService,
                            @Lazy IArtikalService iArtikalService) {
        this.modelMapper = modelMapper;
        this.iKonverzijaService = iKonverzijaService;
        this.iKalkulacijaService = iKalkulacijaService;
        this.iArtikalService = iArtikalService;
    }

    @Override
    public Artikal convert(ArtikalRequest source) {
        if (source.isAktivanZaProdaju()) {
            KalkulacijaArtikal kalkulacijaArtikal = modelMapper.map(source, KalkulacijaArtikal.class);
            calculateCommonFields(kalkulacijaArtikal);
            calculateKalkulacijaFields(kalkulacijaArtikal);

            Kalkulacija kalkulacija = iKalkulacijaService.increaseNabavnaAndProdajnaCena(source.getKonverzijaKalkulacijaId(),
                                                                                         kalkulacijaArtikal.getUkupnaNabavnaVrednost(),
                                                                                         kalkulacijaArtikal.getUkupnaProdajnaVrednost());

            kalkulacijaArtikal.setBaznaKonverzijaKalkulacija(kalkulacija);
            return kalkulacijaArtikal;
        } else {
            KonverzijaArtikal konverzijaArtikal = modelMapper.map(source, KonverzijaArtikal.class);
            calculateCommonFields(konverzijaArtikal);

            Konverzija konverzija = iKonverzijaService.increaseNabavnaCena(source.getKonverzijaKalkulacijaId(), konverzijaArtikal.getUkupnaNabavnaVrednost());
            konverzijaArtikal.setBaznaKonverzijaKalkulacija(konverzija);

            return konverzijaArtikal;
        }
    }

    private void calculateKalkulacijaFields(KalkulacijaArtikal artikal) {
        artikal.setMarza(artikal.getNabavnaCenaPosleRabata() * artikal.getMarzaProcenat() / 100);
        artikal.setProdajnaOsnovica(artikal.getNabavnaCenaPosleRabata() + artikal.getMarza());
        artikal.setPorez(artikal.getProdajnaOsnovica() * artikal.getPorezProcenat() / 100);
        artikal.setOsnovica(artikal.getProdajnaOsnovica() * artikal.getKolicina());
        artikal.setUkupnaProdajnaVrednost(artikal.getProdajnaCena() * artikal.getKolicina());

        handleIstorijaProdaje(artikal);
    }

    private void calculateCommonFields(Artikal artikal) {
        artikal.setRabat(artikal.getNabavnaCena() * (artikal.getRabatProcenat()/100));
        artikal.setNabavnaCenaPosleRabata(artikal.getNabavnaCena() - artikal.getRabat());
        artikal.setUkupnaNabavnaVrednost(artikal.getNabavnaCenaPosleRabata() * artikal.getKolicina());
    }

    private void handleIstorijaProdaje(KalkulacijaArtikal artikal) {
        if (!isKalkulacijaArtikalSaved(artikal)) {
            return;
        }

        KalkulacijaArtikal savedKalkulacijaArtikal = (KalkulacijaArtikal)iArtikalService.findById(artikal.getArtikalId()).get();
        List<IstorijaProdajneCene> istorijaProdajneCene = savedKalkulacijaArtikal.getIstorijaProdajneCene();
        if (isProdajnaCenaUpdated(artikal)) {
            istorijaProdajneCene.add(new IstorijaProdajneCene(new Date(), savedKalkulacijaArtikal.getProdajnaCena()));
            artikal.setIstorijaProdajneCene(new ArrayList<>(istorijaProdajneCene));
        }
        artikal.setIstorijaProdajneCene(new ArrayList<>(istorijaProdajneCene));
    }

    private boolean isProdajnaCenaUpdated(KalkulacijaArtikal artikal) {
        Artikal savedArtikal = iArtikalService.findById(artikal.getArtikalId()).get();
        KalkulacijaArtikal savedKalkulacijaArtikal = (KalkulacijaArtikal) savedArtikal;
        return !Objects.equals(savedKalkulacijaArtikal.getProdajnaCena(), artikal.getProdajnaCena());
    }

    private boolean isKalkulacijaArtikalSaved(KalkulacijaArtikal artikal) {
        if (artikal.getArtikalId() == null || !iArtikalService.findById(artikal.getArtikalId()).isPresent()) {
            return false;
        }

        Artikal savedArtikal = iArtikalService.findById(artikal.getArtikalId()).get();
        return savedArtikal instanceof KalkulacijaArtikal;
    }
}
