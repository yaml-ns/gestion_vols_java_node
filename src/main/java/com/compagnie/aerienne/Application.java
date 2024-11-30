package com.compagnie.aerienne;

import com.compagnie.aerienne.interface_graphique.AppColors;
import com.compagnie.aerienne.interface_graphique.InterfacePrincipale;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.*;
import java.io.*;

public class Application {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                FlatMacDarkLaf.setup();
                InterfacePrincipale view = new InterfacePrincipale();
                view.getRootPane().putClientProperty(
                        "JRootPane.titleBarBackground",
                        AppColors.BG_MEDIUM
                );
                view.setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}