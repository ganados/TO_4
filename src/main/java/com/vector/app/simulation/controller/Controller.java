package com.vector.app.simulation.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.vector.app.chart.Chart;
import com.vector.app.memento.multi.Mementos;
import com.vector.app.simulation.generator.GeneratePopulation;
import com.vector.app.simulation.individual.Individual;
import com.vector.app.simulation.individualParams.IndividualParams;
import com.vector.app.simulation.population.Population;
import com.vector.app.simulation.room.Room;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Controller extends JFrame {
    private JButton save;
    private JButton load;
    private JComboBox position;
    private Population population;
    private Room room;
    private Mementos mementos;
    private int counter = 0;

    public void makeSimulation() {
        Random random = new Random();
        preparePopulation(population);
        Chart chart = new Chart(population.getPopulation().toString());

        prepareChart();

        for (; ; ) {

            for (int i = 0; i < 25; i++) {

                population.getInfected().forEach(Individual::handle);

                chart.updateList(population.getPopulation().toString());
                addChart(chart);

                for (int j = 0; j < population.getInfected().size(); j++) {
                    Individual individual = population.getInfected().get(j);
                    individual.clearParams(population);
                    individual.getDistances(population.getNotInfected());
                    individual.getTimes(population.getNotInfected());

                    Map<String, Integer> times = individual.getIndividualParams().getTimes();

                    for (String key : times.keySet()) {
                        if (times.get(key) >= 75) {
                            try {
                                population.getIndividual(key).handle(individual);
                            } catch (NullPointerException e) {
                                System.out.println("Key " + key + " does not exist");
                            }
                        }
                    }
                }
                population.getPopulation().forEach(individual1 -> individual1.generatePosition(room, random));
                population.deleteIfExited();
                if (counter % 7 == 0) {
                    population.addIndividual(GeneratePopulation.getNotResistIndividual(room));
                }
            }

            System.out.println(counter);
            System.out.println("Infected: " + population.getInfected().size() + " " + "Not infected: " + population.getNotInfected().size());
            if (counter == 10000) {
                break;
            }
            if (population.getInfected().size() == 0) {
                break;
            }
            counter++;
        }
        population.getPopulation().forEach(System.out::println);
        System.out.println("Infected: " + population.getInfected().size() + " " + "Not infected: " + population.getNotInfected().size());
    }


    public void preparePopulation(final Population population) {
        for (int j = 0; j < population.getInfected().size(); j++) {
            Individual individual = population.getInfected().get(j);
            List<Individual> comparedIndividuals = new LinkedList<>(population.getPossibleInfected());
            Map<String, Double> distances = individual.getDistances(comparedIndividuals);
            Map<String, Integer> times = individual.getTimes(comparedIndividuals);

            individual.setIndividualParams(IndividualParams.of(distances, times));
        }
    }

    private void prepareChart() {
        setSave(new JButton("save"));
        setLoad(new JButton("load"));
        save.setBounds(0, 0, 100, 50);

        save.addActionListener(new SaveListener());
        load.addActionListener(new LoadListener());
        JPanel jPanel = new JPanel();
        jPanel.add(save);
        jPanel.add(load);
        setPosition(new JComboBox(mementos.getIndexes()));
        jPanel.add(position);
        add(jPanel, BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocation(800, 50);
        setVisible(true);
    }

    private void addChart(final Chart chart) {
        add(chart);
        revalidate();
        repaint();
    }

    private void addToComboBox(final String[] indexes) {
        String[] strings = getItemsFromComboBox();
        if (indexes.length != strings.length) {
            for (int ii = strings.length; ii < indexes.length; ii++) {
                position.addItem(indexes[ii]);
            }
        }
    }

    private String[] getItemsFromComboBox() {
        String[] strings = new String[position.getItemCount()];
        for (int ii = 0; ii < position.getItemCount(); ii++) {
            strings[ii] = position.getItemAt(ii).toString();
        }
        return strings;
    }

    class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            mementos.addMemento(counter, new ArrayList<>(population.getPopulationCopy()));
            addToComboBox(mementos.getIndexes());
        }
    }

    class LoadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            int pos = counter;
            try {
                pos = Integer.parseInt(position.getItemAt(position.getSelectedIndex()).toString());
            } catch (NumberFormatException e) {
                System.out.println("Illegal arg");
            }
            population.setPopulation(mementos.getMemento(pos));
        }
    }
}
