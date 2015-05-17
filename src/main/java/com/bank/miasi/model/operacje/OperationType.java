package com.bank.miasi.model.operacje;

import java.math.BigDecimal;

public interface OperationType {

    String getName();

    BigDecimal getKwota(BigDecimal kwota);

    OperationType getReverse();

    boolean isZewnetrzny();

    boolean isSecure();
}
