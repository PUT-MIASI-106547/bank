package com.bank.miasi.kir;

import java.math.BigDecimal;

public class Paczka {

    private final Bank bankOdbiorcy;
    private final Bank bankNadawcy;
    private final Przesylka przesylka;

    public Paczka(Bank bankNadawcy, Bank bankOdbiorcy, Przesylka przesylka) {
        this.bankOdbiorcy = bankOdbiorcy;
        this.bankNadawcy = bankNadawcy;
        this.przesylka = przesylka;
    }

    public Paczka(Bank bankNadawcy, Bank bankOdbiorcy, BigDecimal kwota, String nazwa,
            String tytul, String numerKontaOdbiorcy, String numerKontaNadawcy) {
        this.bankOdbiorcy = bankOdbiorcy;
        this.bankNadawcy = bankNadawcy;
        przesylka = new Przesylka(kwota, tytul, numerKontaOdbiorcy, numerKontaNadawcy);
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
