package com.mufeng.StreamAPI.stream;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/**
 * 从一个列表中筛选金额较高的交易，然后按货币分组
 * Created by mufeng on 2017/12/22.
 */
public class SQLstyle {
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        //1.传统做法
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getPrice() > 1000) {
                Currency currency = transaction.getCurrency();
                List<Transaction> transactionsForCurrency =
                        transactionsByCurrencies.get(currency);
                if (transactionsForCurrency == null) {
                    transactionsForCurrency = new ArrayList<>();
                    transactionsByCurrencies.put(currency,
                            transactionsForCurrency);
                }
                transactionsForCurrency.add(transaction);
            }
        }
        //2.Steam 方式
        transactionsByCurrencies = transactions.stream()
                .filter(t -> t.getPrice() > 1000)
                .collect(groupingBy(Transaction::getCurrency));
    }

    private static class Currency {
    }

    private static class Transaction {
        private int price;
        private Currency currency;

        public int getPrice() {
            return price;
        }

        public Currency getCurrency() {
            return currency;
        }
    }
}
