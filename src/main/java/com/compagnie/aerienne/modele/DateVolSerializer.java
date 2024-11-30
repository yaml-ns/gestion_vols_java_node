package com.compagnie.aerienne.modele;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class DateVolSerializer extends JsonSerializer<DateVol> {
    @Override
    public void serialize(DateVol dateVol, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(String.format(
                "%02d/%02d/%04d",
                dateVol.getJour(),
                dateVol.getMois(),
                dateVol.getAn()
                )
        );
    }
    }

