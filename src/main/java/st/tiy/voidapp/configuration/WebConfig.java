package st.tiy.voidapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import st.tiy.voidapp.exception.MissingApiKeyException;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public String apiKey(@Value("${api.key}") String apiKey) {
		if (isBlank(apiKey)) {
			throw new MissingApiKeyException();
		}

		return apiKey;
	}

	@Bean
	public WebClient webClient(String apiKey) {
		return WebClient.builder()
		                .defaultHeader("X-Riot-Token", apiKey)
		                .build();
	}

	@Deprecated
	@Bean
	public RestTemplate restTemplate(String apiKey) {
		RestTemplate restTemplate = new RestTemplate();

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(((request, body, execution) -> {
			request.getHeaders().add("X-Riot-Token", apiKey);
			return execution.execute(request, body);
		}));
		restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
		        .allowedOrigins(
				        "http://localhost:5173",
				        "https://tiy.st"
		        )
		        .allowedMethods("GET", "POST")
		        .allowedHeaders("*")
		        .exposedHeaders("*")
		        .allowCredentials(true)
		        .maxAge(3600);
	}
}
