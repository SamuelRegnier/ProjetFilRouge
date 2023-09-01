package com.filrouge.ProjetFilRouge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication// c'est le point de depart et d'entrée de notre projet, 
//il va scanner tous les controllers et les services et faire les liens entre les couches
public class ProjetFilRougeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetFilRougeApplication.class, args);
	}

}
