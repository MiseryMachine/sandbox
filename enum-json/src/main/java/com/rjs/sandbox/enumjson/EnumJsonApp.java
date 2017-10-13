package com.rjs.sandbox.enumjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjs.sandbox.enumjson.model.Bar;
import com.rjs.sandbox.enumjson.model.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

/**
 * @author Randy Strobel
 */
@SpringBootApplication
public class EnumJsonApp implements CommandLineRunner {
	@Autowired
	private ObjectMapper objectMapper;

	public EnumJsonApp() {
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Override
	public void run(String... strings) throws Exception {
		Logger logger = Logger.getLogger("Enum-Json Test");
		Foo foo = new Foo("Randy", Bar.ONE);
		foo.setDescription("Testing Randy.");
		logger.info("Test 1:  " + objectMapper.writeValueAsString(foo));

		String testJson = "{\"name\": \"David\", \"bar\": {\"enumName\": \"THREE\", \"enumText\": \"Three\"}";
		foo = objectMapper.readValue(testJson, Foo.class);
		logger.info("Test 2:  " + foo);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(EnumJsonApp.class, args);
	}
}
