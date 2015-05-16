package com.bank.miasi.services;

import com.bank.miasi.model.Klient;
import com.google.inject.Inject;

import java.math.BigDecimal;

public class KlientService {
    @Inject
    private Autoryzator autoryzator;

    public Klient createKlient(String imie, String nazwisko, String adres, String pesel, String NIP, String nrTelefonu, BigDecimal zarobkiMiesieczne, String haslo) {
        Klient klient = new Klient(imie, nazwisko, adres, pesel, NIP, nrTelefonu, zarobkiMiesieczne);
        klient.setHaslo(autoryzator.generate(klient, haslo));

        return klient;
    }

    /**
     * Sprawdzanie hasla uzytkownika
     *
     * @return boolean - true jeżeli haso jest prawidlowe, false jezeli nie jest
     */
    public boolean checkPassword(Klient klient, String haslo) {

        return autoryzator.verify(klient, haslo);
    }

    /**
     * Sprawdzanie zdolnosci kredytowej kienta
     *
     * @return boolean - true jeżeli klient posiada zdolnosc kredytowa, false
     * jezeli jej nie posiada
     */
    public boolean sprawdzZdolnoscKredytowa(Klient klient) {
        BigDecimal a = new BigDecimal("1000.0");
        int decyzja = klient.getZarobkiMiesieczne().compareTo(a);

        return decyzja > 0;

    }

}
