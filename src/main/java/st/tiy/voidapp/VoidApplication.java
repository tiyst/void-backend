package st.tiy.voidapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VoidApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoidApplication.class, args);
	}

}
