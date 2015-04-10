package com.bank.miasi;

import com.bank.miasi.konta.typy.TypKonta;
import java.math.BigDecimal;

public class Odsetki {

    private OdsetkiState state;

    public Odsetki(OdsetkiState state) {
        this.state = state;
    }

    public BigDecimal obliczOdsetki(BigDecimal stanKonta, TypKonta typKonta) {
        return state.obliczOdsetki(stanKonta, typKonta);
    }
}
