package com.compagnie.aerienne.interface_graphique.composants.table;

import com.compagnie.aerienne.interface_graphique.AppColors;
import com.compagnie.aerienne.interface_graphique.composants.BoutonFiltrer;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class FilterForm extends JPanel {

    JPanel formContainer;
    JTextField idVolField = new JTextField(10);
    JTextField destinationField = new JTextField(10);
    JTextField datedField = new JTextField(10);
    JTextField resevField = new JTextField(10);

    JButton resetButton;
    public FilterForm(TableRowSorter<TableModel> sorter){
        setBackground(AppColors.BG_LIGHT);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5,0,0,0));
        formContainer = new JPanel();
        formContainer.setVisible(false);
        BoutonFiltrer btnFilter = new BoutonFiltrer();
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(AppColors.BG_LIGHT);
        header.add(btnFilter,BorderLayout.EAST);
        header.setBorder(new EmptyBorder(0,0,5,0));
        add(header,BorderLayout.NORTH);
        initForm();

        btnFilter.setTargetPanel(formContainer);

        DocumentListener listener = new FilterListener(
                sorter,
                idVolField,
                destinationField,
                datedField,
                resevField
        );

        idVolField.getDocument().addDocumentListener(listener);
        destinationField.getDocument().addDocumentListener(listener);
        datedField.getDocument().addDocumentListener(listener);
        resevField.getDocument().addDocumentListener(listener);

        resetButton.addActionListener(e -> {
            idVolField.setText("");
            destinationField.setText("");
            datedField.setText("");
            resevField.setText("");
        });



    }

    private void initForm(){
        formContainer.setBackground(AppColors.BG_DARK);
        formContainer.setBorder(new CompoundBorder(
                new LineBorder(AppColors.BG_LIGHT),
                new EmptyBorder(10,10,10,10)
        ));
        resetButton = new JButton("Réinitialiser");
        resetButton.setBackground(AppColors.BG_LIGHT);
        idVolField.setBackground(AppColors.BG_LIGHT);
        destinationField.setBackground(AppColors.BG_LIGHT);
        datedField.setBackground(AppColors.BG_LIGHT);
        resevField.setBackground(AppColors.BG_LIGHT);

        formContainer.setLayout(new GridLayout(3, 4, 5, 5));
        formContainer.add(new JLabel("ID Vol :"));
        formContainer.add(idVolField);
        formContainer.add(new JLabel("Destination :"));
        formContainer.add(destinationField);
        formContainer.add(new JLabel("Date :"));
        formContainer.add(datedField);
        formContainer.add(new JLabel("Réservations :"));
        formContainer.add(resevField);
        formContainer.add(new JLabel(""));
        formContainer.add(new JLabel(""));
        formContainer.add(new JLabel(""));
        formContainer.add(resetButton);
        add(formContainer,BorderLayout.CENTER);
    }
}
