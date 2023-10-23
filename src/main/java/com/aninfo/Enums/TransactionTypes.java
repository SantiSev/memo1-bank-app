package com.aninfo.Enums;

public enum TransactionTypes {
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal");

    private String transactionType;

    TransactionTypes (String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionType() {
        return transactionType;
    }
}

