package com.rjs.sandbox.enumjson.model;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author Randy Strobel
 */
public abstract class ExtendedEnumJsonSerializer<T extends Enum<T>> extends StdSerializer<Enum> {
	public ExtendedEnumJsonSerializer(Class<Enum> t) {
		super(t);
	}

	@Override
	public void serialize(Enum e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeFieldName("name");
		jsonGenerator.writeString(e.name());
		jsonGenerator.writeFieldName("text");
		jsonGenerator.writeString(e.toString());
		jsonGenerator.writeEndObject();
	}

	/**
	 * Override this to add serialization for any other fields in the enum.
	 * @param t                  The enum.
	 * @param jsonGenerator      JSON generator.
	 * @param serializerProvider Serializing provider.
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void serializeFields(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {

	}
}
