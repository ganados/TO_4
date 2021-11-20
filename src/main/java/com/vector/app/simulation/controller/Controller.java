package com.vector.app.simulation.controller;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.vector.app.individual.Individual;
import com.vector.app.simulation.checks.IndividualParams;
import com.vector.app.simulation.population.Population;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Controller {


    public void prepareSimulation(final Population population) {
        for (int j = 0; j < population.getInfected().size(); j++) {
            Individual individual = population.getInfected().get(j);
            List<Individual> comparedIndividuals = new LinkedList<>(population.getPossibleInfected());
            Map<String, Double> distances = individual.getDistances(comparedIndividuals);
            Map<String, Integer> times = individual.getTimes(comparedIndividuals);

            individual.setIndividualParams(IndividualParams.of(distances, times));
        }
    }
}
