package org.ibs;

import org.ibs.domain.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses= PersonRepository.class)
public class EmployeeDepServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDepServiceApplication.class, args);
	}

}
