/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.miasi;

import com.bank.miasi.exceptions.NieWystarczajacoSrodkow;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.kir.ManagerKIR;
import com.bank.miasi.konta.Konto;
import com.bank.miasi.konta.KontoBankowe;
import com.bank.miasi.konta.typy.KontoWygodne;
import com.bank.miasi.operacje.PrzelewWychodzacy;
import com.bank.miasi.operacje.TypOperacji;
import com.bank.miasi.operacje.Wplata;
import com.bank.miasi.test.SymulatorZewnetrznegoKIR;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
public class OperacjaBankowaTest {

    Klient klient1;
    Klient klient2;
    KontoBankowe kontoKlient1;
    KontoBankowe kontoKlient2;
    ManagerKIR managerKIR;

    public OperacjaBankowaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        klient1 = new Klient("krzychu", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00));
        klient2 = new Klient("Jakub", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00));
        kontoKlient1 = new KontoBankowe(new KontoWygodne(), "41111", klient1);
        kontoKlient2 = new KontoBankowe(new KontoWygodne(), "41111", klient2);
        managerKIR = new ManagerKIR(new SymulatorZewnetrznegoKIR(), 4, "test");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of wykonajOperacje method, of class OperacjaBankowa.
     */
    @Test
    public void testWykonajOperacje() throws Exception {
        System.out.println("wykonajOperacje");
        BigDecimal kwota = new BigDecimal(100.10);
        TypOperacji typOperacji = new PrzelewWychodzacy();
        InicjujWartosc(1000.0, kontoKlient1);
        InicjujWartosc(1000.0, kontoKlient2);
        String tytul = "test";

        OperacjaBankowa.wykonajOperacje(managerKIR, kwota, typOperacji, tytul, kontoKlient1, kontoKlient2);
        assertEquals(899.90, kontoKlient1.getStan().doubleValue(), 0);
        assertEquals(1100.10, kontoKlient2.getStan().doubleValue(), 0);
        assertEquals(2, getHistoria(kontoKlient1).size());
        assertEquals(2, getHistoria(kontoKlient2).size());

    }

    private List<OperacjaBankowa> getHistoria(Konto kontoKlient) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date dateOd = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        Date dateDo = calendar.getTime();
        List<OperacjaBankowa> historia = kontoKlient.getHistoria(dateOd, dateDo);
        return historia;
    }

    /**
     * Test of wykonajOperacje method, of class OperacjaBankowa.
     */
    @Test(expected = NieWystarczajacoSrodkow.class)
    public void testWykonajOperacjeBezSrodkow() throws Exception {
        System.out.println("wykonajOperacje");
        BigDecimal kwota = new BigDecimal(1000.10);
        TypOperacji typOperacji = new PrzelewWychodzacy();
        InicjujWartosc(1000.0, kontoKlient1);
        InicjujWartosc(1000.0, kontoKlient2);
        String tytul = "test";

        OperacjaBankowa.wykonajOperacje(managerKIR, kwota, typOperacji, tytul, kontoKlient1, kontoKlient2);

    }

    private void InicjujWartosc(double ile, Konto konto) throws NiewspieranaOperacja {
        OperacjaBankowa.wykonajOperacje(managerKIR, new BigDecimal(ile), new Wplata(), "wplata Poczatkowa", konto, null);
    }

}
