package com.bank.miasi.services.api;

import com.bank.miasi.controlers.kir.Bank;
import com.bank.miasi.model.Klient;
import com.bank.miasi.model.konta.Kontable;
import com.bank.miasi.model.konta.typy.AccountType;
import com.bank.miasi.reports.Raport;

import java.math.BigDecimal;
import java.util.Date;

public interface KontoService {
    void printRaport(Kontable kontable, Raport raport, Date odKiedy, Date dokiedy);

    Kontable createKontoBankowe(Bank bank, AccountType typ, String numer, Klient wlasciciel);

    Kontable createKontoBankoweZDebetem(Kontable konto, BigDecimal debet);
}
