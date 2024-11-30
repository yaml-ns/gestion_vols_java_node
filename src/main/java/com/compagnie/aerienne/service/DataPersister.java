package com.compagnie.aerienne.service;

import com.compagnie.aerienne.modele.Vol;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.List;

public class DataPersister {

    public static List<Vol> read() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = DataPersister.class.getResourceAsStream("/donnees/vols.json");
        return objectMapper.readValue(inputStream, new TypeReference<List<Vol>>() {});
    }

    public static void write(List<Vol> listeVols) throws IOException {
        String chemin = "src/main/resources/donnees/vols.json";

        OutputStream outputStream = new FileOutputStream(chemin);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(outputStream, listeVols);

    }

}
