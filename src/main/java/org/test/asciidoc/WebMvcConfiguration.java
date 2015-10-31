package org.test.asciidoc;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
/**
 * Clase que configura Spring MVC en la aplicacion
 **/
@Configuration
@EnableWebMvc
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * Configura el {@link TemplateResolver} para que busque las plantillas de
	 * de Thymeleaf en el classpath bajo la carpeta "templates".
	 * <p/>
	 *
	 * uno que configure un {@link InternalResourceViewResolver} si se utilizan jsps.
	 * Ver el metodo comentado al final de la clase.
	 * @return el template resolver de Thymeleaf
	 **/
	@Bean
	public TemplateResolver springTemplateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("classpath:/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		//reload views on compile, good for development
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	/**
	 * Configura el motor de templates de Thymeleaf.
	 * <p/>
	 *
	 * uno que configure un {@link InternalResourceViewResolver} si se utilizan jsps.
	 * Ver el metodo comentado al final de la clase.
	 * @return el motor de templates de Thymeleaf
	 **/
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(springTemplateResolver());
		return engine;
	}

	/**
	 * Configura el view resolver de Thymeleaf.
	 * <p/>
	 *
	 * uno que configure un {@link InternalResourceViewResolver} si se utilizan jsps.
	 * Ver el metodo comentado al final de la clase.
 	 * @return el view resolver de Thymeleaf
	 */
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		return resolver;
	}

	/**
	 * Configura el gestor de recursos para que mapee las peticiones a las rutas
	 * "/static/**" a los ficheros ubicados bajo la carpeta "static" en el
	 * classpath.
	 * 
	 */
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		registry.addResourceHandler("/static/**")
		             .addResourceLocations("classpath:static/");
	}

	/**
	 * Configura el gestor de mapeos
	 *
	 * @return el RequestMappingHandlerMapping configurado para hacer
	 *         correspondencia exacta
	 */
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
		requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
		requestMappingHandlerMapping.setUseTrailingSlashMatch(false);
		return requestMappingHandlerMapping;
	}
	
	/**
	 * Configura el messages Resource
	 * @return una instancia del gestor de mensajes de la aplicacion.
	 */
	@Bean(name = "messageSource")
	public MessageSource configureMessageSource()
	{
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(5);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
