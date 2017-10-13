package com.rjs.sandbox.enumjson.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjs.sandbox.enumjson.serializers.SimpleEnumSerializer;

/**
 * @author Randy Strobel
 */
@JsonSerialize(using = SimpleEnumSerializer.class)
public enum Bar {
	ONE("One", 1),
	TWO("Two", 2),
	THREE("Three", 3);

	public final String value;
	public final int numeric;

	Bar(String value, int numeric) {
		this.value = value;
		this.numeric = numeric;
	}

	@JsonCreator
	public Bar forValue(@JsonProperty("enumText") String val) {
		for (Bar bar : Bar.values()) {
			if (bar.value.equals(val)) {
				return bar;
			}
		}

		return null;
	}

	@JsonValue
	@Override
	public String toString() {
		return value;
	}
}
