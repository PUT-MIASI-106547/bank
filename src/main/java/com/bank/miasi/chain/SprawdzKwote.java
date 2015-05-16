package com.bank.miasi.chain;

import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.exceptions.ZbytWysokaOperacjaException;
import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class SprawdzKwote extends ChainValidator {

    private final BigDecimal limit;

    public SprawdzKwote(ChainValidator next, BigDecimal limit) {
        super(next);
        this.limit = limit;
    }

    @Override
    public void checkValidity(OperacjaBankowa operacja) throws NiewspieranaOperacja {
        if (operacja.getKwota().compareTo(limit) > 0 && !operacja.getTypOperacji().isSecure()) {
            throw new ZbytWysokaOperacjaException();
        }
        checkNext(operacja);

    }

}
