/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.miasi.kir;

import com.bank.miasi.OperacjaBankowaMock;
import com.bank.miasi.test.SymulatorZewnetrznegoKIR;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.reflect.*;

import com.bank.miasi.operacje.PrzelewWychodzacy;
import com.bank.miasi.service.DependencyInjection;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.easymock.EasyMock;
import org.powermock.api.easymock.PowerMock;

/**
 * @author Johnny
 */
public class ManagerKIRTest {

    private Bank bank;
    private Bank bankDocelowy;

    public ManagerKIRTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        bank = new Bank(5, "www");
        bankDocelowy = new Bank(6, "ttt");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of pobierzPaczki method, of class ManagerKIR.
     */
    @Test
    public void testPobierzPaczki() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        System.out.println("pobierzPaczki");

        bank.pobierzPaczki();

    }

    /**
     * Test of wyslijPaczki method, of class ManagerKIR.
     */
    @Test
    public void testWyslijPaczki() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        bank.pobierzPaczki();
        bank.wyslijPaczki();


    }

    /**
     * Test of przetworzPaczke method, of class ManagerKIR.
     */
    @Test
    public void testPrzetworzPaczke() {
        /* Przesylka przesylka = new Przesylka(BigDecimal.ZERO, null, null, null)
         System.out.println("przetworzPaczke");
         Przesylka przesylka = null;
         ManagerKIR instance = null;
         instance.przetworzPaczke(przesylka);
         // TODO review the generated test code and remove the default call to fail.
         fail("The test case is a prototype.");*/
    }

    /**
     * Test of dodajPaczkeDoWyslania method, of class ManagerKIR.
     */
    @Test
    public void testDodajPaczkeDoWyslaniaTestPrzesylki() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
       /* Przesylka przesylka = Przesylka.getPrzesylkaFromOperacje(new OperacjaBankowaMock(new PrzelewWychodzacy(), BigDecimal.ZERO, null, null, null, null));

        bank.dodajPaczkeDoWyslania(bankDocelowy, przesylka);

        Field field = bank.getClass().getDeclaredField("listaPaczekDoWyslania");
        field.setAccessible(true);

        List<Paczka> lista = (List<Paczka>) field.get(bank);

        // TODO review the generated test code and remove the default call to fail.
        int lastIndex = (lista.size() - 1);
        Paczka dodanaPaczka = lista.get(lastIndex);

        Field fieldPaczki = dodanaPaczka.getClass().getDeclaredField("przesylka");
        fieldPaczki.setAccessible(true);

        Przesylka testowana = (Przesylka) fieldPaczki.get(dodanaPaczka);

        assertEquals(przesylka, testowana);*/
    }

    @Test
    public void testDodajPaczkeDoWyslaniaTestIdBankuOdbiorcy() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        Przesylka przesylka = Przesylka.getPrzesylkaFromOperacje(new OperacjaBankowaMock(new PrzelewWychodzacy(), BigDecimal.ZERO, null, null, null, null));
        bank.dodajPaczkeDoWyslania(bankDocelowy, przesylka);
        PowerMock.mockStatic(DependencyInjection.class);
//        Mock
        bank.wyslijPaczki();
    }

    class KirMock extends SymulatorZewnetrznegoKIR {
        @Override
        public void odbierzPaczkiZBanku(List<Paczka> wyslanePaczki, UUID idSesji) {
            assertEquals(wyslanePaczki.size(), 1);
        }
    }
}
