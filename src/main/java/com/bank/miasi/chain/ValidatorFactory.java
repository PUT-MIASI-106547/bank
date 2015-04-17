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
public class ValidatorFactory {

    private static ChainValidator validator;

    public static ChainValidator getValidator() {
        if (validator == null) {
            initValidators();
        }
        return validator;
    }

    private static void initValidators() {
        SeryjnyPrzelew seryjnyPrzelew = new SeryjnyPrzelew(null, 4, 10);
        SprawdzKwote sprawdzKwote = new SprawdzKwote(seryjnyPrzelew, BigDecimal.valueOf(10000L));
        ZglosKwote zglosKwote = new ZglosKwote(sprawdzKwote, BigDecimal.valueOf(5000L));
        validator = zglosKwote;
    }
}
