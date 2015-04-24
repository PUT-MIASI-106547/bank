package com.bank.miasi;

import com.bank.miasi.operacje.TypOperacji;
import com.bank.miasi.konta.Kontable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Krzysztof
 */
public class OperacjaBankowaMock extends OperacjaBankowa{

    public OperacjaBankowaMock(TypOperacji typOperacji, BigDecimal kwota, String tytul, Kontable odKogo, Kontable doKogo, Date data) {
        super(typOperacji, kwota, tytul, odKogo, doKogo, data);
    }

}
