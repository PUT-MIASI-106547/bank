package com.bank.miasi.model.operacje;

import java.math.BigDecimal;

public class Wyplata implements OperationType {

    @Override
    public String getName() {
        return "Wyplata";
    }

    @Override
    public BigDecimal getKwota(BigDecimal kwota) {
        return kwota.negate();
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
