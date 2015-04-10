/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.miasi;

import com.bank.miasi.konta.typy.TypKonta;
import java.math.BigDecimal;

/**
 *
 * @author student
 */
public interface OdsetkiState {

    BigDecimal obliczOdsetki(BigDecimal stanKonta, TypKonta typKonta);
}
