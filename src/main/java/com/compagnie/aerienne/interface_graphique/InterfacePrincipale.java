package com.compagnie.aerienne.interface_graphique;

import com.compagnie.aerienne.Application;
import com.compagnie.aerienne.interface_graphique.composants.FormulaireVol;
import com.compagnie.aerienne.interface_graphique.composants.InfoPanel;
import com.compagnie.aerienne.interface_graphique.composants.table.VolTable;
import com.compagnie.aerienne.interface_graphique.parts.NorthPanel;
import com.compagnie.aerienne.interface_graphique.parts.SouthPanel;
import com.compagnie.aerienne.interface_graphique.parts.WestPanel;
import com.compagnie.aerienne.modele.Vol;
import com.compagnie.aerienne.service.GestionVolService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InterfacePrincipale extends JFrame {

    GestionVolService volManager;
    VolTable volTable;
    List<Vol> liste = new ArrayList<>();
    SouthPanel southPanel;
    InfoPanel infoPanel;
    public InterfacePrincipale() throws IOException {
        volManager = new GestionVolService();
        southPanel = SouthPanel.getInstance();
        infoPanel = InfoPanel.getInstance();
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

        volTable = new VolTable(new ArrayList<>());

        FormulaireVol formulaire = new FormulaireVol(this, new Vol());
        NorthPanel northPanel = new NorthPanel(formulaire);
        WestPanel westPane = new WestPanel();
        southPanel.setTotalVols(0);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        centerPanel.setBackground(AppColors.BG_LIGHT);
        centerPanel.add(volTable.getTable(), BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(westPane, BorderLayout.WEST);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        add(mainPanel);

        chargerListeVols();
    }

    private void chargerListeVols() {
        infoPanel.setIsLoading(true);
        SwingWorker<List<Vol>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Vol> doInBackground() throws Exception {
                return volManager.getAll();
            }

            @Override
            protected void done() {
                try {
                    liste = get();
                    volTable.updateData(liste);
                    southPanel.setTotalVols(liste.size());
                    infoPanel.setOperationResult(
                            "Chargement des données réussi",
                            InfoPanel.messageType.SUCCESS
                    );
                } catch (Exception e) {
                    infoPanel.setOperationResult(
                            "Une erreur s'est produite lors de la récupération des données",
                            InfoPanel.messageType.ERROR
                    );
                } finally {
                    infoPanel.setIsLoading(false);
                }
            }
        };

        worker.execute();
    }


    public void rafraichirTable(List<Vol> nouvelleListe) {
        volTable.updateData(nouvelleListe);
    }
}
