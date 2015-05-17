package com.bank.miasi.model.operacje;

import java.math.BigDecimal;

public class PrzelewPrzychodzacy implements OperationType {

    @Override
    public String getName() {
        return "Przelew zewnetrzny";
    }

    @Override
    public BigDecimal getKwota(BigDecimal kwota) {
        return kwota;
    }

    public OperationType getReverse() {
        return new PrzelewWychodzacy();
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
