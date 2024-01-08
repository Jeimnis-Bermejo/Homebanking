package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dto.LoanApplicationDTO;
import com.mindhub.homebanking.dto.LoanDTO;
import com.mindhub.homebanking.models.Loan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface LoanService {

    public List<Loan> getAllLoans();

    public List<LoanDTO> getAllLoanDTO();
    public Loan getLoanById(Long id);

    public LoanDTO getLoanDTOById(Long id);

    public ResponseEntity<String> createLoan(LoanApplicationDTO loanApplicationDTO, Authentication authentication);
}
