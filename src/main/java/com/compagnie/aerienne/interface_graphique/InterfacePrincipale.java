package com.compagnie.aerienne.interface_graphique;

import com.compagnie.aerienne.Application;
import com.compagnie.aerienne.interface_graphique.composants.FormulaireVol;
import com.compagnie.aerienne.interface_graphique.composants.table.VolTable;
import com.compagnie.aerienne.interface_graphique.parts.NorthPanel;
import com.compagnie.aerienne.interface_graphique.parts.SouthPanel;
import com.compagnie.aerienne.interface_graphique.parts.WestPanel;
import com.compagnie.aerienne.modele.Vol;
import com.compagnie.aerienne.service.GestionVolService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class InterfacePrincipale extends JFrame {

    GestionVolService volManager;
    VolTable volTable;
    public InterfacePrincipale() throws IOException {
        volManager = new GestionVolService();
        initialiserInterface();
        initialiserLesComposants();
    }

    private void initialiserInterface(){
        setTitle("Gestion des Vols");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(Objects.requireNonNull(Application.class
                .getResource("/icons/air_relax.png")))
                .getImage()
        );
        setLocationRelativeTo(null);
    }
    private void initialiserLesComposants() throws IOException {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(AppColors.BG_MEDIUM);

        volManager = new GestionVolService();
        List<Vol> liste = volManager.getAll();
        volTable = new VolTable(liste);

        FormulaireVol formulaire = new FormulaireVol(this, new Vol());
        NorthPanel northPanel = new NorthPanel(formulaire);
        WestPanel westPane = new WestPanel();
        SouthPanel southPanel = SouthPanel.getInstance();
        southPanel.setTotalVols(liste.size());

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        centerPanel.setBackground(AppColors.BG_LIGHT);
        centerPanel.add(volTable.getTable(),BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);


        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(westPane, BorderLayout.WEST);
        mainPanel.add(southPanel,BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void rafraichirTable(List<Vol> nouvelleListe) {
        volTable.updateData(nouvelleListe);
    }
}
