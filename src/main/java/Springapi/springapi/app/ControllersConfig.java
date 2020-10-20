package Springapi.springapi.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ControllersConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/books").setViewName("books");
        registry.addViewController("/authors").setViewName("authors");
        registry.addViewController("/login").setViewName("login");
    }

}