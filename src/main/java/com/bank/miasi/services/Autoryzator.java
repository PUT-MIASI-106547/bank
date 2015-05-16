package com.bank.miasi.services;

import com.bank.miasi.model.Klient;

public interface Autoryzator {
    boolean verify(Klient klient, String haslo);

    String generate(Klient klient, String haslo);
}
