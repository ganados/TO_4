package com.vector.app.chart;


import java.awt.*;
import javax.swing.JPanel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Chart extends JPanel {
    private String list;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        StringBuilder individuals = new StringBuilder(getList());
        individuals.deleteCharAt(0);
        individuals.deleteCharAt(individuals.lastIndexOf("]"));
        String[] splitedIndividuals = new String(individuals).split(",");

        StringBuilder ills = new StringBuilder();
        StringBuilder healthy = new StringBuilder();

        for (int i = 0; i < splitedIndividuals.length; i++) {
            String[] strings = splitedIndividuals[i].split("-");
            if (strings[0].equals("haveSymptoms") || strings[0].equals("haveNotSymptoms")) {
                ills.append(strings[1]);
                ills.append("-");
            } else {
                healthy.append(strings[1]);
                healthy.append("-");
            }
        }

        if (ills.length() != 0) {
            ills.deleteCharAt(ills.lastIndexOf("-"));
            String[] illsCords = new String(ills).split("-");
            printPoints(g2d, illsCords, Color.red);
        }
        if (healthy.length() != 0) {
            healthy.deleteCharAt(healthy.lastIndexOf("-"));
            String[] healthyCords = new String(healthy).split("-");
            printPoints(g2d, healthyCords, Color.blue);
        }
    }

    private void printPoints(Graphics2D g2d, String[] healthyCords, Color color) {
        g2d.setColor(color);
        int xSize = 5;
        int ySize = 5;
        if(color.equals(Color.blue)){
            xSize = 10;
            ySize = 11;
        }
        for (String healthyCord : healthyCords) {
            String[] cords = healthyCord.split(";");
            int x = (int) Double.parseDouble(cords[0].split("\\.")[0]);
            int y = (int) Double.parseDouble(cords[1].split("\\.")[0]);

            g2d.drawOval(x * 20, y * 30, xSize, ySize);
            g2d.fillOval(x * 20, y * 30, xSize, ySize);
        }
    }
}