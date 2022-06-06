package az.unibank.unitech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class UniTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniTechApplication.class, args);
	}

}
