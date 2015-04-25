package com.bank.miasi.service.providers;

import com.bank.miasi.konta.typy.*;
import com.bank.miasi.service.Constants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Krzysztof
 */
public class AccountTypeProvider implements Provider<TypKonta> {

    private Map<String, TypKonta> typy = new HashMap<>();

    public AccountTypeProvider() {
        typy.put(Constants.KONTO_OPTYMALSNE, new KontoOptymalne());
        typy.put(Constants.KONTO_WYGODNE, new KontoWygodne());
        typy.put(Constants.LOKATA_OPTYMALSNA, new LokataOptymalna());
        typy.put(Constants.LOKATA_ROCZNA, new LokataRoczna());

    }

    public TypKonta getInstance(String name) {
        return typy.get(name);
    }

    public Collection<TypKonta> getList() {
        return typy.values();
    }
}
