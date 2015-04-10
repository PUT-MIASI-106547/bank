package com.bank.miasi.konta.typy;

import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class LokataRoczna implements TypKonta {

    @Override
    public BigDecimal getOprocentowanie() {
        return new BigDecimal(2);
    }

    @Override
    public int getOkresRozliczeniowy() {
        return 365;
    }

}
