package wintersprouts.boogie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BoogieApplication {
	public static void main(String[] args) {
		SpringApplication.run(BoogieApplication.class, args);
	}
}
