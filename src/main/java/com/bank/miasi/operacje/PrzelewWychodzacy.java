package com.bank.miasi.operacje;

import com.google.inject.Inject;

import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class PrzelewWychodzacy implements TypOperacji {

    @Inject
    private PrzelewPrzychodzacy reverseType;

    @Override
    public String getName() {
        return "Przelew wychodzacy";
    }

    @Override
    public BigDecimal getKwota(BigDecimal kwota) {
        return kwota.negate();
    }

    public TypOperacji getReverse() {
        return reverseType;
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
