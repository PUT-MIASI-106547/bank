/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.miasi.chain;

import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class ValidatorFactoryImpl implements ValidatorFactory {

    private ChainValidator validator;

    public ChainValidator getFirstValidator() {
        if (validator == null) {
            initValidators();
        }
        return validator;
    }

    private void initValidators() {
        SeryjnyPrzelew seryjnyPrzelew = new SeryjnyPrzelew(null, 4, 10);
        SprawdzKwote sprawdzKwote = new SprawdzKwote(seryjnyPrzelew, BigDecimal.valueOf(10000L));
        validator = new ZglosKwote(sprawdzKwote, BigDecimal.valueOf(5000L));
    }
}
