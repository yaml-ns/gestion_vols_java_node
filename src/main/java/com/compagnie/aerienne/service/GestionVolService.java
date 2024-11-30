package com.compagnie.aerienne.service;

import com.compagnie.aerienne.modele.Vol;

import java.io.IOException;
import java.util.List;

public class GestionVolService {

    public List<Vol> listeVols;

    public GestionVolService() throws IOException {
        listeVols = DataPersister.read();
    }

    public List<Vol> getAll(){
        return listeVols;
    }

    public Vol getVol(int id){
        for (Vol vol : listeVols){
            if (vol.getIdVol() == id) return vol;
        }
        return null;
    }

    public void addVol(Vol vol) throws IOException {
        listeVols.add(vol);
        DataPersister.write(listeVols);
    }

    public void updateVol(Vol volToUpdate) throws IOException {

        for (int i = 0; i < listeVols.size(); i++) {

            if (listeVols.get(i).getIdVol() == volToUpdate.getIdVol()){
                listeVols.set(i,volToUpdate);
                DataPersister.write(listeVols);
                break;
            }
        }
    }
    public void deleteVol(int id) throws IOException {
        for (Vol vol : listeVols){
            if (vol.getIdVol()==id){
                listeVols.remove(vol);
                DataPersister.write(listeVols);
                break;
            }
        }
    }

    public int getNextId(){
        int id = 0;
        for (Vol vol : listeVols){
            if (vol.getIdVol() > id){
                id = vol.getIdVol();
            }
        }
        return id + 1;
    }

    public void addReservation(Vol vol, int reservations) throws IOException {
        for (Vol v : listeVols){
            if (v.getIdVol() == vol.getIdVol()){
                v.setReserv(v.getReserv() + reservations);
                DataPersister.write(listeVols);
                break;
            }
        }
    }
}
