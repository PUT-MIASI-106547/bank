package com.bank.miasi.kir;

import com.bank.miasi.test.SymulatorZewnetrznegoKIR;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {

    private static Bank instance;

    private List<Paczka> listaPaczekDoWyslania;
    private int idBanku;
    private final KIR kir;
    private final String haslo;


    public Bank(KIR kir, int idBanku, String haslo) {
        this.kir = kir;
        this.idBanku = idBanku;
        this.haslo = haslo;
    }

    public void pobierzPaczki() {
        UUID sesja = kir.zaloguj(idBanku, haslo);
        for (Paczka paczka : kir.sciagnijPaczkiDoBanku(sesja)) {
            przetworzPaczke(paczka.getPrzesylka());
        }
    }

    public void wyslijPaczki() {
        UUID sesja = kir.zaloguj(idBanku, haslo);
        kir.odbierzPaczkiZBanku(listaPaczekDoWyslania, sesja);
        listaPaczekDoWyslania = null;
    }

    public void przetworzPaczke(Przesylka przesylka) {
        // TODO wywolanie powoduje zainicjalizowanie metody obiektu klasy Operacja
        // wykorzystywac bedzie this.listaPaczekOdebranych
    }

    public void dodajPaczkeDoWyslania(Bank bankDocelowy, Przesylka przesylka) {
        if (null == listaPaczekDoWyslania) {
            listaPaczekDoWyslania = new ArrayList<>();
        }
        this.listaPaczekDoWyslania.add(new Paczka(this, bankDocelowy, przesylka));
    }

    public int getBankId() {
        return idBanku;
    }
}
