package com.rjs.sandbox.enumjson.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author Randy Strobel
 */
public class Foo implements Serializable {
	private final String name;
	private final Bar bar;
	private String description;

	@JsonCreator
	public Foo(@JsonProperty("name") String name, @JsonProperty("bar") Bar bar) {
		this.name = name;
		this.bar = bar;
	}

	public String getName() {
		return name;
	}

	public Bar getBar() {
		return bar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Foo {");
		sb.append("\n\tName: ").append(name);
		sb.append("\n\tBar: ").append(bar);

		if (description != null) {
			sb.append("\n\tDescription: ").append(description);
		}

		sb.append("\n}");

		return sb.toString();
	}
}
