package com.rjs.sandbox.javers1.config;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 8/9/2017.
 *
 * @author Randy Joe
 */
@Configuration
public class AppConfig {
	public AppConfig() {
	}

	@Bean
	public Javers javers() {
		return JaversBuilder.javers().build();
	}
}
