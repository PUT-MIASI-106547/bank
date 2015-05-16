/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.miasi.model.konta;

import com.bank.miasi.model.Klient;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.TestInjector;
import com.bank.miasi.model.konta.typy.KontoWygodne;
import com.bank.miasi.Constants;
import com.bank.miasi.services.KlientService;
import com.bank.miasi.controlers.providers.BankProvider;

import java.math.BigDecimal;
import java.util.Date;

import com.bank.miasi.controlers.providers.OperationTypeProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Krzysztof
 */
public class KontoBankoweTest {

    Klient klient1;
    Klient klient2;
    BankProvider provider;
    OperationTypeProvider operationTypeProvider;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestInjector());
        provider = injector.getInstance(BankProvider.class);
        operationTypeProvider = injector.getInstance(OperationTypeProvider.class);
        KlientService klientService = injector.getInstance(KlientService.class);
        klient1 = klientService.createKlient("krzychu", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00), "qqq");
        klient2 = klientService.createKlient("Jakub", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00), "qqq");
    }

    /**
     * Test of wplata method, of class KontoBankowe.
     */
    @Test
    public void testWplata() throws Exception {
        System.out.println("wplata");
        Kontable kontoKlient1 = new KontoBankowe(provider.getInstance(Constants.BANK_ALIOR_BANK), new KontoWygodne(), "41111", klient1);
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(operationTypeProvider.getInstance(Constants.WPLATA), new BigDecimal(100), "test", kontoKlient1, null, new Date());
        kontoKlient1.wplata(operacjaBankowa);
        assertEquals(kontoKlient1.getStan(), operacjaBankowa.getKwota());
        // TODO review the generated test code and remove the default call to fail.
    }

}
