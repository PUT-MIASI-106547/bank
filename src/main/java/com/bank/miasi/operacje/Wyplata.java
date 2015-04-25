package com.bank.miasi.operacje;

import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class Wyplata implements TypOperacji {

    @Override
    public String getName() {
        return "Wyplata";
    }

    @Override
    public BigDecimal getKwota(BigDecimal kwota) {
        return kwota;
    }

    public TypOperacji getReverse() {
        return this;
    }

    @Override
    public boolean isZewnetrzny() {
        return false;
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
