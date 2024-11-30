package com.compagnie.aerienne.interface_graphique.composants;

import com.compagnie.aerienne.interface_graphique.AppColors;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BoutonFiltrer extends JToggleButton {

    private int initialHeight;
    private JPanel targetPanel;
    private int step = 5;
    private int delay = 5;
    private Timer animationTimer;
    public BoutonFiltrer() {
        setText("Filtrer les vols");
        FlatSVGIcon FilterIcon = new FlatSVGIcon(getClass().getResource("/icons/filter.svg"));
        setIcon(FilterIcon);
        setPreferredSize(new Dimension(150, 35));
        setBackground(AppColors.BG_DARK);
        setIconTextGap(10);
        setSelected(false);
        addActionListener(e->{
            toggle();
        });
    }

    private void toggle() {

        if (targetPanel == null) {
            throw new IllegalStateException("Le panel cible n'a pas été défini.");
        }

        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }

        boolean isExpanding = isSelected();

        animationTimer = new Timer(delay, new AbstractAction() {
            int currentHeight = targetPanel.getPreferredSize().height;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isExpanding && currentHeight < initialHeight) {
                    currentHeight += step;
                } else if (!isExpanding && currentHeight > 0) {
                    currentHeight -= step;
                } else {
                    ((Timer) e.getSource()).stop();
                    if (!isExpanding) {
                        targetPanel.setVisible(false);
                    }
                }

                targetPanel.setPreferredSize(new Dimension(targetPanel.getWidth(), currentHeight));
                targetPanel.revalidate();
            }
        });

        if (isExpanding) {
            targetPanel.setVisible(true);
        }

        animationTimer.start();
    }

   public void setTargetPanel(JPanel target){
        targetPanel = target;
        initialHeight = target.getPreferredSize().height;
        targetPanel.setVisible(false);
        targetPanel.setPreferredSize(new Dimension(0, 0));
   }

    public void setStep(int step) {
        this.step = step;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
