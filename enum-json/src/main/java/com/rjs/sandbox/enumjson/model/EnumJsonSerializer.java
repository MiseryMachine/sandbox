package com.rjs.sandbox.enumjson.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.rjs.sandbox.enumjson.serializers.ExtendedJsonSerializer;

import java.io.IOException;

/**
 * By default Jackson serializes enums by simply providing the enum value as a string.  This isn't ideal if enum values
 * are viewed by the user.  This serializer breaks out enums into two fields: <b>name</b> - the enum value (akin to the default
 * serializer) and <b>text</b> - which executes the enum's toString() method.  Clients of this serializer should override the
 * toString() method to provide a better UI representation of the enum.
 * <p/>
 * For example, an enum of colors serialized:<br/>
 * [<br/>
 *      &nbsp;&nbsp;&nbsp;&nbsp;{<br/>
 *          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "RED",<br/>
 *          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"text": "Red"<br/>
 *      &nbsp;&nbsp;&nbsp;&nbsp;},<br/>
 *      &nbsp;&nbsp;&nbsp;&nbsp;{<br/>
 *          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "GREEN",<br/>
 *          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"text": "Green"<br/>
 *      &nbsp;&nbsp;&nbsp;&nbsp;},<br/>
 *      &nbsp;&nbsp;&nbsp;&nbsp;{<br/>
 *          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "BLUE",<br/>
 *          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"text": "Blue"<br/>
 *      &nbsp;&nbsp;&nbsp;&nbsp;}<br/>
 * ]
 * @author Randy Strobel
 */
public class EnumJsonSerializer<T extends Enum<T>> extends StdSerializer<Enum> {
	protected ExtendedJsonSerializer<T> extendedJsonSerializer;

	public EnumJsonSerializer() {
		super(Enum.class);
	}

	public EnumJsonSerializer(ExtendedJsonSerializer<T> extendedJsonSerializer) {
		super(Enum.class);

		this.extendedJsonSerializer = extendedJsonSerializer;
	}

	@Override
	public void serialize(Enum e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeFieldName("name");
		jsonGenerator.writeString(e.name());
		jsonGenerator.writeFieldName("text");
		jsonGenerator.writeString(e.toString());

		if (extendedJsonSerializer != null) {
			extendedJsonSerializer.serializeFields((T) e, jsonGenerator, serializerProvider);
		}

		jsonGenerator.writeEndObject();
	}
}
