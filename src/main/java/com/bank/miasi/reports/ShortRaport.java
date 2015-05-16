package com.bank.miasi.reports;

import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.Kontable;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author inf106547
 */
public class ShortRaport implements Raport {

    private List<Row> rows = new ArrayList<>();
    private Kontable konto;

    @Override
    public void addRow(OperacjaBankowa operacja) {
        Row row = new Row(operacja);
        rows.add(row);
    }

    public void addHeader(Kontable konto) {
        this.konto = konto;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Raport dla konta ")
                .append(konto.getNumer())
                .append("\n")
                .append("Właściciel:")
        ;
        konto.getWlasciciel().printKlientInfo(sb);
        sb.append("\n\n")
                .append("Pozycje:\n")
                .append("Data")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("Kwota")
                .append("\n");
        for (Row row : rows) {
            sb.append(DateFormat.getDateInstance().format(row.getData()))
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

        BigDecimal getKwota() {
            return operacja.getKwota();
        }

        String getTytul() {
            return operacja.getTytul();
        }
    }
}
