package com.bank.miasi.kir;

import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {

    private List<Paczka> listaPaczekDoWyslania = new ArrayList<>();
    private int idBanku;
    private String haslo;

    private KIR kirInstance;

    public Bank(int idBanku, String haslo, KIR kir) {
        this.idBanku = idBanku;
        this.haslo = haslo;
        kirInstance = kir;
    }

    public void pobierzPaczki() {
        UUID sesja = kirInstance.zaloguj(idBanku, haslo);
        for (Paczka paczka : kirInstance.sciagnijPaczkiDoBanku(sesja)) {
            przetworzPaczke(paczka.getPrzesylka());
        }
    }

    public void wyslijPaczki() {
        UUID sesja = kirInstance.zaloguj(idBanku, haslo);
        kirInstance.odbierzPaczkiZBanku(getListaPaczek(), sesja);
        listaPaczekDoWyslania = null;
    }

    public void przetworzPaczke(Przesylka przesylka) {
        OperacjaBankowa operacja = przesylka.rozpakuj();
        try {
            przesylka.getOdbiorca().wplata(operacja);
        } catch (NiewspieranaOperacja ex) {
            dodajPaczkeDoWyslania(przesylka.getNadawca().getBank(), new Przesylka(operacja, przesylka.getOdbiorca(), przesylka.getNadawca()));
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dodajPaczkeDoWyslania(Bank bankDocelowy, Przesylka przesylka) {
        this.getListaPaczek().add(new Paczka(this, bankDocelowy, przesylka));
    }

    private List<Paczka> getListaPaczek() {
        if (null == listaPaczekDoWyslania) {
            listaPaczekDoWyslania = new ArrayList<>();
        }
        return listaPaczekDoWyslania;
    }

    public int getBankId() {
        return idBanku;
    }

}
