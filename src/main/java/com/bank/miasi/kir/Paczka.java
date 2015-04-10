package com.bank.miasi.kir;

import java.math.BigDecimal;

public class Paczka {

    private final int idBankuOdbiorcy;
    private final int idBankuNadawcy;
    private final Przesylka przesylka;

    public Paczka(int idBankuNadawcy, int idBankuOdbiorcy, Przesylka przesylka) {
        this.idBankuOdbiorcy = idBankuOdbiorcy;
        this.idBankuNadawcy = idBankuNadawcy;
        this.przesylka = przesylka;
    }

    public Paczka(int idBankuNadawcy, int idBankuOdbiorcy, BigDecimal kwota, String nazwa,
            String tytul, String numerKontaOdbiorcy, String numerKontaNadawcy) {
        this.idBankuOdbiorcy = idBankuOdbiorcy;
        this.idBankuNadawcy = idBankuNadawcy;
        przesylka = new Przesylka(kwota, tytul, numerKontaOdbiorcy, numerKontaNadawcy);
    }

    public int getIdBankuOdbiorcy() {
        return idBankuOdbiorcy;
    }

    public int getIdBankuNadawcy() {
        return idBankuNadawcy;
    }

    public Przesylka getPrzesylka() {
        return przesylka;
    }

}
