package org.devs.teamcoll.Teamcoll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TeamcollApplication {
	public static void main(String[] args) {
		SpringApplication.run(TeamcollApplication.class, args);
	}
}
