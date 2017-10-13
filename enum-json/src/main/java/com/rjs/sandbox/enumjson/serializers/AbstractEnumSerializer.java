package com.rjs.sandbox.enumjson.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Randy Strobel
 */
public abstract class AbstractEnumSerializer<E extends Enum> extends AbstractSerializer<Enum, E> {
	public AbstractEnumSerializer(Class<Enum> t) {
		super(t);
	}

	@Override
	public void serialize(Enum e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeFieldName("enumName");
		jsonGenerator.writeString(e.name());
		jsonGenerator.writeFieldName("enumText");
		jsonGenerator.writeString(e.toString());
		serializeFields((E) e, jsonGenerator, serializerProvider);
		jsonGenerator.writeEndObject();
	}
}
