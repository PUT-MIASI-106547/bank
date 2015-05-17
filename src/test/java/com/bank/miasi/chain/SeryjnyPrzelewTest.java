package com.bank.miasi.chain;

import com.bank.miasi.TestInjector;
import com.bank.miasi.TestUtil;
import com.bank.miasi.controlers.kir.Bank;
import com.bank.miasi.controlers.kir.KIR;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.KontoBankowe;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class SeryjnyPrzelewTest {

    private static final BigDecimal LOWER_LIMIT = new BigDecimal(1000.01);
    @Inject
    private TestUtil testUtil;
    @Inject
    private KIR kir;
    private KontoBankowe odkogo;
    private KontoBankowe dokogo;
    private KontoBankowe dokogo2;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestInjector());
        injector.injectMembers(this);
        Bank bank = testUtil.createBank(11, kir);
        odkogo = testUtil.createKontoBankowe(bank, testUtil.createKlient(1));
        dokogo = testUtil.createKontoBankowe(bank, testUtil.createKlient(2));
        dokogo2 = testUtil.createKontoBankowe(bank, testUtil.createKlient(3));

    }

    @Test(expected = NiewspieranaOperacja.class)
    public void testCheckValidityException() throws Exception {
        SeryjnyPrzelew seryjnyPrzelew = new SeryjnyPrzelew(null, 1, 5);
        testUtil.createOperacjaBankowa(odkogo, dokogo, LOWER_LIMIT);
        OperacjaBankowa operacja = testUtil.createOperacjaBankowa(odkogo, dokogo, LOWER_LIMIT);
        seryjnyPrzelew.checkValidity(operacja);
        operacja = testUtil.createOperacjaBankowa(odkogo, dokogo2, LOWER_LIMIT);
        seryjnyPrzelew.checkValidity(operacja);
        operacja = testUtil.createOperacjaBankowa(odkogo, dokogo2, LOWER_LIMIT);
        seryjnyPrzelew.checkValidity(operacja);
    }

    @Test
    public void testCheckValidityValid() throws Exception {
        SeryjnyPrzelew seryjnyPrzelew = new SeryjnyPrzelew(null, 2, 5);
        OperacjaBankowa operacja = testUtil.createOperacjaBankowa(odkogo, dokogo, LOWER_LIMIT);
        seryjnyPrzelew.checkValidity(operacja);
        operacja = testUtil.createOperacjaBankowa(odkogo, dokogo2, LOWER_LIMIT);
        seryjnyPrzelew.checkValidity(operacja);
        operacja = testUtil.createOperacjaBankowa(odkogo, dokogo2, LOWER_LIMIT);
        seryjnyPrzelew.checkValidity(operacja);
    }

    @Test
    public void testCheckValidityValidShortHistory() throws Exception {
        SeryjnyPrzelew seryjnyPrzelew = new SeryjnyPrzelew(null, 1, 1);
        OperacjaBankowa operacja;
        operacja = testUtil.createOperacjaBankowa(odkogo, dokogo, LOWER_LIMIT);
        seryjnyPrzelew.checkValidity(operacja);
        operacja = testUtil.createOperacjaBankowa(odkogo, dokogo2, LOWER_LIMIT);
        seryjnyPrzelew.checkValidity(operacja);
        operacja = testUtil.createOperacjaBankowa(odkogo, dokogo, LOWER_LIMIT);
        seryjnyPrzelew.checkValidity(operacja);
    }

}