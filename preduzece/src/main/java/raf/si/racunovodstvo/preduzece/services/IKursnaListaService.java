package raf.si.racunovodstvo.preduzece.services;

import raf.si.racunovodstvo.preduzece.responses.KursnaListaResponse;

public interface IKursnaListaService {

    KursnaListaResponse getKursnaLista();
    KursnaListaResponse getKursnaListaForDay(String date);
}
