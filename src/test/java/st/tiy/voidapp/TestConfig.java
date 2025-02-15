package st.tiy.voidapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

@Configuration
@TestPropertySource(properties = {
		"api.key=mock api key"
})
public class TestConfig {

	@Bean
	public String testApiKey() {
		return "mock api key";
	}
}
