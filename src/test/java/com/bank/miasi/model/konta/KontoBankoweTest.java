/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.miasi.model.konta;

import com.bank.miasi.Constants;
import com.bank.miasi.TestInjector;
import com.bank.miasi.controlers.providers.AccountTypeProvider;
import com.bank.miasi.controlers.providers.BankProvider;
import com.bank.miasi.controlers.providers.OperationTypeProvider;
import com.bank.miasi.model.Klient;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.services.api.KlientService;
import com.bank.miasi.services.api.KontoService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author Krzysztof
 */
public class KontoBankoweTest {

    private Klient klient1;
    private Klient klient2;
    private BankProvider provider;
    private OperationTypeProvider operationTypeProvider;
    private AccountTypeProvider accountTypeProvider;
    private KontoService kontoService;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestInjector());
        provider = injector.getInstance(BankProvider.class);
        operationTypeProvider = injector.getInstance(OperationTypeProvider.class);
        accountTypeProvider = injector.getInstance(AccountTypeProvider.class);
        kontoService = injector.getInstance(KontoService.class);
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
        Kontable kontoKlient1 = kontoService.createKontoBankowe(provider.getInstance(Constants.BANK_ALIOR_BANK), accountTypeProvider.getInstance(Constants.KONTO_WYGODNE), "41111", klient1);
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(operationTypeProvider.getInstance(Constants.WPLATA), new BigDecimal(100), "test", kontoKlient1, null, new Date());
        kontoKlient1.wplata(operacjaBankowa);
        assertEquals(kontoKlient1.getStan(), operacjaBankowa.getKwota());
        // TODO review the generated test code and remove the default call to fail.
    }

}
