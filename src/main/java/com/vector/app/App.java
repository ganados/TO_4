package com.vector.app;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import com.vector.app.individual.Individual;
import com.vector.app.memento.multi.Mementos;
import com.vector.app.memento.single.Memento;
import com.vector.app.room.Room;
import com.vector.app.simulation.controller.Controller;
import com.vector.app.simulation.generator.GeneratePopulation;
import com.vector.app.simulation.population.Population;

public class App {
    public static void main(String[] args) {
        Random random = new Random();
        Room room = new Room(10, 15);
        Population population = GeneratePopulation.generateNotResistPopulation(20, room);
        Mementos mementos = new Mementos(new LinkedList<>());
        Controller controller = new Controller();
        int counter = 0;
        //here
        controller.prepareSimulation(population);
        for (; ; ) {
            counter++;
            mementos.addMemento(Memento.of(counter, population));
            for (int i = 0; i < 25; i++) {
                population.getInfected().forEach(Individual::handle);
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
//                    individual.getIndividualParams().getDistances().entrySet().forEach(System.out::println);
//                    times.entrySet().forEach(System.out::println);
                    // TODO: sprawdzanie czy czas >= 75, zmiana stanu,

                    // TODO: mozliwe przeniesienie metod getDistances, getTimes do controllera, zarazanie sie,
                }
                population.getPopulation().forEach(individual1 -> individual1.generatePosition(room));
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
        }
        population.getPopulation().forEach(System.out::println);
        System.out.println("Infected: " + population.getInfected().size() + " " + "Not infected: " + population.getNotInfected().size());
    }
}
