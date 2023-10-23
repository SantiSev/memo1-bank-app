package com.aninfo.service;

import com.aninfo.Enums.ExceptionMessages;
import com.aninfo.Enums.TransactionTypes;
import com.aninfo.exceptions.AccountNotFoundException;
import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.exceptions.WithdrawNegativeSumException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    public Account createAccount(double balance) {
        if (balance < 0) {
            throw new WithdrawNegativeSumException(ExceptionMessages.WithdrawNegativeSum.getExceptionMessage());
        }
        Account account = new Account(balance);
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        try{accountRepository.deleteById(cbu);
        }catch (Exception e){
            throw new AccountNotFoundException(ExceptionMessages.AccountNotFound.getExceptionMessage());
        }
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (sum <= 0) {
            throw new WithdrawNegativeSumException(ExceptionMessages.WithdrawNegativeSum.getExceptionMessage());
        }else if (account.getBalance() < sum) {
            throw new InsufficientFundsException(ExceptionMessages.InsufficientFunds.getExceptionMessage());
        }

        Transaction transaction = new Transaction(account, TransactionTypes.WITHDRAWAL, sum * -1);
        account.getTransactions().add(transaction);
        account.setBalance(account.getBalance() - sum);

        transactionService.save(transaction);
        accountRepository.save(account);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException(ExceptionMessages.DepositNegativeSum.getExceptionMessage());
        }

        Account account = accountRepository.findAccountByCbu(cbu);
        account.setBalance(account.getBalance() + sum + bank_promo(sum));
        Transaction transaction = new Transaction(account, TransactionTypes.DEPOSIT, sum);
        account.getTransactions().add(transaction);


        transactionService.save(transaction);
        accountRepository.save(account);

        return account;
    }


    private double bank_promo(double sum){
        if (sum >= 2000){
            return sum * 0.10;
        }
        return 0;
    }

}
