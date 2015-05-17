package com.bank.miasi.model.konta;

import com.bank.miasi.TestInjector;
import com.bank.miasi.TestUtil;
import com.bank.miasi.controlers.kir.KIR;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

public class LokataTest {

    private Lokata kontoKlient1;
    @Inject
    private TestUtil testUtil;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestInjector());
        injector.injectMembers(this);
        kontoKlient1 = testUtil.createLokata(null, testUtil.createKlient(1));

    }


    @Test(expected = NiewspieranaOperacja.class)
    public void testWplata() throws Exception {
        kontoKlient1.wplata(testUtil.createOperacjaBankowa(null, null));
    }
}