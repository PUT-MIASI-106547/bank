package com.bank.miasi.controlers.kir;

import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.kir.Paczka;
import com.bank.miasi.model.kir.Przesylka;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {

    private static final Logger LOG = Logger.getLogger(Bank.class.getName());
    private List<Paczka> listaPaczekDoWyslania = new ArrayList<>();
    private int idBanku;
    private String haslo;

    private KIR kir;

    public Bank(int idBanku, String haslo, KIR kir) {
        this.idBanku = idBanku;
        this.haslo = haslo;
        this.kir = kir;
    }

    public void pobierzPaczki() {
        UUID sesja = kir.zaloguj(idBanku, haslo);
        for (Paczka paczka : kir.sciagnijPaczkiDoBanku(sesja)) {
            przetworzPaczke(paczka.getPrzesylka());
        }
    }

    public void wyslijPaczki() {
        UUID sesja = kir.zaloguj(idBanku, haslo);
        kir.odbierzPaczkiZBanku(getListaPaczek(), sesja);
        listaPaczekDoWyslania = null;
    }

    private void przetworzPaczke(Przesylka przesylka) {
        OperacjaBankowa operacja = przesylka.rozpakuj();
        try {
            przesylka.getOdbiorca().wplata(operacja);
        } catch (NiewspieranaOperacja ex) {
            dodajPaczkeDoWyslania(przesylka.getNadawca().getBank(), new Przesylka(operacja, przesylka.getOdbiorca(), przesylka.getNadawca()));
            LOG.log(Level.SEVERE, null, ex);
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
