package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dto.Transactiondto;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImplement implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public List<Transactiondto> getAllTransactionDTO() {
        return null;
    }

    @Override
    public void createTransaction(String description, Double amount, Account account) {
        TransactionType transactionType = amount > 0 ? TransactionType.CREDIT : TransactionType.DEBITO;
        Transaction transaction = new Transaction(transactionType, amount,
                description, LocalDate.now());
        account.addTransaction(transaction);
        transactionRepository.save(transaction);
        account.setBalance(account.getBalance() + amount);
    }
}

