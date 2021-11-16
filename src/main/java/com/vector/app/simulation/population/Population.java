package com.vector.app.simulation.population;

import java.util.List;

import com.vector.app.individual.Individual;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Population {
    List<Individual> population;

    public void addIndividual(final Individual individual) {
        this.population.add(individual);
    }

    public void addIndividuals(final List<Individual> individuals) {
        this.population.addAll(individuals);
    }
}
