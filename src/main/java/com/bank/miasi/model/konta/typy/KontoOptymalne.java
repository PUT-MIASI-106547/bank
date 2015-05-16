package com.bank.miasi.model.konta.typy;

import java.math.BigDecimal;

/**
 * @author Krzysztof
 */
public class KontoOptymalne implements TypKonta {

    @Override
    public BigDecimal getOprocentowanie() {
        return new BigDecimal(3);
    }

    @Override
    public int getOkresRozliczeniowy() {
        return 30;
    }

}
