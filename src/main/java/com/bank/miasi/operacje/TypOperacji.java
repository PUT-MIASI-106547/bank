package com.bank.miasi.operacje;

import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public interface TypOperacji {

    public String getName();

    public BigDecimal getKwota(BigDecimal kwota);
    public TypOperacji getReverse();
}
