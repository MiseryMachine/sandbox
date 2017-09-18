package com.rjs.sandbox.number.autogen.parsers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Randy Strobel
 */
public class AutoGenParser {
	public static final String ROLLED_OVER  = "rolled_over";
	public static final String INCREMENTED  = "incremented";

	private final String label;
	private final List<String> chars;
	private final String minValue;
	private final String maxValue;

	public AutoGenParser(String label, String[] chars) {
		this.label = label;
		minValue = chars[0];
		maxValue = chars[chars.length - 1];

		this.chars = Arrays.asList(chars);
	}

	public String getLabel() {
		return label;
	}

	public String initialValue(int len) {
		if (len <= 0) {
			return "";
		}

		return String.join("", Collections.nCopies(len, minValue));
	}

	public boolean isMax(String current) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < current.length(); i++) {
			String ch = current.substring(i, i + 1);

			if (chars.contains(ch)) {
				sb.append(maxValue);
			}
			else {
				sb.append(ch);
			}
		}

		return sb.toString().equals(current);
	}

	public String increment(String current) throws Exception {
		return increment(current, false)[0];
	}

	public String[] increment(String current, boolean rollover) throws Exception {
		if (current == null) {
			throw new IllegalArgumentException("Current number is null.");
		}

		boolean isMax = isMax(current);

		if (!rollover) {
			if (isMax) {
				throw new IndexOutOfBoundsException("Auto-gen value is currently at maximum and cannot be incremented: " + current);
			}

			return new String[] {addOne(current), INCREMENTED};
		}
		else {
			return isMax ? new String[] {initialValue(current.length()), ROLLED_OVER} : new String[] {addOne(current), INCREMENTED};
		}
	}

	private String addOne(String valStr) {
		if (valStr.length() == 0) {
			return valStr;
		}

		int lastIdx = valStr.length() - 1;
		String lastChar = valStr.substring(lastIdx);
		int idx = chars.indexOf(lastChar) + 1;

		if (idx == 0) {
			// Char not part of sequence, skip it.
			return addOne(valStr.substring(0, lastIdx)) + valStr.substring(lastIdx);
		}
		else if (idx < chars.size()) {
			return valStr.substring(0, lastIdx) + chars.get(idx);
		}
		else {
			return addOne(valStr.substring(0, lastIdx)) + minValue;
		}
	}
}
