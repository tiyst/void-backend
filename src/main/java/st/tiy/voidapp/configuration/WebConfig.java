package st.tiy.voidapp.configuration;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Setter
@Configuration
@EnableWebMvc
@ConfigurationProperties(prefix = "void-cors")
public class WebConfig implements WebMvcConfigurer {

	private List<String> allowedOrigins;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
		        .allowedOrigins(allowedOrigins.toArray(new String[0]))
		        .allowedMethods("GET", "POST")
		        .allowedHeaders("*")
		        .exposedHeaders("*")
		        .allowCredentials(true)
		        .maxAge(3600);
	}

}
