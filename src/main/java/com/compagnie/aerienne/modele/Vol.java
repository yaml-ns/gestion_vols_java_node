package com.compagnie.aerienne.modele;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Vol {

    private int idVol;
    private String destination;
    @JsonDeserialize(using = DateVolDeserializer.class)
    @JsonSerialize(using = DateVolSerializer.class)
    private DateVol dated;
    private int reserv;

    public Vol(){}

    public Vol(int idVol, String destination, DateVol dated, int nbrReservations){
        this.idVol = idVol;
        this.destination = destination;
        this.dated = dated;
        this.reserv = nbrReservations;
    }

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public DateVol getDated() {
        return dated;
    }

    public void setDated(DateVol dated) {
        this.dated = dated;
    }

    public int getReserv() {
        return reserv;
    }

    public void setReserv(int reserv) {
        this.reserv = reserv;
    }

    @Override
    public String toString() {
        return "Vol{" +
                "numeroVol=" + idVol +
                ", destination='" + destination + '\'' +
                ", dateDepart=" + dated +
                ", nbrReservations=" + reserv +
                '}';
    }
}
