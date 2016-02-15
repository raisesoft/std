package com.cd.bbh.common.enums;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JSONCollectionSerializer extends JsonSerializer<Collection<?>> {

	@Override
	public void serialize(Collection<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
		if (value.size() <= 0) {
			gen.writeObject(null);
		} else {
			gen.writeObject(value);
		}
	}
}
