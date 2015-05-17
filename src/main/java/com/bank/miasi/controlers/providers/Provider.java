package com.bank.miasi.controlers.providers;


import java.util.Collection;
import java.util.Set;

public interface Provider<T> {
    T getInstance(String name);

    Collection<T> getList();

    Set getMapSet();

}
