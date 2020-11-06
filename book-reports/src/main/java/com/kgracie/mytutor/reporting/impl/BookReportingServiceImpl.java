package com.kgracie.mytutor.reporting.impl;

import com.kgracie.mytutor.reporting.api.BookReportingService;
import com.kgracie.mytutor.reporting.comparators.SummedTransactionComparator;
import com.kgracie.mytutor.reporting.domain.SummedTransaction;
import com.kgracie.mytutor.reporting.domain.SummedTransactionBuilder;
import com.kgracie.mytutor.sales.api.TransactionService;
import com.kgracie.mytutor.sales.domain.Transaction;
import com.kgracie.mytutor.sales.domain.TransactionType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class BookReportingServiceImpl implements BookReportingService {

    private final TransactionService transactionService;

    public BookReportingServiceImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public String generateReport() {
        List<Transaction> transactions = transactionService.retrieveTransactions();

        Map<String, List<Transaction>> groupedTransactions = transactions.stream()
                .collect(groupingBy(Transaction::title));

        StringBuilder builder = new StringBuilder();

        List<SummedTransaction> summedTransactions = groupedTransactions.entrySet().stream()
                .map(entry ->
                        SummedTransactionBuilder.newInstance()
                                .title(entry.getKey())
                                .numberOfCopiesSold(sumQuantitySold(entry.getValue()))
                                .totalProfit(sumTransactionAmount(entry.getValue()))
                                .build()
                ).sorted(new SummedTransactionComparator())
                .collect(Collectors.toList());

        summedTransactions.stream()
                .forEach(summed -> builder.append(
                        String.format("%s | %d Copies Sold | £%.2f Total Profit\n", summed.title(), summed.numberOfCopiesSold(), summed.totalProfit())));

        return builder.toString();
    }

    private int sumQuantitySold(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.transactionType() == TransactionType.SALE)
                .map(Transaction::quantity).reduce(0, Integer::sum);
    }

    private double sumTransactionAmount(List<Transaction> transactions) {
        return transactions.stream().map(Transaction::value).reduce(0.0, Double::sum);
    }

}
