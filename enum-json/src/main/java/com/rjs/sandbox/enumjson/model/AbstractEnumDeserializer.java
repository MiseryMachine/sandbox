package com.rjs.sandbox.enumjson.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Randy Strobel
 */
public class AbstractEnumDeserializer extends StdDeserializer<Enum> {
	public AbstractEnumDeserializer() {
		super(Enum.class);
	}

	@Override
	public Enum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		Logger logger = Logger.getLogger("Enum-Json Test");

		logger.info("value class = " + this._valueClass.getName());
		logger.info("value class = " + this.handledType().getName());

		JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		return null;
	}
}
