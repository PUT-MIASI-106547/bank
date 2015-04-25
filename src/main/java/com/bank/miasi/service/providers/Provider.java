package com.bank.miasi.service.providers;

import java.util.Collection;

/**
 * Created by Krzysztof on 24.04.2015.
 */
public interface Provider<T> {
    T getInstance(String name);

    Collection<T> getList();
}
