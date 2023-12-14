package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class HomebakingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebakingApplication.class, args);
	}
	@Bean
public CommandLineRunner initData(ClientRepository clientRepository,
								  AccountRepository accounRepository ,
								  TransactionRepository transactionRepository,
								  LoanRepository loanRepository,
								  ClientLoanRepository clientLoanRepository){

		return args -> {
			Client client1 = new Client("Melba","Morel", "melba@mindhub.com" );
			Client client2 = new Client("Carlos Mario", "Diaz","CarlosMario89@gmail.com");

			clientRepository.save(client1);
			clientRepository.save(client2);
			System.out.println(client1);
			System.out.println(client2);




			Account accountMelba1= new Account("VIN001",5000.0 , LocalDate.now());
			Account accountMelba2= new Account("VIN002",7500.0 , LocalDate.now().plusDays(1));
			Account accountCarlos1= new Account("VIN003",15000.0 , LocalDate.now());
			Account accountCarlos2= new Account("VIN004",5500.0 , LocalDate.now().plusDays(1));
			client1.addAcount(accountMelba1);
			client1.addAcount(accountMelba2);
			client2.addAcount(accountCarlos1);
			client2.addAcount(accountCarlos2);


           accounRepository.save(accountMelba1);
           accounRepository.save(accountMelba2);
		   accounRepository.save(accountCarlos1);
			accounRepository.save(accountCarlos2);

			Transaction transaction1=new Transaction(TransactionType.DEBITO,-3500.0,"Market" ,LocalDate.now());
			Transaction transaction2=new Transaction(TransactionType.CREDIT,1500.0,"Tes Credito" ,LocalDate.now());
			Transaction transaction3=new Transaction(TransactionType.DEBITO,-4500.0,"Tes Debito" ,LocalDate.now());
			Transaction transaction4=new Transaction(TransactionType.CREDIT,2500.0,"Tes Credito" ,LocalDate.now());

			accountMelba1.addTransactions(transaction1);
			accountMelba1.addTransactions(transaction2);
			accountMelba2.addTransactions(transaction3);
			accountMelba2.addTransactions(transaction4);

			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);

			Loan loan1=new Loan("Mortgage",500000.0, List.of(12,24,36,48,60));
			Loan loan2=new Loan("Staff",100000.0, List.of(6,12,24));
			Loan loan3=new Loan("Automotive ",300000.0, List.of(12,24,36));

			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);

			ClientLoan client1loan =new ClientLoan(400000.0, 60);
			ClientLoan client2loan= new ClientLoan(50000.0 ,12 );
			ClientLoan client3loan =new ClientLoan(100000.0, 24);
			ClientLoan client4loan= new ClientLoan(200000.0 ,36 );


			client1.addClientLoan(client1loan);
			loan1.addClientLoan(client1loan);
			client1.addClientLoan(client2loan);
			loan2.addClientLoan(client2loan);

			client2.addClientLoan(client3loan);
			loan2.addClientLoan(client3loan);
			client2.addClientLoan(client4loan);
			loan3.addClientLoan(client4loan);



            clientLoanRepository.save(client1loan);
            clientLoanRepository.save(client2loan);
            clientLoanRepository.save(client3loan);
			clientLoanRepository.save(client4loan);


		};
}
}
