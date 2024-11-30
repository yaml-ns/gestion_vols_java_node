package com.compagnie.aerienne.interface_graphique.composants.model;

import com.compagnie.aerienne.interface_graphique.AppColors;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ActionButtonRendrer extends JPanel implements TableCellRenderer {

    private final JButton btnEdit = new JButton();
    private final JButton btnReserve = new JButton();
    private final JButton btnDelete = new JButton();

    public ActionButtonRendrer() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        initializeButtons();
        add(btnEdit);
        add(btnDelete);
        add(btnReserve);
    }

    private void initializeButtons() {
        btnEdit.setPreferredSize(new Dimension(25, 25));
        btnEdit.setToolTipText("Modifier");
        btnEdit.setIcon(new FlatSVGIcon("icons/edit.svg"));
        btnEdit.setBackground(AppColors.BG_MEDIUM);
        btnEdit.setFocusable(false);

        btnReserve.setPreferredSize(new Dimension(25, 25));
        btnReserve.setToolTipText("Réserver");
        btnReserve.setIcon(new FlatSVGIcon("icons/calendar-plus.svg"));
        btnReserve.setBackground(AppColors.BG_EXTRA_LIGHT);
        btnReserve.setFocusable(false);

        btnDelete.setPreferredSize(new Dimension(25, 25));
        btnDelete.setToolTipText("Supprimer");
        btnDelete.setIcon(new FlatSVGIcon("icons/trash.svg"));
        btnDelete.setBackground(AppColors.BG_DANGER);
        btnDelete.setFocusable(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(table.getBackground());
        }
        initializeButtons();

        return this;
    }
}
