package com.bank.miasi.operacje;

import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class PrzelewWychodzacy implements TypOperacji{

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

}
