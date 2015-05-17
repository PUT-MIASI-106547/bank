package com.bank.miasi.model.konta;

import com.bank.miasi.controlers.kir.Bank;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.model.Klient;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.typy.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface Kontable {

    BigDecimal getStan();

    AccountType getTyp();

    List<OperacjaBankowa> getHistoria(Date odKiedy, Date doKiedy);

    String getNumer();

    Klient getWlasciciel();

    void wplata(OperacjaBankowa operacjaBankowa) throws NiewspieranaOperacja;

    void addHistoriaOperacji(OperacjaBankowa operacjaBankowa);

    Bank getBank();

    void setInitialAmount(BigDecimal initialAmount);

}
