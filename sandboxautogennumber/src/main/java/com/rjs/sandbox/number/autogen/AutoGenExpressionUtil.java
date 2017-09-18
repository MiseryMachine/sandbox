package com.rjs.sandbox.number.autogen;

import com.rjs.sandbox.number.autogen.parsers.AutoGenParser;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Randy Strobel
 */
@Service("autoGenExpressionUtil")
public class AutoGenExpressionUtil {
	public static final String START_TAG_EXPR    = "\\{\\{";
	public static final String START_TAG    = "{{";
	public static final String END_TAG      = "}}";
	private static final String[] NUMERIC_ARRAY         = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	private static final String[] ALPHA_ARRAY           = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private static final String[] ALPHA_NUMERIC_ARRAY   = Stream.concat(Arrays.stream(ALPHA_ARRAY), Arrays.stream(NUMERIC_ARRAY)).toArray(String[]::new);
	private static final String[] NUMERIC_ALPHA_ARRAY   = Stream.concat(Arrays.stream(NUMERIC_ARRAY), Arrays.stream(ALPHA_ARRAY)).toArray(String[]::new);

	private Map<String, AutoGenParser> parserMap = new HashMap<>();
//	private Pattern pattern = Pattern.compile(EXPR_TOKEN);

	public AutoGenExpressionUtil() {
		parserMap.put("[0-9]", new AutoGenParser("[0-9]", NUMERIC_ARRAY));
		parserMap.put("[A-Z]", new AutoGenParser("[A-Z]", ALPHA_ARRAY));
		parserMap.put("[0-Z]", new AutoGenParser("[0-Z]", NUMERIC_ALPHA_ARRAY));
		parserMap.put("[A-9]", new AutoGenParser("[A-9]", ALPHA_NUMERIC_ARRAY));
	}

	public String evaluate(String pattern, String value) throws Exception {
		String[] split = pattern.split(START_TAG_EXPR);
		List<String[]> parts =  new ArrayList<>();
		List<String> dataParts = new ArrayList<>();
		int valIdx1 = 0;
		int valIdx2;
		StringBuilder sb = new StringBuilder();

		for (String aSplit : split) {
			int endIdx = aSplit.indexOf(END_TAG);

			if (endIdx >= 0) {
				String[] part = aSplit.substring(0, endIdx).split("x");
				parts.add(part);
				int len = Integer.parseInt(part[1]);
				valIdx2 = valIdx1 + len;
				dataParts.add(value.substring(valIdx1, valIdx2));
				sb.append("%s");
				String extra = aSplit.substring(endIdx + END_TAG.length());
				valIdx1 = valIdx2 + extra.length();
				sb.append(extra);
			}
			else {
				sb.append(aSplit);
			}
		}

		Object[] newValues = dataParts.toArray(new String[dataParts.size()]);

		for (int i = parts.size() - 1; i >= 0; i--) {
			String[] part = parts.get(i);
			String data = dataParts.get(i);
			AutoGenParser parser = parserMap.get(part[0]);

			if (parser != null) {
				String[] newValue = parser.increment(data, i > 0);

				newValues[i] = newValue[0];
				
				if (AutoGenParser.INCREMENTED.equals(newValue[1])) {
					break;
				}
			}
			else {
				throw new Exception("Auto number parser could not be found for expression; " + part[0]);
			}
		}

		return String.format(sb.toString(), newValues);
	}
}
