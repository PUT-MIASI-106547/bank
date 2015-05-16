package com.bank.miasi.model.operacje;

import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class Wplata implements TypOperacji {

    @Override
    public String getName() {
        return "Wplata";
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
