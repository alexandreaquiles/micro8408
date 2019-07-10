package br.com.caelum.eats.distancia;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLogConfig {

	
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter log = new CommonsRequestLoggingFilter();
		log.setIncludeClientInfo(true);
		log.setIncludeQueryString(true);
		log.setIncludePayload(true);
		log.setIncludeHeaders(true);
		return log;
	}
	
}
