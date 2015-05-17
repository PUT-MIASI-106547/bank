package com.bank.miasi.services;

import com.bank.miasi.controlers.kir.Bank;
import com.bank.miasi.model.Klient;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.Kontable;
import com.bank.miasi.model.konta.KontoBankowe;
import com.bank.miasi.model.konta.KontoBankoweZDebetem;
import com.bank.miasi.model.konta.typy.AccountType;
import com.bank.miasi.reports.Raport;
import com.bank.miasi.services.api.KontoService;
import com.bank.miasi.services.api.OperacjaBankowaService;
import com.google.inject.Inject;

import java.math.BigDecimal;
import java.util.Date;

public class KontoServiceImpl implements KontoService {

    @Inject
    OperacjaBankowaService operacjaBankowaService;

    @Override
    public void printRaport(Kontable kontable, Raport raport, Date odKiedy, Date dokiedy) {
        for (OperacjaBankowa operacjaBankowa : kontable.getHistoria(odKiedy, dokiedy)) {
            operacjaBankowaService.addRowToRaport(operacjaBankowa, raport);
        }
        raport.addHeader(kontable);
        raport.print();
    }

    @Override
    public Kontable createKontoBankowe(Bank bank, AccountType typ, String numer, Klient wlasciciel) {
        return new KontoBankowe(bank, typ, numer, wlasciciel);
    }

    @Override
    public Kontable createKontoBankoweZDebetem(Kontable konto, BigDecimal debet) {
        return new KontoBankoweZDebetem(konto, debet);
    }
}
