package com.compagnie.aerienne.modele;

import java.time.LocalDate;

public class DateVol {
    static public String tabMois[] = {null, "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};
    static public LocalDate dateActuelle = LocalDate.now();
    private int jour;
    private int mois;
    private int an;

    public DateVol() {
        jour = mois = 1;
        an = 2000;
    }

    public DateVol(int jour, int mois, int an) {
        this.jour = jour;
        this.mois = mois;
        this.an = an;
    }

    //Valider trois
    public static String validerDate(int jour, int mois, int an, boolean[] etat) {
        String message = "";//Servira comme message par défaut
        int nbJours = 0;

        //Valider le mois
        if (mois < 1 || mois > 12) {
            etat[1] = false;
            message += "Mois " + mois + " n'est un mois valide [1-12]" + "\n";
        } else {
            etat[1] = true;
        }

        //Valider le jour
        if (etat[1]) {
            nbJours = determinerNbJoursMois(mois, an);
            if (jour > nbJours || jour <= 0) {
                etat[0] = false;
                message += "Jour invalide pour le mois de " + tabMois[mois].toLowerCase() + "\n";
            } else {
                etat[0] = true;
            }

        } else {
            message += "Impossible de valider le jour puisque votre mois est invalide";
        }

        //Valider Année
        int anneActuelle = dateActuelle.getYear();
        if (an < anneActuelle) {
            etat[2] = false;
            message += "Année " + an + " ne peut pas étre inférieure à l'année actuelle, soit " + anneActuelle + "\n";
        } else {
            etat[2] = true;
        }
        return message;
    }

    public static boolean validerDateReservation(DateVol dateReservation) {
        boolean etatDate = true;
        if (dateReservation.getMois() < dateActuelle.getMonthValue()) {
            etatDate = false;
        } else if (dateReservation.getJour() < dateActuelle.getDayOfMonth()) {
            etatDate = false;
        }
        return etatDate;
    }

    public static boolean estBissextile(int an) {
        return (an % 4 == 0 && an % 100 != 0) || (an % 400 == 0);
    }

    public static int determinerNbJoursMois(int mois, int an) {
        int nbJours;
        int tabJrMois[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (mois == 2 && estBissextile(an))
            nbJours = 29;
        else
            nbJours = tabJrMois[mois];
        return nbJours;
    }

    // Les méthodes set et get habituelles
    public int getJour() {
        return this.jour;
    }

    public void setJour(int jour) {
        int nbJours = determinerNbJoursMois(this.mois, this.an);
        if (jour > nbJours || jour < 1) {
            System.out.println("Jour invalide pour le mois de " + tabMois[this.mois].toLowerCase());
        } else {
            this.jour = jour;
        }
    }

    public int getMois() {
        return this.mois;
    }

    public void setMois(int mois) {
        int nbJours;
        if (mois < 1 || mois > 12) {
            System.out.println("Mois " + mois + " n'est un mois valide [1-12]");
        } else {
            nbJours = determinerNbJoursMois(mois, this.an);
            if (this.jour > nbJours) {
                System.out.println("Mois " + tabMois[mois].toLowerCase() + " n'est un mois valide pour le jour actuel du vol qu'est " + this.jour);
            } else {
                this.mois = mois;
            }
        }
    }

    public int getAn() {
        return this.an;
    }

    public void setAn(int an) {
        int anneActuelle = dateActuelle.getYear();
        if (an < anneActuelle) {
            System.out.println("Année " + an + " ne peut pas étre inférieure é l'année actuelle, soit " + anneActuelle);
        } else {
            this.an = an;
        }
    }

    public String toString() {
        return String.format(
                "%02d/%02d/%04d",
                this.jour,
                this.mois,
                this.an
        );
    }
} // fin de la classe Date