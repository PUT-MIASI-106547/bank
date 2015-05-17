package com.bank.miasi.model.operacje;

import java.math.BigDecimal;

public class Wplata implements OperationType {

    @Override
    public String getName() {
        return "Wplata";
    }

    @Override
    public BigDecimal getKwota(BigDecimal kwota) {
        return kwota;
    }

    public OperationType getReverse() {
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
