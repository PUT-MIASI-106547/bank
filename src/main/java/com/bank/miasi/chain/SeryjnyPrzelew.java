package com.bank.miasi.chain;

import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.exceptions.ZbytWysokaOperacjaException;
import com.bank.miasi.konta.Kontable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author Krzysztof
 */
public class SeryjnyPrzelew extends ChainValidator {

    private final int historyLimit;
    private final int historySize;
    private final Map<Kontable, Queue<Kontable>> historia = new HashMap<>();

    public SeryjnyPrzelew(ChainValidator next, int historyLimit, int historySize) {
        super(next);
        this.historyLimit = historyLimit;
        this.historySize = historySize;
    }

    @Override
    public void checkValidity(OperacjaBankowa operacja) throws NiewspieranaOperacja {
        final Kontable odKogo = operacja.getOdKogo();
        final Kontable doKogo = operacja.getDoKogo();
        if (!historia.containsKey(odKogo)) {
            historia.put(odKogo, new LinkedList<Kontable>());
        }
        Queue<Kontable> historiaKonta = historia.get(odKogo);
        if (countItemsInHistory(historiaKonta, doKogo) > historyLimit) {
            throw new NiewspieranaOperacja();
        }
        historiaKonta.add(doKogo);
        if (historiaKonta.size() > historySize) {
            historiaKonta.peek();
        }
        checkNext(operacja);
    }

    private long countItemsInHistory(Queue<Kontable> historiaKonta, final Kontable doKogo) {
        return historiaKonta.stream().filter(new Predicate<Kontable>() {

            @Override
            public boolean test(Kontable t) {
                if (t == null && doKogo == null) {
                    return true;
                }
                if (null != doKogo && t != null) {
                    return t.getNumer().equals(doKogo.getNumer());
                }
                return false;
            }
        }).count();
    }

}
