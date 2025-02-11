package com.example.realtime_chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RealtimeChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealtimeChatApplication.class, args);
	}

}
