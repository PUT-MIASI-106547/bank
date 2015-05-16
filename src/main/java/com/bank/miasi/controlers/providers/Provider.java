package com.bank.miasi.controlers.providers;

import java.util.Collection;

public interface Provider<T> {
    T getInstance(String name);

    Collection<T> getList();
}
