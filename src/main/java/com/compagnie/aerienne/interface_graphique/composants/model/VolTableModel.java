package com.compagnie.aerienne.interface_graphique.composants.model;

import com.compagnie.aerienne.modele.Vol;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class VolTableModel extends AbstractTableModel {

    private List<Vol> listeDeVols;
    private final String[] nomsDesColonnes = {"Numéro","Destination","Date de départ","Nbr. Réservations","Actions"};
        public VolTableModel(List<Vol> listeDeVols){
            this.listeDeVols = listeDeVols;
        }

    @Override
    public int getRowCount() {
        return listeDeVols.size();
    }

    @Override
    public int getColumnCount() {
        return nomsDesColonnes.length;
    }

    @Override
    public String getColumnName(int column) {
        return nomsDesColonnes[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vol vol = listeDeVols.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> vol.getIdVol();
            case 1 -> vol.getDestination();
            case 2 -> vol.getDated().toString();
            case 3 -> vol.getReserv();
            case 4 -> "Actions";
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 4;
    }


    public void updateData(List<Vol> nouveauxVols){
            this.listeDeVols = nouveauxVols;
            fireTableDataChanged();
    }

    public Vol getVolAt(int row) {
        return listeDeVols.get(row);
    }
}
