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
public class AllRaport implements Raport {

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
                .append("\nWłaściciel:");
        konto.getWlasciciel().printKlientInfo(sb);
        sb.append("\n\nPozycje:\n")
                .append("Data")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("Od Kogo")
                .append("\t")
                .append("\t")
                .append("DoKogo")
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

        String getOdKogo() {
            Kontable odKogo = operacja.getOdKogo();
            if (odKogo == null) {
                return "Nieznany";
            }
            return odKogo.toString();
        }

        String getDoKogo() {
            Kontable doKogo = operacja.getDoKogo();
            if (doKogo == null) {
                return "Nieznany";
            }
            return doKogo.toString();
        }

        BigDecimal getKwota() {
            return operacja.getKwota();
        }

        String getTytul() {
            return operacja.getTytul();
        }
    }
}
