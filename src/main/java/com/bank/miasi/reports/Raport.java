package com.bank.miasi.reports;

import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.Kontable;

/**
 *
 * @author inf106547
 */
public interface Raport {

    void addRow(OperacjaBankowa aThis);

    void addHeader(Kontable konto);

    void print();
}
