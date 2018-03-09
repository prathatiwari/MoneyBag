package com.pratha.moneybag;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * class representing money and transactions
 */
public class MoneyBag {

    private static final LinkedHashMap<Double, Integer> MONEY_BAG;

    static {
        MONEY_BAG = new LinkedHashMap<>();
        MONEY_BAG.put(2000.0, 20);
        MONEY_BAG.put(1000.0, 20);
        MONEY_BAG.put(500.0, 20);
        MONEY_BAG.put(200.0, 20);
        MONEY_BAG.put(100.0, 20);
        MONEY_BAG.put(50.0, 20);
        MONEY_BAG.put(20.0, 20);
        MONEY_BAG.put(10.0, 20);
        MONEY_BAG.put(5.0, 20);
        MONEY_BAG.put(1.0, 20);
        MONEY_BAG.put(0.50, 20);
        MONEY_BAG.put(0.25, 20);
    }


    public HashMap<Double, Integer> getCashForAmount(Double amount) {

        LinkedHashMap<Double, Integer> result = new LinkedHashMap<>();

        for (Map.Entry<Double, Integer> entry : MONEY_BAG.entrySet()) {
            int count = (int) (amount / entry.getKey());

            if (count > 0) {
                if (count <= entry.getValue()) {
                    result.put(entry.getKey(), count);

                    //get remaining amount
                    amount = amount % entry.getKey();
                } else {
                    result.put(entry.getKey(), entry.getValue());

                    //get remaining amount
                    amount = (count - entry.getValue()) * entry.getKey()
                            + amount % entry.getKey();

                }
            }
        }

        if (amount > 0) {
            return null;
        }
        return result;
    }

    public String getCashInPrintableFormat(HashMap<Double, Integer> cash, String totalValueLabel) {
        int count = 0;
        double amount = 0;
        DecimalFormat valueFormatter = new DecimalFormat("00000.00");
        DecimalFormat countFormatter = new DecimalFormat("00");

        StringBuilder output = new StringBuilder();
        output.append("\n");
        for (Map.Entry<Double, Integer> entry : cash.entrySet()) {
            output.append(entry.getKey())
                    .append(" x ")
                    .append(countFormatter.format(entry.getValue()))
                    .append(" = ")
                    .append(valueFormatter.format(entry.getValue() * entry.getKey()))
                    .append("\n");

            count += entry.getValue();
            amount += (entry.getValue() * entry.getKey());
        }

        output.append("\n" + totalValueLabel + ":  ")
                .append(countFormatter.format(count))
                .append(" :: ").append(valueFormatter.format(amount));

        return output.toString();
    }
}
