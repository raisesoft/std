package com.cd.bbh.common.enums;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JSONEnumSerializer<E extends Enum<E>> extends JsonSerializer<E> {

	@Override
	public void serialize(E value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
		ValueEnum valueEnum = (ValueEnum) value;
		gen.writeObject(valueEnum.desc());
	}
}
