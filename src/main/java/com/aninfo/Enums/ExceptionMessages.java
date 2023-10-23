package com.aninfo.Enums;

public enum ExceptionMessages {

    InsufficientFunds("Insufficient funds"),
    DepositNegativeSum("Cannot deposit negative sums"),
    WithdrawNegativeSum("Cannot withdraw negative sums"),
    AccountNotFound("This account does not exist!"),
    TransactionNotFound("This transaction does not exist!");

    private String exceptionMessage;

    ExceptionMessages(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
