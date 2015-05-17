package com.bank.miasi.model;

import com.bank.miasi.model.konta.typy.KontoOptymalne;
import com.bank.miasi.model.konta.typy.KontoWygodne;
import com.bank.miasi.model.konta.typy.LokataOptymalna;
import com.bank.miasi.model.konta.typy.LokataRoczna;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class OdsetkiKalkulatorTest {

    @Test
    public void testObliczOdsetkiLO() throws Exception {
        OdsetkiKalkulator kalkulator = new OdsetkiKalkulator(new LokataOptymalna());
        BigDecimal odsetki = kalkulator.obliczOdsetki(new BigDecimal(1000));
        assertEquals(new BigDecimal(0.59).doubleValue(), odsetki.doubleValue(),0.01);
    }

    @Test
    public void testObliczOdsetkiKO() throws Exception {
        OdsetkiKalkulator kalkulator = new OdsetkiKalkulator(new KontoOptymalne());
        BigDecimal odsetki = kalkulator.obliczOdsetki(new BigDecimal(1000));
        assertEquals(new BigDecimal(2), odsetki);
    }

    @Test
    public void testObliczOdsetkiKW() throws Exception {
        OdsetkiKalkulator kalkulator = new OdsetkiKalkulator(new KontoWygodne());
        BigDecimal odsetki = kalkulator.obliczOdsetki(new BigDecimal(1000));
        assertEquals(new BigDecimal(0), odsetki);
    }

    @Test
    public void testObliczOdsetkiLR() throws Exception {
        OdsetkiKalkulator kalkulator = new OdsetkiKalkulator(new LokataRoczna());
        BigDecimal odsetki = kalkulator.obliczOdsetki(new BigDecimal(1000));
        assertEquals(new BigDecimal(20), odsetki);
    }
}