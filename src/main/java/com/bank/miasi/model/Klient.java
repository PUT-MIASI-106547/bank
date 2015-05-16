package com.bank.miasi.model;

import com.google.inject.Inject;

import java.math.BigDecimal;

public class Klient {

    private final String imie, nazwisko;
    private String adres, NIP;
    private final String pesel;
    private String haslo;
    private String nrTelefonu;
    private BigDecimal zarobkiMiesieczne;

    /**
     * Utworzenie klienta
     */
    @Inject
    public Klient(String imie, String nazwisko, String adres, String pesel, String NIP, String nrTelefonu, BigDecimal zarobkiMiesieczne) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.NIP = NIP;
        this.pesel = pesel;
        this.nrTelefonu = nrTelefonu;
        this.zarobkiMiesieczne = zarobkiMiesieczne;
    }


    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPesel() {
        return pesel;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String nip) {
        NIP = nip;
    }

    public String getNrTelefonu() {
        return nrTelefonu;
    }

    public void setNrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    public BigDecimal getZarobkiMiesieczne() {
        return zarobkiMiesieczne;
    }

    public void setZarobkiMiesieczne(BigDecimal zarobkiMiesieczne) {
        this.zarobkiMiesieczne = zarobkiMiesieczne;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }



    @Override
    public String toString() {
        return imie + " " + nazwisko;
    }

    public void printKlientInfo(StringBuilder sb) {
        sb.append(getImie())
                .append(" ")
                .append(getNazwisko())
                .append("\n")
                .append(getAdres())
                .append("\n")
                .append(getPesel())
                .append("\n")
                .append(getNrTelefonu())
                .append("\n")
                .append(getNIP());
    }

    public String getHaslo() {
        return haslo;
    }


}
