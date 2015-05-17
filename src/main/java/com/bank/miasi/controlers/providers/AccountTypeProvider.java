package com.bank.miasi.controlers.providers;

import com.bank.miasi.model.konta.typy.*;
import com.bank.miasi.Constants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Krzysztof
 */
public class AccountTypeProvider implements Provider<AccountType> {

    private Map<String, AccountType> typy = new HashMap<>();

    public AccountTypeProvider() {
        typy.put(Constants.KONTO_OPTYMALSNE, new KontoOptymalne());
        typy.put(Constants.KONTO_WYGODNE, new KontoWygodne());
        typy.put(Constants.LOKATA_OPTYMALSNA, new LokataOptymalna());
        typy.put(Constants.LOKATA_ROCZNA, new LokataRoczna());

    }

    public AccountType getInstance(String name) {
        return typy.get(name);
    }

    public Collection<AccountType> getList() {
        return typy.values();
    }

    public Set getMapSet() {
        return typy.entrySet();
    }
}
