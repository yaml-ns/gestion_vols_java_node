package com.compagnie.aerienne.interface_graphique.composants;

import com.compagnie.aerienne.Application;
import com.compagnie.aerienne.interface_graphique.AppColors;
import com.compagnie.aerienne.interface_graphique.InterfacePrincipale;
import com.compagnie.aerienne.interface_graphique.parts.SouthPanel;
import com.compagnie.aerienne.modele.Vol;
import com.compagnie.aerienne.service.GestionVolService;
import com.compagnie.aerienne.service.VolValidator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class FormulaireVol extends JDialog {

    private final Vol vol;
    private JTextField dated;
    private final JLabel spinner;
    private JTextField destination;
    private JTextField nbrReservations;
    private final InterfacePrincipale ip;
    JLabel errorMessage;
    public FormulaireVol(InterfacePrincipale ip, Vol vol){

        super(ip, JDialog.ModalityType.APPLICATION_MODAL);
        this.ip = ip;
        this.vol = vol;

        spinner = new Chargement("Chargement ...");

        setTitle("Ajouter / Modifier un nouveau vol");
        setIconImage(new ImageIcon(Objects.requireNonNull(Application.class
                .getResource("/icons/air_relax.png")))
                .getImage()
        );
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.errorMessage = new JLabel();
        errorMessage.setForeground(AppColors.FG_DANGER);
        topPanel.add(errorMessage);
        topPanel.add(spinner);

        add(topPanel, BorderLayout.NORTH);
        JPanel form = new JPanel(new GridBagLayout());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setFields(form);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnEnregistrer = new BoutonEnregistrer();
        JButton btnReset = new JButton("Effacer");

        btnReset.setPreferredSize(new Dimension(100,25));
        btnReset.setBorder(new LineBorder(new Color(0,0,0,0),1));


        btnEnregistrer.addActionListener(e -> {
            spinner.setVisible(true);
            errorMessage.setText("");
            process(vol);
        });

        btnReset.addActionListener(e -> this.resetForm());

        bottomPanel.add(btnEnregistrer);
        bottomPanel.add(btnReset);

        add(bottomPanel, BorderLayout.SOUTH);

        setMinimumSize(new Dimension(400,250));
        pack();
        setLocationRelativeTo(ip);

    }


    private void setFields(JPanel form) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel destinationLabel = new JLabel("Destination :");
        form.add(destinationLabel, gbc);
        gbc.gridx = 1;
        this.destination  = new JTextField(this.vol.getDestination(),15);
        form.add(this.destination, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;

        JLabel datedLabel = new JLabel("Date de départ :");
        form.add(datedLabel, gbc);


        String dateDepart = vol.getDated() != null ? vol.getDated().toString() : "";
        this.dated = new JTextField(dateDepart,15);
        gbc.gridx = 1;
        form.add(this.dated, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;

        JLabel nbrReservationLabel = new JLabel("Nombre de réservations :");
        form.add(nbrReservationLabel, gbc);

        gbc.gridx = 1;
         this.nbrReservations= new JTextField(String.valueOf(this.vol.getReserv()),15);
        form.add(this.nbrReservations, gbc);

        add(form,BorderLayout.CENTER);

    }

    private void resetForm(){
        this.destination.setText("");
        this.dated.setText("");
        this.nbrReservations.setText("");
        this.errorMessage.setText("");
    }


    private void process(Vol vol){

        InfoPanel infoPanel = InfoPanel.getInstance();

        VolValidator validator = new VolValidator(
                vol.getIdVol(),
                this.destination.getText(),
                this.dated.getText(),
                this.nbrReservations.getText()
        );
        Vol validatedVol = validator.validate();
        if (validatedVol == null){
            this.errorMessage.setText(validator.getErrorMessage());
            spinner.setVisible(false);
        }else{
            spinner.setVisible(false);
            this.errorMessage.setText("");
            try {
                GestionVolService volManager = new GestionVolService();
                String infoMessage;
                if (validatedVol.getIdVol() == 0){
                    try {
                    volManager.addVol(validatedVol);
                        infoMessage = """
                                       Le Vol %d à destination de %s a été ajouté avec succès !"""
                                .formatted(
                                        validatedVol.getIdVol(),
                                        validatedVol.getDestination()
                                );

                        SouthPanel.getInstance().setTotalVols(volManager.getAll().size());
                        infoPanel.setOperationResult(infoMessage, InfoPanel.messageType.SUCCESS);
                    }catch (Exception e){
                        infoMessage = "Une erreur s'est produite pendant l'ajout du vol";
                        infoPanel.setOperationResult(infoMessage, InfoPanel.messageType.ERROR);
                    }

                }else{
                    try {

                    Vol updatedVol = volManager.updateVol(validatedVol);
                    if (updatedVol != null){
                        infoMessage = """
                                       Le Vol %d à destination de %s a été modifié avec succès !"""
                                .formatted(
                                        validatedVol.getIdVol(),
                                        validatedVol.getDestination()
                                );
                        infoPanel.setOperationResult(infoMessage, InfoPanel.messageType.SUCCESS);
                    }else{
                        infoMessage = "Une erreur s'est produite pendant la mise à jour";
                        infoPanel.setOperationResult(infoMessage, InfoPanel.messageType.ERROR);
                    }
                    }catch (Exception e){
                        infoMessage = "Une erreur s'est produite pendant la mise à jour";
                        infoPanel.setOperationResult(infoMessage, InfoPanel.messageType.ERROR);
                    }

                }
                ip.rafraichirTable(volManager.getAll());
                this.resetForm();
                dispose();

            }catch (Exception exception){
                errorMessage.setText("Une erreur s'est produite lors de cette opération.");
            }

        }
    }




}
