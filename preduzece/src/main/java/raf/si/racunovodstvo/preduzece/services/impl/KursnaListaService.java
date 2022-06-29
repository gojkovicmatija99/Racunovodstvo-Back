package raf.si.racunovodstvo.preduzece.services.impl;

import org.springframework.stereotype.Service;
import raf.si.racunovodstvo.preduzece.feign.KursnaListaFeignClient;
import raf.si.racunovodstvo.preduzece.responses.KursnaListaResponse;
import raf.si.racunovodstvo.preduzece.services.IKursnaListaService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class KursnaListaService implements IKursnaListaService {

    private final KursnaListaFeignClient kursnaListaFeignClient;

    public KursnaListaService(KursnaListaFeignClient kursnaListaFeignClient) {
        this.kursnaListaFeignClient = kursnaListaFeignClient;
    }

    @Override
    public KursnaListaResponse getKursnaLista() {
        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String todayFormatted = dateFormat.format(today);
        return kursnaListaFeignClient.getKursnaListaForDate(todayFormatted);
    }

    @Override
    public KursnaListaResponse getKursnaListaForDay(String date) {
        return kursnaListaFeignClient.getKursnaListaForDate(date);
    }
}
