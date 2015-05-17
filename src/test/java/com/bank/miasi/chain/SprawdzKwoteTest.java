package com.bank.miasi.chain;

import com.bank.miasi.TestInjector;
import com.bank.miasi.TestUtil;
import com.bank.miasi.exceptions.ZbytWysokaOperacjaException;
import com.bank.miasi.model.OperacjaBankowa;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class SprawdzKwoteTest {

    private static final BigDecimal LIMIT = new BigDecimal(1000);
    private static final BigDecimal LOWER_LIMIT = new BigDecimal(999.99);
    private static final BigDecimal GREATER_LIMIT = new BigDecimal(1000.01);
    @Inject
    private TestUtil testUtil;
    private SprawdzKwote sprawdzKwote = new SprawdzKwote(null, LIMIT);

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestInjector());
        injector.injectMembers(this);
    }

    @Test
    public void testCheckValidityLower() throws Exception {
        OperacjaBankowa operacja = testUtil.createOperacjaBankowa(null, null, LOWER_LIMIT);
        sprawdzKwote.checkValidity(operacja);
    }

    @Test
    public void testCheckValidityEquals() throws Exception {
        OperacjaBankowa operacja = testUtil.createOperacjaBankowa(null, null, LIMIT);
        sprawdzKwote.checkValidity(operacja);
    }

    @Test(expected = ZbytWysokaOperacjaException.class)
    public void testCheckValidityGreater() throws Exception {
        OperacjaBankowa operacja = testUtil.createOperacjaBankowa(null, null, GREATER_LIMIT);
        sprawdzKwote.checkValidity(operacja);
    }

    @Test
    public void testCheckValidityGreaterSecure() throws Exception {
        OperacjaBankowa operacja = testUtil.createOperacjaBankowaWplata(null, null, LOWER_LIMIT);
        sprawdzKwote.checkValidity(operacja);
    }
}