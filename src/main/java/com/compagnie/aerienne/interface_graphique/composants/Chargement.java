package com.compagnie.aerienne.interface_graphique.composants;

import javax.swing.*;
import java.awt.*;

public class Chargement extends JLabel {

    public Chargement(){
        setText("Chargement ...");
        init();
    }
    public Chargement(String text){
        setText(text);
        init();
    }

    private void init(){
        ImageIcon originalSpinnerIcon = new ImageIcon(FormulaireVol.class.getResource("/icons/spinner.gif"));
        setIcon(originalSpinnerIcon);
        setVisible(false);
        setForeground(new Color(77, 77, 77));
        setIconTextGap(5);
    }
}
