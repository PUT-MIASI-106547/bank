package com.bank.miasi.model.operacje;

import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class PrzelewWychodzacy implements TypOperacji {

    @Override
    public String getName() {
        return "Przelew wychodzacy";
    }

    @Override
    public BigDecimal getKwota(BigDecimal kwota) {
        return kwota.negate();
    }

    public TypOperacji getReverse() {
        return new PrzelewPrzychodzacy();
    }

    @Override
    public boolean isZewnetrzny() {
        return true;
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
