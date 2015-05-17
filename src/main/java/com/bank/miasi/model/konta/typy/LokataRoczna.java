package com.bank.miasi.model.konta.typy;

import java.math.BigDecimal;

public class LokataRoczna implements AccountType {

    @Override
    public BigDecimal getOprocentowanie() {
        return new BigDecimal(2);
    }

    @Override
    public int getOkresRozliczeniowy() {
        return 365;
    }


}
