package com.bank.miasi.kir;

import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.service.DependencyInjection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {

    private List<Paczka> listaPaczekDoWyslania = new ArrayList<>();
    private int idBanku;
    private String haslo;

    private KIR kirInstance = DependencyInjection.getKirInstance();

    public Bank(int idBanku, String haslo) {
        this.idBanku = idBanku;
        this.haslo = haslo;
    }

    public void pobierzPaczki() {
        UUID sesja = kirInstance.zaloguj(idBanku, haslo);
        for (Paczka paczka : kirInstance.sciagnijPaczkiDoBanku(sesja)) {
            przetworzPaczke(paczka.getPrzesylka());
        }
    }

    public void wyslijPaczki() {
        UUID sesja = kirInstance.zaloguj(idBanku, haslo);
        kirInstance.odbierzPaczkiZBanku(listaPaczekDoWyslania, sesja);
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
        if (null == listaPaczekDoWyslania) {
            listaPaczekDoWyslania = new ArrayList<>();
        }
        this.listaPaczekDoWyslania.add(new Paczka(this, bankDocelowy, przesylka));
    }

    public int getBankId() {
        return idBanku;
    }
}
