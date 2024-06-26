package cabservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath:sql.xml" })
public class CabserviceprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabserviceprojectApplication.class, args);
	}

}
