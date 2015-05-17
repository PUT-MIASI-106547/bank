package com.bank.miasi.chain;

import com.bank.miasi.TestInjector;
import com.bank.miasi.TestUtil;
import com.bank.miasi.model.OperacjaBankowa;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class ZglosKwoteTest {

    private static final BigDecimal LIMIT = new BigDecimal(1000);
    private static final BigDecimal LOWER_LIMIT = new BigDecimal(999.99);
    private static final BigDecimal GREATER_LIMIT = new BigDecimal(1000.01);
    @Inject
    private TestUtil testUtil;
    private List<LogRecord> list;
    private ZglosKwote zglosKwote = new ZglosKwote(null, LIMIT);

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestInjector());
        injector.injectMembers(this);
        list = new ArrayList<>();

        PowerMock.mockStatic(Logger.class);
        Logger logger1 = Logger.getLogger(ZglosKwote.class.getName());
        logger1.addHandler(new Handler() {
            @Override
            public void publish(LogRecord record) {
                list.add(record);
            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        });
    }

    @Test
    public void testCheckValidityLower() throws Exception {
        OperacjaBankowa operacja = testUtil.createOperacjaBankowa(null, null, LOWER_LIMIT);
        zglosKwote.checkValidity(operacja);
        assertEquals(0, list.size());
    }

    @Test
    public void testCheckValidityEquals() throws Exception {
        OperacjaBankowa operacja = testUtil.createOperacjaBankowa(null, null, LIMIT);
        zglosKwote.checkValidity(operacja);
        assertEquals(0, list.size());
    }

    @Test
    public void testCheckValidityGreater() throws Exception {
        OperacjaBankowa operacja = testUtil.createOperacjaBankowa(null, null, GREATER_LIMIT);
        zglosKwote.checkValidity(operacja);
        assertEquals(1, list.size());
        assertEquals("Podejrzanie wysoka kwota", list.get(0).getMessage());
    }

    @Test
    public void testCheckValidityGreaterSecure() throws Exception {
        OperacjaBankowa operacja = testUtil.createOperacjaBankowaWplata(null, null, GREATER_LIMIT);
        zglosKwote.checkValidity(operacja);
        assertEquals(0, list.size());
    }
}