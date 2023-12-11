package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
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
public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accounRepository){

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
		};
}
}
