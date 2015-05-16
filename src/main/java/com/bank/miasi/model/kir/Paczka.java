package com.bank.miasi.model.kir;

import com.bank.miasi.controlers.kir.Bank;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.Kontable;

public class Paczka {

    private final Bank bankOdbiorcy;
    private final Bank bankNadawcy;
    private final Przesylka przesylka;

    public Paczka(Bank bankNadawcy, Bank bankOdbiorcy, Przesylka przesylka) {
        this.bankOdbiorcy = bankOdbiorcy;
        this.bankNadawcy = bankNadawcy;
        this.przesylka = przesylka;
    }

    public Paczka(Bank bankNadawcy, Bank bankOdbiorcy, OperacjaBankowa operacjaBankowa, Kontable nadawca, Kontable odbiorca) {
        this.bankOdbiorcy = bankOdbiorcy;
        this.bankNadawcy = bankNadawcy;
        przesylka = new Przesylka(operacjaBankowa, nadawca, odbiorca);
    }

    public Bank getBankOdbiorcy() {
        return bankOdbiorcy;
    }

    public Bank getBankNadawcy() {
        return bankNadawcy;
    }

    public Przesylka getPrzesylka() {
        return przesylka;
    }

}
