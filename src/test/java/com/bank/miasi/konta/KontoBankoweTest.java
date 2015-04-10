/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.miasi.konta;

import com.bank.miasi.Klient;
import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.OperacjaBankowaTest;
import com.bank.miasi.konta.typy.KontoWygodne;
import java.math.BigDecimal;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
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
        OperacjaBankowa operacjaBankowa;
        operacjaBankowa = EasyMock.createNiceMock(OperacjaBankowa.class);
        EasyMock.expect(operacjaBankowa.getKwota()).andReturn(new BigDecimal(10.0));
        Konto kontoKlient1 = new KontoBankowe(new KontoWygodne(), "41111", klient1);
        kontoKlient1.wplata(operacjaBankowa);
        assertEquals(kontoKlient1.getStan(), operacjaBankowa.getKwota());
        // TODO review the generated test code and remove the default call to fail.
    }

}
