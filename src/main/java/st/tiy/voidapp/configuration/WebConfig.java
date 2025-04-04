package st.tiy.voidapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

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
