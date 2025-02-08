package st.tiy.budgetopgg.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.exception.MissingApiKeyException;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Configuration
public class OpGgConfiguration {

	@Bean
	public String apiKey(@Value("${api.key}") String apiKey) {
		if (isBlank(apiKey)) {
			throw new MissingApiKeyException();
		}

		return apiKey;
	}

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

}
