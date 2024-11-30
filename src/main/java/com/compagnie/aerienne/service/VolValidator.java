package com.compagnie.aerienne.service;

import com.compagnie.aerienne.modele.DateVol;
import com.compagnie.aerienne.modele.Vol;

import java.util.InvalidPropertiesFormatException;

public class VolValidator {

    private int id;
    private Vol vol;
    private DateVol dateVol;
    private String destination;
    private String dated;
    private String nbrReservation;
    private String msgErreur;

    public VolValidator(int id,String destination, String dated, String nbrReservation){
        this.id = id;
        this.destination = destination;
        this.dated = dated;
        this.nbrReservation = nbrReservation;
    }
    public Vol validate(){
        if (this.validateDestination() && this.validateDated() && this.validateNbrReservation()){
            return this.getValidatedVol();
        }
        return null;
    }

    private Vol getValidatedVol() {
        this.vol = new Vol(this.id,this.destination,this.dateVol,Integer.parseInt(this.nbrReservation));
        return this.vol;
    }

    private boolean validateDestination(){
        if (this.destination == null || this.destination.isBlank()){
            this.msgErreur="Le champs destination ne doit pas être vide !";
            return false;
        }
        if (this.destination.length() <2 ){
            this.msgErreur = "Le champs destination doit avoir au moins deux caractères";
            return false;
        }
        return true;
    }

    private boolean validateNbrReservation(){
        try {
            int nbr = Integer.parseInt(this.nbrReservation);
            if (nbr < 0 || nbr > 400){
                msgErreur = "Veuillez saisir un nombre de réservations entre 0 et 400 svp !";
                return false;
            }

        }catch (NumberFormatException e){
            msgErreur = "Veuillez saisir un nombre valide svp !";
            return false;
        }

        return true;
    }

    private boolean validateDated(){
        if (this.dated.isBlank()){
            msgErreur = "Veuillez saisir une date de départ svp !";
            return false;
        }
        try{
            String[] elems = this.dated.split("/");
            boolean[] etat = new boolean[3];

            String dateError = DateVol.validerDate(
                    Integer.parseInt(elems[0]),
                    Integer.parseInt(elems[1]),
                    Integer.parseInt(elems[2]),
                    etat);
            System.out.println(dateError);
            if (!dateError.isBlank()){
                msgErreur = dateError;
                return false;
            }
            this.dateVol = new DateVol(
                    Integer.parseInt(elems[0]),
                    Integer.parseInt(elems[1]),
                    Integer.parseInt(elems[2])
            );

            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            msgErreur = "La date de départ semble invalide !";
            return false;
        }

    }

    public String getErrorMessage(){
        return this.msgErreur;
    }
}
