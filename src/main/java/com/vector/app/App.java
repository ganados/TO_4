package com.vector.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JFrame;

import com.vector.app.chart.Chart;
import com.vector.app.memento.multi.Mementos;
import com.vector.app.simulation.controller.Controller;
import com.vector.app.simulation.generator.GeneratePopulation;
import com.vector.app.simulation.individual.Individual;
import com.vector.app.simulation.population.Population;
import com.vector.app.simulation.room.Room;

public class App {
    public static void main(String[] args) {

        Random random = new Random();
        Room room = new Room(40, 30);
        Population population = GeneratePopulation.generateNotResistPopulation(500, room);
        Mementos mementos = new Mementos(new HashMap<>());
        Controller controller = new Controller();


        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 700);
        f.setLocation(800, 50);
        f.setVisible(true);
        Chart chart = new Chart(population.getPopulation().toString());

        controller.prepareSimulation(population);
        int counter = 0;
        for (; ; ) {
//            if (counter == 100) {
//                population.setPopulation(mementos.getMemento(1));
//            }
            mementos.addMemento(counter, new ArrayList<>(population.getPopulation()));
            for (int i = 0; i < 25; i++) {

                population.getInfected().forEach(Individual::handle);
                chart.updateList(population.getPopulation().toString());
                f.add(chart);
                f.revalidate();
                f.repaint();
                for (int j = 0; j < population.getInfected().size(); j++) {
                    Individual individual = population.getInfected().get(j);
                    individual.clearParams(population);
                    individual.getDistances(population.getNotInfected());
                    individual.getTimes(population.getNotInfected());

                    Map<String, Integer> times = individual.getIndividualParams().getTimes();

                    for (String key : times.keySet()) {
                        if (times.get(key) >= 75) {
                            population.getIndividual(key).handle(individual);
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
}
