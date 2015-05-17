package com.bank.miasi.model.operacje;

import java.math.BigDecimal;

public class PrzelewWychodzacy implements OperationType {

    @Override
    public String getName() {
        return "Przelew wychodzacy";
    }

    @Override
    public BigDecimal getKwota(BigDecimal kwota) {
        return kwota.negate();
    }

    public OperationType getReverse() {
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
