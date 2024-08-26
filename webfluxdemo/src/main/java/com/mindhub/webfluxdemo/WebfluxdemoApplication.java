package com.mindhub.webfluxdemo;

import com.mindhub.webfluxdemo.models.Item;
import com.mindhub.webfluxdemo.repositories.ItemRespository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebfluxdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ItemRespository itemRespository){
		return args -> {
			Item item = new Item("Cuaderno");
			itemRespository.save(item).subscribe();
			Item item2 = new Item("Lapiz");
			itemRespository.save(item2).subscribe();
			Item item3 = new Item("Esfero");
			itemRespository.save(item3).subscribe();
			Item item4 = new Item("Bloc de notas");
			itemRespository.save(item4).subscribe();
		};
	}
}
