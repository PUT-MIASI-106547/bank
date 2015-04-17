package com.bank.miasi.kir;

import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.konta.Kontable;
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
