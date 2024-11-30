package com.compagnie.aerienne.interface_graphique.parts;

import com.compagnie.aerienne.interface_graphique.AppColors;
import com.compagnie.aerienne.interface_graphique.composants.InfoPanel;
import com.compagnie.aerienne.modele.Vol;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SouthPanel extends JPanel {

    private static SouthPanel instance;
    JLabel totalVols = new JLabel();
    JLabel copyright = new JLabel("©2024 - Tous droits réservés  |  ");

    public static SouthPanel getInstance(){
        if (instance == null){
            instance = new SouthPanel();
        }
        return instance;
    }
    public SouthPanel(){
        setBackground(AppColors.BG_MEDIUM);
        add(copyright);
        add(totalVols);
    }


    public void setTotalVols(int total){
        totalVols.setText("Total des vols : " + total);
    }
}
