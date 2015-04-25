/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.miasi;

import com.bank.miasi.exceptions.NieWystarczajacoSrodkow;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.konta.Kontable;
import com.bank.miasi.konta.KontoBankowe;
import com.bank.miasi.konta.typy.KontoWygodne;
import com.bank.miasi.operacje.PrzelewWychodzacy;
import com.bank.miasi.operacje.TypOperacji;
import com.bank.miasi.operacje.Wplata;
import com.bank.miasi.service.Constants;
import com.bank.miasi.service.providers.AccountTypeProvider;
import com.bank.miasi.service.providers.BankProvider;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bank.miasi.service.providers.OperationTypeProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Krzysztof
 */
public class OperacjaBankowaTest {

    Klient klient1;
    Klient klient2;
    Kontable kontoKlient1;
    Kontable kontoKlient2;

    BankProvider provider;
    OperationTypeProvider operationTypeProvider;
    AccountTypeProvider accountTypeProvider;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestInjector());
        provider = injector.getInstance(BankProvider.class);
        operationTypeProvider = injector.getInstance(OperationTypeProvider.class);
        accountTypeProvider = injector.getInstance(AccountTypeProvider.class);

        klient1 = new Klient("krzychu", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00));
        klient2 = new Klient("Jakub", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00));
        kontoKlient1 = new KontoBankowe(provider.getInstance(Constants.BANK_ALIOR_BANK), accountTypeProvider.getInstance(Constants.KONTO_WYGODNE), "41111", klient1);
        kontoKlient2 = new KontoBankowe(provider.getInstance(Constants.BANK_ALIOR_BANK), accountTypeProvider.getInstance(Constants.KONTO_WYGODNE), "41111", klient2);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of wykonajOperacje method, of class OperacjaBankowa.
     */
    @Test
    public void testWykonajOperacje() throws Exception {
        BigDecimal kwota = new BigDecimal(100.10);
        TypOperacji typOperacji = operationTypeProvider.getInstance(Constants.PRZELEW_PRZYCHODZACY);
        InicjujWartosc(1000.0, kontoKlient1);
        InicjujWartosc(1000.0, kontoKlient2);
        String tytul = "test";

        OperacjaBankowa.wykonajOperacje(kwota, typOperacji, tytul, kontoKlient1, kontoKlient2);
        assertEquals(899.90, kontoKlient2.getStan().doubleValue(), 0);
        assertEquals(1100.10, kontoKlient1.getStan().doubleValue(), 0);
        assertEquals(2, getHistoria(kontoKlient1).size());
        assertEquals(2, getHistoria(kontoKlient2).size());

    }

    private List<OperacjaBankowa> getHistoria(Kontable kontoKlient) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date dateOd = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        Date dateDo = calendar.getTime();
        return kontoKlient.getHistoria(dateOd, dateDo);
    }

    /**
     * Test of wykonajOperacje method, of class OperacjaBankowa.
     */
    @Test(expected = NieWystarczajacoSrodkow.class)
    public void testWykonajOperacjeBezSrodkow() throws Exception {
        BigDecimal kwota = new BigDecimal(1000.10);
        TypOperacji typOperacji = operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY);
        InicjujWartosc(1000.0, kontoKlient1);
        InicjujWartosc(1000.0, kontoKlient2);
        String tytul = "test";

        OperacjaBankowa.wykonajOperacje(kwota, typOperacji, tytul, kontoKlient1, kontoKlient2);

    }

    private void InicjujWartosc(double ile, Kontable konto) throws NiewspieranaOperacja {
        OperacjaBankowa.wykonajOperacje(new BigDecimal(ile), operationTypeProvider.getInstance(Constants.WPLATA), "wplata Poczatkowa", konto, null);
    }

}
