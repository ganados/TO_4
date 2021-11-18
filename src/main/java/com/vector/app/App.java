package com.vector.app;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.vector.app.individual.Individual;
import com.vector.app.memento.multi.Mementos;
import com.vector.app.memento.single.Memento;
import com.vector.app.room.Room;
import com.vector.app.simulation.checks.distance.IndividualParams;
import com.vector.app.simulation.checks.distance.TimeDistance;
import com.vector.app.simulation.controller.Controller;
import com.vector.app.simulation.generator.GeneratePopulation;
import com.vector.app.simulation.population.Population;

public class App {
    public static void main(String[] args) {
        Random random = new Random();
        Room room = new Room(10, 15);
        Population population = GeneratePopulation.generateNotResistPopulation(50);
        Mementos mementos = new Mementos();
        List<Memento> mementoList = new LinkedList<>();

        Controller controller = Controller.of(room, population, mementos);
        List<TimeDistance> timeDistances = new LinkedList<>();
        List<IndividualParams> individualParams = new LinkedList<>();

        for (; ; ) {
            for (int i = 0; i < 75; i++) {
                for(int j = 0; j< population.getPopulation().size(); j++){
                    Individual individual = population.getPopulation().get(i);
                    List<Individual> comparedIndividuals = new LinkedList<>();
                    for(int k =j; k<population.getPopulation().size(); k++){
                        comparedIndividuals.add(population.getPopulation().get(j));
                    }
                    LinkedHashMap<String, Double> distances = individual.getDistances(comparedIndividuals);
                    LinkedHashMap<String, Integer> times = individual.getTimes(comparedIndividuals, distances);

                    // TODO: sprawdzanie czy czas >= 75, zmiana stanu, czy wychodzi poza obszar, 50% na powrot jesli tak
                }
            }
        }
    }
}
