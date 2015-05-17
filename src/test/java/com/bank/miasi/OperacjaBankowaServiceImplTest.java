/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.miasi;

import com.bank.miasi.controlers.kir.Bank;
import com.bank.miasi.controlers.kir.KIR;
import com.bank.miasi.controlers.providers.Provider;
import com.bank.miasi.exceptions.NieWystarczajacoSrodkow;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.model.Klient;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.Kontable;
import com.bank.miasi.model.operacje.OperationType;
import com.bank.miasi.services.OperacjaBankowaServiceImpl;
import com.bank.miasi.services.api.OperacjaBankowaService;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Krzysztof
 */
public class OperacjaBankowaServiceImplTest {

    private Kontable kontoKlient1;
    private Kontable kontoKlient2;
    private OperacjaBankowaService operacjaBankowaService;
    @Inject
    private Provider<OperationType> operationTypeProvider;
    @Inject
    private TestUtil testUtil;
    @Inject
    private KIR kir;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestInjector());
        injector.injectMembers(this);
        Klient klient1 = testUtil.createKlient(1);
        Klient klient2 = testUtil.createKlient(2);
        Bank bank = testUtil.createBank(1, kir);
        kontoKlient1 = testUtil.createKontoBankowe(bank, klient1);
        kontoKlient2 = testUtil.createKontoBankowe(bank, klient2);
        operacjaBankowaService = new OperacjaBankowaServiceImpl();
        injector.injectMembers(operacjaBankowaService);
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
        OperationType operationType = operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY);
        InicjujWartosc(1000.0, kontoKlient1);
        InicjujWartosc(1000.0, kontoKlient2);
        String tytul = "test";

        operacjaBankowaService.wykonajOperacje(kwota, operationType, tytul, kontoKlient1, kontoKlient2);
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
        OperationType operationType = operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY);
        InicjujWartosc(1000.0, kontoKlient1);
        InicjujWartosc(1000.0, kontoKlient2);
        String tytul = "test";

        operacjaBankowaService.wykonajOperacje(kwota, operationType, tytul, kontoKlient1, kontoKlient2);

    }

    private void InicjujWartosc(double ile, Kontable konto) throws NiewspieranaOperacja {
        operacjaBankowaService.wykonajOperacje(new BigDecimal(ile), operationTypeProvider.getInstance(Constants.WPLATA), "wplata Poczatkowa", konto, null);
    }

}
