package com.compagnie.aerienne.modele;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class DateVolDeserializer extends JsonDeserializer<DateVol> {
    @Override
    public DateVol deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            String date = jsonParser.getValueAsString();
            String[] elems = date.split("/");
            int jour = Integer.parseInt(elems[0]);
            int mois = Integer.parseInt(elems[1]);
            int annee = Integer.parseInt(elems[2]);

            return new DateVol(jour, mois, annee);
    }
}
