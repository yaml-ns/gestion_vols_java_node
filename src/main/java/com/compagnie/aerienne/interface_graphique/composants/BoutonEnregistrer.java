package com.compagnie.aerienne.interface_graphique.composants;

import com.compagnie.aerienne.interface_graphique.AppColors;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BoutonEnregistrer extends JButton {

    public BoutonEnregistrer(){
        setText("Enregistrer");
        setIcon(new FlatSVGIcon("icons/floppy.svg",12,12));
        setBackground(AppColors.BG_EXTRA_LIGHT);
        setPreferredSize(new Dimension(120,25));
        setBorder(new LineBorder(new Color(0,0,0,0),1));
    }

}
