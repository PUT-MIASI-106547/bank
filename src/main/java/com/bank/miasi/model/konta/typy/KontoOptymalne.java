package com.bank.miasi.model.konta.typy;

import java.math.BigDecimal;

public class KontoOptymalne implements AccountType {

    @Override
    public BigDecimal getOprocentowanie() {
        return new BigDecimal(3);
    }

    @Override
    public int getOkresRozliczeniowy() {
        return 30;
    }

}
