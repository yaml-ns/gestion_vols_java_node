package com.compagnie.aerienne.service;

import com.compagnie.aerienne.modele.Vol;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GestionVolService {

    public List<Vol> listeVols;
    private final String API_VOLS_URL = "http://127.0.0.1:3000/api/v1/vols/";

    public GestionVolService() throws IOException {
        listeVols = DataPersister.read();
    }

    public List<Vol> getAll() throws IOException {
        URL url = new URL(API_VOLS_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = bufferedReader.readLine()) !=null){
            content.append(line);
        }
        bufferedReader.close();
        connection.disconnect();
        ObjectMapper om = new ObjectMapper();
        return om.readValue(content.toString(), new TypeReference<List<Vol>>() {});
    }

    public Vol getVol(int id){
        try {
            URL url = new URL(API_VOLS_URL + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = bufferedReader.readLine()) !=null){
                content.append(line);
            }
            bufferedReader.close();
            connection.disconnect();
            ObjectMapper om = new ObjectMapper();
            return om.readValue(content.toString(), Vol.class);
        }catch (Exception e){
            return null;
        }

    }

    public Vol addVol(Vol vol) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInputString = objectMapper.writeValueAsString(vol);

        URL url = new URL(API_VOLS_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(jsonInputString);
            outputStream.flush();


        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_OK) {

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            return objectMapper.readValue(response.toString(), Vol.class);

        } else {
            return null;
        }

    }

    public Vol updateVol(Vol volToUpdate) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInputString = objectMapper.writeValueAsString(volToUpdate);
        URL url = new URL(API_VOLS_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream();
             OutputStreamWriter writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            writer.write(jsonInputString);
            writer.flush();
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream(),
                            StandardCharsets.UTF_8
                    )
            )) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }

                return objectMapper.readValue(response.toString(), Vol.class);
            }
        } else {
            return null;
        }
    }


    public boolean deleteVol(int id) throws IOException {

        URL url = new URL(API_VOLS_URL + id);
        System.out.println(url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);
        connection.disconnect();
        return responseCode == HttpURLConnection.HTTP_OK;

    }

    public boolean addReservation(Vol vol, int reservations) throws IOException {
        String urlApi = API_VOLS_URL + vol.getIdVol() + "/reserver/" + reservations;
        URL url = new URL(urlApi);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_OK;

    }
}
