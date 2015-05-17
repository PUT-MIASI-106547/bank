package com.bank.miasi.controlers.providers;

import com.bank.miasi.model.operacje.*;
import com.bank.miasi.Constants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Krzysztof
 */
public class OperationTypeProvider implements Provider<OperationType> {

    private Map<String, OperationType> typy = new HashMap<>();

    public OperationTypeProvider() {
        typy.put(Constants.WPLATA, new Wplata());
        typy.put(Constants.WYPLATA, new Wyplata());
        typy.put(Constants.PRZELEW_PRZYCHODZACY, new PrzelewPrzychodzacy());
        typy.put(Constants.PRZELEW_WYCHODZACY, new PrzelewWychodzacy());

    }

    public OperationType getInstance(String name) {
        return typy.get(name);
    }

    public Collection<OperationType> getList() {
        return typy.values();
    }
    @Override
    public Set getMapSet() {
        return typy.entrySet();
    }
}
