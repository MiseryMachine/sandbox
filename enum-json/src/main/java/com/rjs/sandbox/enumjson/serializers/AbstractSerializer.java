package com.rjs.sandbox.enumjson.serializers;

import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author Randy Strobel
 */
public abstract class AbstractSerializer<T, E extends T> extends StdSerializer<T> implements ExtendedJsonSerializer<E> {
	public AbstractSerializer(Class<T> t) {
		super(t);
	}
}
