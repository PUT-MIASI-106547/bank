/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.miasi.konta;

import com.bank.miasi.Klient;
import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.OperacjaBankowaMock;
import com.bank.miasi.TestInjector;
import com.bank.miasi.konta.typy.KontoWygodne;
import com.bank.miasi.operacje.Wplata;
import com.bank.miasi.service.Constants;
import com.bank.miasi.service.providers.BankProvider;

import java.math.BigDecimal;
import java.util.Date;

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
public class KontoBankoweTest {

    Klient klient1 = new Klient("krzychu", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00));
    Klient klient2 = new Klient("Jakub", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00));
    BankProvider provider;
    OperationTypeProvider operationTypeProvider;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestInjector());
        provider = injector.getInstance(BankProvider.class);
        operationTypeProvider = injector.getInstance(OperationTypeProvider.class);
    }

    /**
     * Test of wplata method, of class KontoBankowe.
     */
    @Test
    public void testWplata() throws Exception {
        System.out.println("wplata");
        Kontable kontoKlient1 = new KontoBankowe(provider.getInstance(Constants.BANK_ALIOR_BANK), new KontoWygodne(), "41111", klient1);
        OperacjaBankowa operacjaBankowa = new OperacjaBankowaMock(operationTypeProvider.getInstance(Constants.WPLATA), new BigDecimal(100), "test", kontoKlient1, null, new Date());
        kontoKlient1.wplata(operacjaBankowa);
        assertEquals(kontoKlient1.getStan(), operacjaBankowa.getKwota());
        // TODO review the generated test code and remove the default call to fail.
    }

}
