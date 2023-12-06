package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.mindhub.homebanking.repositories.ClientRepository;

@SpringBootApplication
public class HomebakingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebakingApplication.class, args);
	}
	@Bean
public CommandLineRunner initData(ClientRepository clientRepository){

		return args -> {
			Client cliente = new Client("Melba","Morel", "melba@mindhub.com" );
			clientRepository.save(cliente);
			System.out.println(cliente);
		};
}
}
