package cl.ccastillo.recyclothes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import cl.ccastillo.recyclothes.property.FileStorageProperties;

@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class RecyclothesProductosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RecyclothesProductosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("recyclothes-rest-producto ---  Servidor Listo");
		
	}

}
