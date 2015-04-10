package com.bank.miasi.service.visitator;

import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.konta.Konto;
import com.bank.miasi.operacje.TypOperacji;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author inf106547
 */
public class ShortRaport implements Raport {

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
                .append("\n")
                .append("Właściciel:")
                .append(konto.getWlasciciel())
                .append("\n\n")
                .append("Pozycje:\n")
                .append("Data")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("Kwota")
                .append("\n");
        for (Row row : rows) {
            sb.append(row.getData().toLocaleString())
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
