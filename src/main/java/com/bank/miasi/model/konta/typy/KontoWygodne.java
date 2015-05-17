package com.bank.miasi.model.konta.typy;

import java.math.BigDecimal;

public class KontoWygodne implements AccountType {

    @Override
    public BigDecimal getOprocentowanie() {
        return new BigDecimal(0);
    }

    @Override
    public int getOkresRozliczeniowy() {
        return 30;
    }

}
