package com.bank.miasi.chain;

import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.exceptions.NiewspieranaOperacja;

/**
 *
 * @author Krzysztof
 */
public abstract class ChainValidator {

    private ChainValidator next;

    public ChainValidator(ChainValidator next) {
        this.next = next;
    }

    public abstract void checkValidity(OperacjaBankowa operacja) throws NiewspieranaOperacja;

    protected void checkNext(OperacjaBankowa operacja) throws NiewspieranaOperacja {
        if (next!=null){
            next.checkValidity(operacja);
        }
    }
;
}
