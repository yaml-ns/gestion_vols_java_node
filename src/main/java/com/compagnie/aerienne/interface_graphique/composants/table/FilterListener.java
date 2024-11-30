package com.compagnie.aerienne.interface_graphique.composants.table;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

public class FilterListener implements DocumentListener {

    TableRowSorter<TableModel> sorter;
    private final JTextField[] fields;

    public FilterListener(TableRowSorter<TableModel> sorter, JTextField... fields){
        this.sorter = sorter;
        this.fields = fields;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateFilters();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateFilters();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateFilters();
    }

    private void updateFilters() {
        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        for (int i = 0; i < fields.length; i++) {
            String text = fields[i].getText().trim();
            if (!text.isEmpty()) {
                try {
                    filters.add(RowFilter.regexFilter("(?i)" + text, i));
                } catch (java.util.regex.PatternSyntaxException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        if (filters.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.andFilter(filters));
        }
    }
}
