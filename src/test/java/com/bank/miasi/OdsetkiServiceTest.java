package com.bank.miasi;

import java.math.BigDecimal;

import com.bank.miasi.services.OdsetkiService;
import com.bank.miasi.controlers.providers.AccountTypeProvider;
import com.bank.miasi.controlers.providers.BankProvider;
import com.bank.miasi.controlers.providers.OperationTypeProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * @author Johnny
 */
public class OdsetkiServiceTest {

    BankProvider provider;
    OperationTypeProvider operationTypeProvider;
    AccountTypeProvider accountTypeProvider;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestInjector());
        provider = injector.getInstance(BankProvider.class);
        operationTypeProvider = injector.getInstance(OperationTypeProvider.class);
        accountTypeProvider = injector.getInstance(AccountTypeProvider.class);

    }

    /**
     * Test of obliczOdsetki method, of class OdsetkiService.
     */
    @org.junit.Test
    public void testObliczOdsetkiLokataRoczna() {
        BigDecimal stanKonta = new BigDecimal(1000);
        BigDecimal expResult = new BigDecimal("20.00");
        BigDecimal result = new OdsetkiService(accountTypeProvider.getInstance(Constants.LOKATA_ROCZNA)).obliczOdsetki(stanKonta);
        assertEquals(expResult.doubleValue(), result.doubleValue(), 0.001);
    }

    @org.junit.Test
    public void testObliczOdsetkiLokataOptymalna() {
        BigDecimal stanKonta = new BigDecimal("1000");
        BigDecimal expResult = new BigDecimal("0.594");
        BigDecimal result = new OdsetkiService(accountTypeProvider.getInstance(Constants.LOKATA_OPTYMALSNA)).obliczOdsetki(stanKonta);
        assertEquals(expResult.doubleValue(), result.doubleValue(), 0.001);
    }

    @org.junit.Test
    public void testObliczOdsetkiKontoWygodne() {
        BigDecimal stanKonta = new BigDecimal("1000");
        BigDecimal expResult = new BigDecimal("0.00");
        BigDecimal result = new OdsetkiService(accountTypeProvider.getInstance(Constants.KONTO_WYGODNE)).obliczOdsetki(stanKonta);
        assertEquals(expResult.doubleValue(), result.doubleValue(), 0.001);
    }

    @org.junit.Test
    public void testObliczOdsetkiLokataRoczna100000PLN59GR() {
        BigDecimal stanKonta = new BigDecimal("100000.59");
        BigDecimal expResult = new BigDecimal("2000.01");
        BigDecimal result = new OdsetkiService(accountTypeProvider.getInstance(Constants.LOKATA_ROCZNA)).obliczOdsetki(stanKonta);
        assertEquals(expResult.doubleValue(), result.doubleValue(), 0.001);
    }

    @org.junit.Test
    public void testObliczOdsetkiLokataOptymalna100000PLN59GR() {
        BigDecimal stanKonta = new BigDecimal("100000.59");
        BigDecimal expResult = new BigDecimal("59.452");
        BigDecimal result = new OdsetkiService(accountTypeProvider.getInstance(Constants.LOKATA_OPTYMALSNA)).obliczOdsetki(stanKonta);
        assertEquals(expResult.doubleValue(), result.doubleValue(), 0.001);
    }
}
