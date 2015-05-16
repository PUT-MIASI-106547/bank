/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.miasi.controlers.kir;

import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.TestInjector;
import com.bank.miasi.model.kir.Przesylka;
import com.bank.miasi.Constants;
import com.bank.miasi.controlers.providers.AccountTypeProvider;
import com.bank.miasi.controlers.providers.OperationTypeProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bank.miasi.controlers.providers.BankProvider;

import java.math.BigDecimal;

import org.powermock.api.easymock.PowerMock;

/**
 * @author Johnny
 */
public class BankTest {

    private Bank bank;
    private Bank bankDocelowy;

    BankProvider provider;
    OperationTypeProvider operationTypeProvider;
    AccountTypeProvider accountTypeProvider;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestInjector());
        provider = injector.getInstance(BankProvider.class);
        operationTypeProvider = injector.getInstance(OperationTypeProvider.class);
        accountTypeProvider = injector.getInstance(AccountTypeProvider.class);
        bank = provider.getInstance(Constants.BANK_ALIOR_BANK);
        bankDocelowy = provider.getInstance(Constants.BANK_WBK);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of pobierzPaczki method, of class ManagerKIR.
     */
    @Test
    public void testPobierzPaczki() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

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
       /* Przesylka przesylka = Przesylka.getPrzesylkaFromOperacje(new OperacjaBankowaMock(operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), BigDecimal.ZERO, null, null, null, null));

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
        Przesylka przesylka = Przesylka.getPrzesylkaFromOperacje(new OperacjaBankowa(operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), BigDecimal.ZERO, null, null, null, null));
        bank.dodajPaczkeDoWyslania(bankDocelowy, przesylka);
        PowerMock.mockStatic(BankProvider.class);
//        Mock
        bank.wyslijPaczki();
    }


}
