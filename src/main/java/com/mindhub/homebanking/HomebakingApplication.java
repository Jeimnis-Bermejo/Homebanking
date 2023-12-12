package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.mindhub.homebanking.repositories.ClientRepository;

import java.time.LocalDate;

@SpringBootApplication
public class HomebakingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebakingApplication.class, args);
	}
	@Bean
public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accounRepository ,TransactionRepository transactionRepository){

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
			System.out.println(accountMelba1);
			System.out.println(accountMelba2);

           accounRepository.save(accountMelba1);
           accounRepository.save(accountMelba2);
		   accounRepository.save(accountCarlos1);
			accounRepository.save(accountCarlos2);

			Transaction transaction1=new Transaction(TransactionType.DEBITO,3500.0,"Tes debito" ,LocalDate.now());
			Transaction transaction2=new Transaction(TransactionType.CREDIT,1500.0,"Tes credito" ,LocalDate.now());
			accountMelba1.addTransactions(transaction1);
			accountMelba2.addTransactions(transaction2);



			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			System.out.println(transaction1);
			System.out.println(transaction2);



 		};
}
}
