package com.aninfo.service;

import com.aninfo.Enums.ExceptionMessages;
import com.aninfo.exceptions.AccountNotFoundException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }


    public Collection<Transaction> findAllByAccount(Optional<Account> account) {
        if (account.isEmpty()) {
            throw new AccountNotFoundException(ExceptionMessages.AccountNotFound.getExceptionMessage());
        }
        return transactionRepository.findAllByAccount(account.get());
    }

}
