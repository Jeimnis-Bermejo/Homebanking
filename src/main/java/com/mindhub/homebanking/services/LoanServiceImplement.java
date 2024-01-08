package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dto.LoanApplicationDTO;
import com.mindhub.homebanking.dto.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.services.LoanService;
import com.mindhub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImplement implements LoanService {

    @Autowired
    LoanRepository loanRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ClientLoanRepository clientLoanRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionService transactionService;

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public List<LoanDTO> getAllLoanDTO() {
        return getAllLoans().stream().map(LoanDTO::new).collect(Collectors.toList());
    }

    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    @Override
    public LoanDTO getLoanDTOById(Long id) {
        return new LoanDTO(getLoanById(id));
    }

    @Override
    public ResponseEntity<String> createLoan(LoanApplicationDTO loanApplicationDTO,
                                             Authentication authentication) {
        Loan loan = getLoanById(loanApplicationDTO.getId());
        Client client = clientRepository.findByEmail(authentication.getName());
        Account account = accountRepository.findByNumber(loanApplicationDTO.getAccountNumber());
        Double amount = loanApplicationDTO.getAmount();

        if (amount <= 0) {
            return new ResponseEntity<>("The amount must be greater than 0", HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getPayments() <= 0) {
            return new ResponseEntity<>("The payments must be greater than 0", HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getAccountNumber().isBlank()) {
            return new ResponseEntity<>("The account number cannot be empty", HttpStatus.FORBIDDEN);
        }
        if (!loanRepository.existsById(loanApplicationDTO.getId())) {
            return new ResponseEntity<>("The loan does not exist", HttpStatus.FORBIDDEN);
        }
        assert loan != null;
        if (!loan.getPayments().contains(loanApplicationDTO.getPayments())) {
            return new ResponseEntity<>("Invalid payments amount", HttpStatus.FORBIDDEN);
        }
        if (amount > loan.getMaxAmount()) {
            return new ResponseEntity<>("Loan exceeds the maximum amount. The maximum amount is " + loan.getMaxAmount(), HttpStatus.FORBIDDEN);
        }
        if (!accountRepository.existsByNumber(loanApplicationDTO.getAccountNumber())) {
            return new ResponseEntity<>("The account does not exist", HttpStatus.FORBIDDEN);
        }
        if (!client.getAccount().contains(accountRepository.findByNumber(loanApplicationDTO.getAccountNumber()))) {
            return new ResponseEntity<>("The account does not belong to the client", HttpStatus.FORBIDDEN);
        }

        ClientLoan loanApplication = new ClientLoan(amount * 1.2, loanApplicationDTO.getPayments());
        loan.addClientLoan(loanApplication);
        client.addClientLoan(loanApplication);
        clientLoanRepository.save(loanApplication);

        transactionService.createTransaction(loan.getName() + " loan approved", amount, account);
        return new ResponseEntity<String>("Loan created successfully!", HttpStatus.CREATED);
    }


}

