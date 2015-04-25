package com.bank.miasi.operacje;

import com.google.inject.Inject;

import java.math.BigDecimal;

/**
 * @author Krzysztof
 */
public class PrzelewPrzychodzacy implements TypOperacji {

    @Inject
    private PrzelewWychodzacy reverseType;

    @Override
    public String getName() {
        return "Przelew zewnetrzny";
    }

    @Override
    public BigDecimal getKwota(BigDecimal kwota) {
        return kwota;
    }

    public TypOperacji getReverse() {
        return reverseType;
    }

    @Override
    public boolean isZewnetrzny() {
        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

}
