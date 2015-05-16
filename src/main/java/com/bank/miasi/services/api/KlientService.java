package com.bank.miasi.services.api;

import com.bank.miasi.model.Klient;

import java.math.BigDecimal;

public interface KlientService {
    Klient createKlient(String imie, String nazwisko, String adres, String pesel, String NIP, String nrTelefonu, BigDecimal zarobkiMiesieczne, String haslo);

    boolean checkPassword(Klient klient, String haslo);

    boolean sprawdzZdolnoscKredytowa(Klient klient);
}
