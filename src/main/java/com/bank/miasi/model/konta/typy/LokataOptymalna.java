package com.bank.miasi.model.konta.typy;

import java.math.BigDecimal;

public class LokataOptymalna implements AccountType {

    @Override
    public BigDecimal getOprocentowanie() {
        return new BigDecimal(0.7);
    }

    @Override
    public int getOkresRozliczeniowy() {
        return 31;
    }

}
