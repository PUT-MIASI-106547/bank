package com.bank.miasi.operacje;

import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class PrzelewPrzychodzacy implements TypOperacji {

    @Override
    public String getName() {
        return "Przelew zewnetrzny";
    }

    @Override
    public BigDecimal getKwota(BigDecimal kwota) {
        return kwota;
    }

    public TypOperacji getReverse() {
        return new PrzelewWychodzacy();
    }

    @Override
    public boolean isZewnetrzny() {
        return false;
    }

}
