/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.miasi.model.konta;

import com.bank.miasi.Constants;
import com.bank.miasi.TestInjector;
import com.bank.miasi.TestUtil;
import com.bank.miasi.controlers.kir.KIR;
import com.bank.miasi.exceptions.BlednaKwota;
import com.bank.miasi.exceptions.NieWystarczajacoSrodkow;
import com.bank.miasi.model.OperacjaBankowa;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class KontoBankoweTest {

    private Kontable kontoKlient1;
    @Inject
    private TestUtil testUtil;
    @Inject
    private KIR kir;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestInjector());
        injector.injectMembers(this);
        kontoKlient1 = testUtil.createKontoBankowe(testUtil.createBank(1, kir), testUtil.createKlient(1));
    }

    @Test
    public void testWplata() throws Exception {
        BigDecimal kwota = new BigDecimal(100);
        kontoKlient1.wplata(testUtil.createOperacjaBankowaWplata(kontoKlient1, null, kwota));

        assertEquals(kontoKlient1.getStan(), kwota);
    }

    @Test(expected = NieWystarczajacoSrodkow.class)
    public void testWyplata() throws Exception {
        BigDecimal kwota = new BigDecimal(100);
        kontoKlient1.wplata(testUtil.createOperacjaBankowaWyplata(kontoKlient1, null, kwota));

        assertEquals(kontoKlient1.getStan(), kwota);
    }

    @Test(expected = NieWystarczajacoSrodkow.class)
    public void testWplataLessThanZero() throws Exception {
        BigDecimal kwota = new BigDecimal(-100);
        kontoKlient1.wplata(testUtil.createOperacjaBankowaWplata(kontoKlient1, null, kwota));

        assertEquals(kontoKlient1.getStan(), kwota);
    }

    @Test(expected = BlednaKwota.class)
    public void testWplataZero() throws Exception {
        BigDecimal kwota = new BigDecimal(0);
        kontoKlient1.wplata(testUtil.createOperacjaBankowaWplata(kontoKlient1, null, kwota));

        assertEquals(kontoKlient1.getStan(), kwota);
    }
}
