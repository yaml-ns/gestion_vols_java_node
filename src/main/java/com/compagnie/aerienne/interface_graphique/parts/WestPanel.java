package com.compagnie.aerienne.interface_graphique.parts;

import com.compagnie.aerienne.Application;
import com.compagnie.aerienne.interface_graphique.AppColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class WestPanel extends JPanel {

    public WestPanel(){
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(15,20,15,15));
        setPreferredSize(new Dimension(220,300));
        setBackground(AppColors.BG_LIGHT);

        JLabel marque = new JLabel(
        """
            <html>
            <span style='font-size:22px; font-weight:bold;'>
            AIR RELAX
            </span>
            </html>
            """
        );
        JLabel slogan = new JLabel(
                """
                <html>
                <span style='font-size:12px;font-style:italic;margin-top:5px;'>
                Voyager, se reposer !
                </span>
                </html>
                """
        );
        slogan.setBorder(new EmptyBorder(0,0,0,0));
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE,2));

        JPanel haut = new JPanel(new BorderLayout());
        haut.setBackground(AppColors.BG_LIGHT);
        haut.add(marque,BorderLayout.NORTH);
        haut.add(slogan,BorderLayout.CENTER);
        haut.add(separator,BorderLayout.SOUTH);
        add(haut,BorderLayout.NORTH);

        JLabel realisation = new JLabel(
                """
                <html>Réalisé par : <br>
                - Kevin Jesus <br>
                - Térence Sionneau<br>
                - Djamel N.Sidenas
                </html>""");
        add(realisation, BorderLayout.SOUTH);

    }

}
