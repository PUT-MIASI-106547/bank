package com.bank.miasi.konta.typy;

import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class KontoWygodne implements TypKonta {

    @Override
    public BigDecimal getOprocentowanie() {
        return new BigDecimal(0);
    }

    @Override
    public int getOkresRozliczeniowy() {
        return 30;
    }

}
