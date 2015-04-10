package com.bank.miasi.kir;

import com.bank.miasi.OperacjaBankowa;
import java.math.BigDecimal;

public class Przesylka {

    public static Przesylka getPrzesylkaFromOperacje(OperacjaBankowa operacjaBankowa) {
            String numerOd = "nadawca nieznany";
        if (operacjaBankowa.getOdKogo() != null) {
            numerOd = operacjaBankowa.getOdKogo().getNumer();
        }
        String numerDo = operacjaBankowa.getDoKogo().getNumer();
        return new Przesylka(operacjaBankowa.getKwota(), operacjaBankowa.getTytul(), numerOd, numerDo);
    }

    private final BigDecimal kwota;
    private final String tytul;
    private final String numerKontaOdbiorcy;
    private final String numerKontaNadawcy;

    public Przesylka(BigDecimal kwota, String tytul, String numerKontaOdbiorcy, String numerKontaNadawcy) {
        this.kwota = kwota;
        this.tytul = tytul;
        this.numerKontaOdbiorcy = numerKontaOdbiorcy;
        this.numerKontaNadawcy = numerKontaNadawcy;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public String getTytul() {
        return tytul;
    }

    public String getNumerKontaOdbiorcy() {
        return numerKontaOdbiorcy;
    }

    public String getNumerKontaNadawcy() {
        return numerKontaNadawcy;
    }

}
