package com.compagnie.aerienne.interface_graphique.composants.model;

import com.compagnie.aerienne.interface_graphique.AppColors;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private final JPanel panel = new JPanel();
    private ActionListener onUpdateListener;
    private ActionListener onReserveListener;
    private ActionListener onDeleteListener;

    public ActionButtonEditor() {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        Dimension btnDimension = new Dimension(25, 25);

        JButton btnUpdate = new JButton();
        btnUpdate.setPreferredSize(btnDimension);
        btnUpdate.setIcon(new FlatSVGIcon("icons/edit.svg"));
        btnUpdate.setBackground(AppColors.BG_MEDIUM);
        btnUpdate.setToolTipText("Modifier");
        btnUpdate.setFocusable(false);

        JButton btnReserve = new JButton();
        btnReserve.setPreferredSize(btnDimension);
        btnReserve.setIcon(new FlatSVGIcon("icons/calendar-plus.svg"));
        btnReserve.setBackground(AppColors.BG_EXTRA_LIGHT);
        btnReserve.setToolTipText("Réserver");
        btnReserve.setFocusable(false);

        JButton btnDelete = new JButton();
        btnDelete.setPreferredSize(btnDimension);
        btnDelete.setIcon(new FlatSVGIcon("icons/trash.svg"));
        btnDelete.setBackground(AppColors.BG_DANGER);
        btnDelete.setToolTipText("Supprimer");
        btnDelete.setFocusable(false);

        btnUpdate.addActionListener(e -> {
            fireEditingStopped();
            if (onUpdateListener != null) {
                onUpdateListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "update"));
            }
        });

        btnReserve.addActionListener(e -> {
            fireEditingStopped();
            if (onReserveListener != null) {
                onReserveListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "reserve"));
            }
        });

        btnDelete.addActionListener(e -> {
            fireEditingStopped();
            if (onDeleteListener != null) {
                onDeleteListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "delete"));
            }
        });

        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnReserve);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            panel.setBackground(table.getSelectionBackground());
        } else {
            panel.setBackground(table.getBackground());
        }

        // Force the update of tooltips
        panel.revalidate();
        panel.repaint();

        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    public void setOnUpdateListener(ActionListener onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
    }

    public void setOnReserveListener(ActionListener onReserveListener) {
        this.onReserveListener = onReserveListener;
    }

    public void setOnDeleteListener(ActionListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }
}
