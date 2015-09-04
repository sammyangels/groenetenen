package be.vdab.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan
@EnableSpringDataWebSupport
public class CreateControllerBeans extends WebMvcConfigurerAdapter {

    @Bean
    LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieMaxAge(604800);
        return resolver;
    }

    @Bean
	InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/JSP/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:teksten");
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    @Bean
    LocalValidatorFactoryBean validatorFactory() {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setValidationMessageSource(messageSource());
        return factory;
    }

    @Override
    public Validator getValidator() {
        return new SpringValidatorAdapter(validatorFactory().getValidator());
    }

    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/images/**")
	.addResourceLocations("/images/"); 
	registry.addResourceHandler("/styles/**").addResourceLocations("/styles/");
	registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/");
	}

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/info").setViewName("info");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }
}
