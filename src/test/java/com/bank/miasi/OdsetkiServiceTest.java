package com.bank.miasi;

import com.bank.miasi.controlers.providers.Provider;
import com.bank.miasi.model.OdsetkiKalkulator;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Before;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class OdsetkiServiceTest {

    @Inject
    Provider<OdsetkiKalkulator> odsetkiKalkulatorProvider;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestInjector());
        injector.injectMembers(this);
    }

    /**
     * Test of obliczOdsetki method, of class OdsetkiKalkulator.
     */
    @org.junit.Test
    public void testObliczOdsetkiLokataRoczna() {
        BigDecimal stanKonta = new BigDecimal(1000);
        BigDecimal expResult = new BigDecimal("20.00");
        BigDecimal result = odsetkiKalkulatorProvider.getInstance(Constants.LOKATA_ROCZNA).obliczOdsetki(stanKonta);
        assertEquals(expResult.doubleValue(), result.doubleValue(), 0.001);
    }

    @org.junit.Test
    public void testObliczOdsetkiLokataOptymalna() {
        BigDecimal stanKonta = new BigDecimal("1000");
        BigDecimal expResult = new BigDecimal("0.594");
        BigDecimal result = odsetkiKalkulatorProvider.getInstance(Constants.LOKATA_OPTYMALSNA).obliczOdsetki(stanKonta);
        assertEquals(expResult.doubleValue(), result.doubleValue(), 0.001);
    }

    @org.junit.Test
    public void testObliczOdsetkiKontoWygodne() {
        BigDecimal stanKonta = new BigDecimal("1000");
        BigDecimal expResult = new BigDecimal("0.00");
        BigDecimal result = odsetkiKalkulatorProvider.getInstance(Constants.KONTO_WYGODNE).obliczOdsetki(stanKonta);
        assertEquals(expResult.doubleValue(), result.doubleValue(), 0.001);
    }

    @org.junit.Test
    public void testObliczOdsetkiLokataRoczna100000PLN59GR() {
        BigDecimal stanKonta = new BigDecimal("100000.59");
        BigDecimal expResult = new BigDecimal("2000.01");
        BigDecimal result = odsetkiKalkulatorProvider.getInstance(Constants.LOKATA_ROCZNA).obliczOdsetki(stanKonta);
        assertEquals(expResult.doubleValue(), result.doubleValue(), 0.001);
    }

    @org.junit.Test
    public void testObliczOdsetkiLokataOptymalna100000PLN59GR() {
        BigDecimal stanKonta = new BigDecimal("100000.59");
        BigDecimal expResult = new BigDecimal("59.452");
        BigDecimal result = odsetkiKalkulatorProvider.getInstance(Constants.LOKATA_OPTYMALSNA).obliczOdsetki(stanKonta);
        assertEquals(expResult.doubleValue(), result.doubleValue(), 0.001);
    }
}
