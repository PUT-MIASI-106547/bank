/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.miasi.konta;

import com.bank.miasi.Klient;
import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.OperacjaBankowaMock;
import com.bank.miasi.konta.typy.KontoWygodne;
import com.bank.miasi.operacje.Wplata;
import com.bank.miasi.service.DependencyInjection;

import java.math.BigDecimal;
import java.util.Date;

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

    public KontoBankoweTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of wplata method, of class KontoBankowe.
     */
    @Test
    public void testWplata() throws Exception {
        System.out.println("wplata");
        Kontable kontoKlient1 = new KontoBankowe(DependencyInjection.getAliorBank(), new KontoWygodne(), "41111", klient1);
        OperacjaBankowa operacjaBankowa = new OperacjaBankowaMock(new Wplata(), new BigDecimal(100), "test", kontoKlient1, null, new Date());
        kontoKlient1.wplata(operacjaBankowa);
        assertEquals(kontoKlient1.getStan(), operacjaBankowa.getKwota());
        // TODO review the generated test code and remove the default call to fail.
    }

}
