package com.bank.miasi.model.operacje;

import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public interface TypOperacji {

    String getName();

    BigDecimal getKwota(BigDecimal kwota);
    TypOperacji getReverse();

    boolean isZewnetrzny();

    boolean isSecure();
}
