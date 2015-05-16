package com.bank.miasi.services;

import com.bank.miasi.model.konta.Kontable;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.reports.Raport;
import com.google.inject.Inject;

import java.util.Date;

public class KontoService {

    @Inject
    OperacjaBankowaService operacjaBankowaService;

    public void printRaport(Kontable kontable, Raport raport, Date odKiedy, Date dokiedy) {
        for (OperacjaBankowa operacjaBankowa : kontable.getHistoria(odKiedy, dokiedy)) {
            operacjaBankowaService.addRowToRaport(operacjaBankowa, raport);
        }
        raport.addHeader(kontable);
        raport.print();
    }
}
