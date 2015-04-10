package com.bank.miasi;

import java.math.BigDecimal;
import java.util.UUID;

public class Klient {

    private final String imie, nazwisko;
    private String adres, NIP;
    private final String pesel;
    private UUID haslo;
    private String nrTelefonu;
    private BigDecimal zarobkiMiesieczne;

    /**
     * Utworzenie klienta
     *
     * @param imie
     * @param nazwisko
     * @param adres
     * @param pesel
     * @param NIP
     * @param nrTelefonu
     * @param zarobkiMiesieczne
     */
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

    public void setNIP(String nIP) {
        NIP = nIP;
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

    protected void setHaslo(UUID haslo) {
        this.haslo = haslo;
    }

    /**
     * Sprawdzanie hasla uzytkownika
     *
     * @return boolean - true jeżeli haso jest prawidlowe, false jezeli nie jest
     */
    public boolean checkHaslo(UUID haslo1) {
        return (this.haslo.equals(haslo));
    }

    /**
     * Sprawdzanie zdolnosci kredytowej kienta
     *
     * @return boolean - true jeżeli klient posiada zdolnosc kredytowa, false
     * jezeli jej nie posiada
     */
    public boolean sprawdzZdolnoscKredytowa() {
        BigDecimal a = new BigDecimal("1000.0");
        int decyzja = this.zarobkiMiesieczne.compareTo(a);

        return decyzja > 0;

    }
}
