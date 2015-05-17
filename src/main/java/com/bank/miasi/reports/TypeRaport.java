package com.bank.miasi.reports;

import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.Kontable;
import com.bank.miasi.model.operacje.OperationType;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TypeRaport implements Raport {

    private List<Row> rows = new ArrayList<>();
    private Kontable konto;
    private OperationType operationType;

    public TypeRaport(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public void addRow(OperacjaBankowa operacja) {
        Row row = new Row(operacja);
        if (operacja.getOperationType().getClass().equals(operationType.getClass())) {
            rows.add(row);
        }
    }

    public void addHeader(Kontable konto) {
        this.konto = konto;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Raport dla konta ")
                .append(konto.getNumer())
                .append("\nWłaściciel:");
        konto.getWlasciciel().printKlientInfo(sb);
        sb.append("\nTyp:")
                .append(operationType.getName())
                .append("\n\nPozycje:\n")
                .append("Data")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("Od Kogo")
                .append("\t")
                .append("\t")
                .append("Do Kogo")
                .append("\t")
                .append("\t")
                .append("Kwota")
                .append("\n");
        for (Row row : rows) {
            sb.append(DateFormat.getDateTimeInstance().format(row.getData()))
                    .append("\t")
                    .append(row.getOdKogo())
                    .append("\t")
                    .append(row.getDoKogo())
                    .append("\t")
                    .append(row.getKwota())
                    .append("\n");
        }
        System.out.println(sb.toString());
    }

    private static class Row {

        private OperacjaBankowa operacja;

        public Row(OperacjaBankowa operacja) {
            this.operacja = operacja;
        }

        Date getData() {
            return operacja.getData();
        }

        Kontable getOdKogo() {
            return operacja.getOdKogo();
        }

        Kontable getDoKogo() {
            return operacja.getDoKogo();
        }

        BigDecimal getKwota() {
            return operacja.getKwota();
        }

        String getTytul() {
            return operacja.getTytul();
        }
    }
}
