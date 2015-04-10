package com.bank.miasi.service.visitator;

import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.konta.Konto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author inf106547
 */
public class AllRaport implements Raport {

    private List<Row> rows = new ArrayList<>();
    private Konto konto;

    @Override
    public void addRow(OperacjaBankowa operacja) {
        Row row = new Row(operacja);
        rows.add(row);
    }

    public void addHeader(Konto konto) {
        this.konto = konto;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Raport dla konta ")
                .append(konto.getNumer())
                .append("\nWłaściciel:")
                .append(konto.getWlasciciel());
        sb.append("\n\nPozycje:\n");
        sb.append("Data")
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
            sb.append(row.getData().toLocaleString())
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
            Konto odKogo = operacja.getOdKogo();
            if (odKogo == null) {
                return "Nieznany";
            }
            return odKogo.toString();
        }

        String getDoKogo() {
            Konto doKogo = operacja.getDoKogo();
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
