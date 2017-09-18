package com.rjs.sandbox.number.autogen;

import com.rjs.sandbox.number.autogen.parsers.AutoGenParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * @author Randy Strobel
 */
@SpringBootApplication
public class AutoGenApp implements CommandLineRunner {
	private static final String[] NUMERIC_ARRAY         = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	private static final String[] ALPHA_ARRAY           = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private static final String[] ALPHA_NUMERIC_ARRAY   = Stream.concat(Arrays.stream(ALPHA_ARRAY), Arrays.stream(NUMERIC_ARRAY)).toArray(String[]::new);
	private static final String[] NUMERIC_ALPHA_ARRAY   = Stream.concat(Arrays.stream(NUMERIC_ARRAY), Arrays.stream(ALPHA_ARRAY)).toArray(String[]::new);

	@Autowired
	@Qualifier("alphaNumericParser")
	private AutoGenParser alphaNumericParser;
	@Autowired
	@Qualifier("numericAlphaParser")
	private AutoGenParser numericAlphaParser;
	@Autowired
	@Qualifier("aToZParser")
	private AutoGenParser aToZParser;
	@Autowired
	@Qualifier("zeroToNineParser")
	private AutoGenParser zeroToNineParser;
	@Autowired
	private AutoGenExpressionUtil autoGenExpressionUtil;

	public AutoGenApp() {
	}

	@Bean
	public AutoGenParser alphaNumericParser() {
		return new AutoGenParser("[A-9]", ALPHA_NUMERIC_ARRAY);
	}

	@Bean
	public AutoGenParser numericAlphaParser() {
		return new AutoGenParser("[0-Z]", NUMERIC_ALPHA_ARRAY);
	}

	@Bean
	public AutoGenParser aToZParser() {
		return new AutoGenParser("[A-Z]", ALPHA_ARRAY);
	}

	@Bean
	public AutoGenParser zeroToNineParser() {
		return new AutoGenParser("[0-9]", NUMERIC_ARRAY);
	}

	@Override
	public void run(String... strings) throws Exception {
		Logger logger = Logger.getLogger("Auto-Gen Test");
		
		try {
			String expr = "{{[A-Z]x4}}-{{[0-9]x5}}";
			String value = "AAAA-99999";
			logger.info(value + " -> " + autoGenExpressionUtil.evaluate(expr, value));
			String value2 = "ZZZZ-99999";
			logger.info(value2 + " -> " + autoGenExpressionUtil.evaluate(expr, value2));
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "Error", e);
		}
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AutoGenApp.class, args);
	}
}
