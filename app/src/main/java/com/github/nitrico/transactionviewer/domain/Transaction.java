package com.github.nitrico.transactionviewer.domain;

import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.Locale;

public class Transaction implements Serializable {

    public final double amount;
    public final String sku;
    public final String currency;

    public Transaction(@NonNull String sku, double amount, @NonNull String currency) {
        this.sku = sku;
        this.amount = amount;
        this.currency = currency;
    }

    public double toDefaultCurrency() {
        if (currency.equals(DataManager.DEFAULT_CURRENCY)) {
            return amount;
        } else {
            return amount * DataManager.getInstance().getConversionTree().getConversionRate(currency);
        }
    }

    @Override
    public String toString() {
        return getCurrencySymbol() + " " + String.format(Locale.ENGLISH, "%,.2f", amount);
    }

    public String toDefaultCurrencyString() {
        return DataManager.DEFAULT_CURRENCY_SYMBOL + " " + String.format(Locale.ENGLISH, "%,.2f", toDefaultCurrency());
    }

    private String getCurrencySymbol() {
        switch (currency) {
            case "AUD": return "A$";
            case "CAD": return "CA$";
            case "EUR": return "€";
            case "GBP": return "£";
            case "USD": return "$";
        }
        return currency;
    }

}