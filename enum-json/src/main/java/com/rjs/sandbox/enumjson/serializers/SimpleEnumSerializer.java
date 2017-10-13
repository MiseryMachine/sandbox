package com.rjs.sandbox.enumjson.serializers;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Randy Strobel
 */
public class SimpleEnumSerializer<E extends Enum> extends AbstractEnumSerializer<E> {
	public SimpleEnumSerializer() {
		super(Enum.class);
	}

	@Override
	public void serializeFields(E e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
	}
}
