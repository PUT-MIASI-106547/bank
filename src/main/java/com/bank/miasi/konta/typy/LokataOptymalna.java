package com.bank.miasi.konta.typy;

import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class LokataOptymalna implements TypKonta {

    @Override
    public BigDecimal getOprocentowanie() {
        return new BigDecimal(0.7);
    }

    @Override
    public int getOkresRozliczeniowy() {
        return 31;
    }

}
