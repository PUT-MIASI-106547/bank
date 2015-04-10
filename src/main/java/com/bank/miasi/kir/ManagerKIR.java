package com.bank.miasi.kir;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ManagerKIR {

    private List<Paczka> listaPaczekDoWyslania;
    private int idBanku;
    private final KIR kir;
    private final String haslo;

    public ManagerKIR(KIR kir, int idBanku, String haslo) {
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

    public void dodajPaczkeDoWyslania(int idBankuDocelowego, Przesylka przesylka) {
        if (null ==listaPaczekDoWyslania){
            listaPaczekDoWyslania = new ArrayList<>();
        }
        this.listaPaczekDoWyslania.add(new Paczka(idBanku, idBankuDocelowego, przesylka));
    }

    public int getBankId() {
        return idBanku;
    }
}
