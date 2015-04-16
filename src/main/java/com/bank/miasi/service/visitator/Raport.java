package com.bank.miasi.service.visitator;

import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.konta.Kontable;

/**
 *
 * @author inf106547
 */
public interface Raport {

    public void addRow(OperacjaBankowa aThis);

    public void addHeader(Kontable konto);

    public void print();
}
