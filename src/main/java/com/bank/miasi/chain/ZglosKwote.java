package com.bank.miasi.chain;

import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.exceptions.NiewspieranaOperacja;

import java.math.BigDecimal;
import java.util.logging.Logger;

/**
 *
 * @author Krzysztof
 */
public class ZglosKwote extends ChainValidator {

    private final BigDecimal limit;

    public ZglosKwote(ChainValidator next, BigDecimal limit) {
        super(next);
        this.limit = limit;
    }

    @Override
    public void checkValidity(OperacjaBankowa operacja) throws NiewspieranaOperacja {
        if (operacja.getKwota().compareTo(limit) > 0 && !operacja.getTypOperacji().isSecure()) {
            Logger.getLogger(ZglosKwote.class.getName()).info("Podejrzanie wysoka kwota");
        }
                checkNext(operacja);

    }

}
