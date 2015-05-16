package com.bank.miasi.services;

import com.bank.miasi.model.Klient;

public class SimpleAutoryzator implements Autoryzator {

    public boolean verify(Klient klient, String haslo) {
        return (haslo + klient.getPesel()).equals(klient.getHaslo());
    }

    public String generate(Klient klient, String haslo) {
        return haslo + klient.getPesel();
    }

}
