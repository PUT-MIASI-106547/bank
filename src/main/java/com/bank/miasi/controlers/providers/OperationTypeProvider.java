package com.bank.miasi.controlers.providers;

import com.bank.miasi.model.operacje.*;
import com.bank.miasi.Constants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Krzysztof
 */
public class OperationTypeProvider implements Provider<TypOperacji> {

    private Map<String, TypOperacji> typy = new HashMap<>();

    public OperationTypeProvider() {
        typy.put(Constants.WPLATA, new Wplata());
        typy.put(Constants.WYPLATA, new Wyplata());
        typy.put(Constants.PRZELEW_PRZYCHODZACY, new PrzelewPrzychodzacy());
        typy.put(Constants.PRZELEW_WYCHODZACY, new PrzelewWychodzacy());

    }

    public TypOperacji getInstance(String name) {
        return typy.get(name);
    }

    public Collection<TypOperacji> getList() {
        return typy.values();
    }
}
