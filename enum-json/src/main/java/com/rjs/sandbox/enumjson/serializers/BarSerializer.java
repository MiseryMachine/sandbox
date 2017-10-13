package com.rjs.sandbox.enumjson.serializers;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.rjs.sandbox.enumjson.model.Bar;

import java.io.IOException;

/**
 * @author Randy Strobel
 */
public class BarSerializer extends AbstractEnumSerializer<Bar> {
	public BarSerializer() {
		super(Enum.class);
	}

	@Override
	public void serializeFields(Bar bar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
		jsonGenerator.writeFieldName("numeric");
		jsonGenerator.writeNumber(bar.numeric);
	}
}
