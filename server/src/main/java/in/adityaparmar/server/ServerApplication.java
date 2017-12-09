package in.adityaparmar.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAutoConfiguration
public class ServerApplication {

	@Bean
	WebMvcConfigurer configurer () {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addResourceHandlers (ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/pages/**").
						addResourceLocations("classpath:/Users/adityaparmar/GitHub/Dropbox-v3.0/server/src/main/resources/static/");
			}
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}


