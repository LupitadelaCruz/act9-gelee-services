package com.gelee.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Clase de configuracion para agregar recursos estaticos
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Configuration
@ComponentScan("com.gelee.api")
@EnableWebMvc
@EnableAsync
public class WebMvcConfig  implements WebMvcConfigurer{
	
	 @Override
	    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

	        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }

	    @Bean
	    public ViewResolver viewResolver() {
	        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setPrefix("/WEB-INF/view/");
	        viewResolver.setSuffix(".jsp");
	        return viewResolver;
	    }

	    @Override
	    public void addViewControllers(final ViewControllerRegistry registry) {
	        registry.addViewController("/csrfAttacker.html");
	    }

}
