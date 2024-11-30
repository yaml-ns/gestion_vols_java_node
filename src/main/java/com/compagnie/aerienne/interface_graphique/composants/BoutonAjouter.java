package com.compagnie.aerienne.interface_graphique.composants;

import com.compagnie.aerienne.interface_graphique.AppColors;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.awt.*;

public class BoutonAjouter extends JButton {

    public BoutonAjouter(){
        setText("Ajouter un Vol");
        FlatSVGIcon plusIcon = new FlatSVGIcon(getClass().getResource("/icons/plus.svg"));
        setIcon(plusIcon);
        setPreferredSize(new Dimension(150,35));
        setSelected(false);
        setBackground(AppColors.BG_EXTRA_LIGHT);
        setIconTextGap(10);


    }

}
