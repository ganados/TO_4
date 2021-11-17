package com.vector.app.simulation.population;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.vector.app.individual.Individual;
import com.vector.app.states.IState;
import com.vector.app.states.healthy.Healthy;
import com.vector.app.states.unhealthy.NotHealthy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class Population {
    private List<Individual> population;

    public void addIndividual(final Individual individual) {
        this.population.add(individual);
    }

    public void addIndividuals(final List<Individual> individuals) {
        this.population.addAll(individuals);
    }

    public Individual getIndividual(final String id) {
        for(Individual individual : population) {
            if(individual.getId().equals(id)){
                return individual;
            }
        }
        return null;
    }

    public List<Individual> getInfected() {
        NotHealthy notHealthy = new NotHealthy();
        return getIndividuals(notHealthy);
    }

    public List<Individual> getNotInfected() {
        Healthy healthy = new Healthy();
        return getIndividuals(healthy);
    }

    private List<Individual> getIndividuals(IState state) {
        List<Individual> individuals = new LinkedList<>();
        for(Individual individual : population) {
            if(individual.getState().equals(state)) {
                individuals.add(individual);
            }
        }
        return individuals;
    }
}
