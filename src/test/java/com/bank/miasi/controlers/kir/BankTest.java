package com.bank.miasi.controlers.kir;

import com.bank.miasi.TestInjector;
import com.bank.miasi.TestUtil;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.kir.Paczka;
import com.bank.miasi.model.kir.Przesylka;
import com.bank.miasi.model.konta.KontoBankowe;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankTest {

    private Bank bank;
    private Bank bankDocelowy;
    private Przesylka przesylka;

    @Inject
    private TestUtil testUtil;
    private KontoBankowe odbiorca;
    private int size;
    private int count = 0;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new TestInjector());
        injector.injectMembers(this);
        odbiorca = new KontoBankowe(null, null, null, null) {
            @Override
            public void wplata(OperacjaBankowa operacjaBankowa) throws NiewspieranaOperacja {
                count++;
            }
        };
        KIR kir = new KIR() {
            @Override
            public UUID zaloguj(int idBanku, String haslo) {
                return UUID.randomUUID();
            }

            @Override
            public void wyloguj(UUID idSesji) {

            }

            @Override
            public List<Paczka> sciagnijPaczkiDoBanku(UUID idSesji) {
                List<Paczka> paczkaList = new ArrayList<>();
                Przesylka przesylka = Przesylka.getPrzesylkaFromOperacje(testUtil.createOperacjaBankowa(testUtil.createKontoBankowe(bank, testUtil.createKlient(1)), odbiorca));
                paczkaList.add(new Paczka(bank, bankDocelowy, przesylka));
                return paczkaList;
            }

            @Override
            public void odbierzPaczkiZBanku(List<Paczka> listaPaczek, UUID idSesji) {
                size = listaPaczek.size();
            }
        };
        przesylka = Przesylka.getPrzesylkaFromOperacje(testUtil.createOperacjaBankowa(testUtil.createKontoBankowe(bank, testUtil.createKlient(1)), odbiorca));
        bank = testUtil.createBank(10, kir);
        bankDocelowy = testUtil.createBank(11, kir);

    }

    @Test
    public void testPobierzPaczki() throws Exception {

        bank.pobierzPaczki();
        Assert.assertEquals(1, count);

    }

    @Test
    public void testWyslijPaczki() throws Exception {
        bank.dodajPaczkeDoWyslania(bankDocelowy, przesylka);
        bank.wyslijPaczki();
        Assert.assertEquals(1, size);
    }

    @Test
    public void testWyslijPaczki2() throws Exception {
        bank.dodajPaczkeDoWyslania(bankDocelowy, przesylka);
        bank.wyslijPaczki();
        Assert.assertEquals(1, size);
        bank.wyslijPaczki();
        Assert.assertEquals(0, size);
    }
}