package com.vector.app.simulation.population;

import java.util.LinkedList;
import java.util.List;

import com.vector.app.individual.Individual;
import com.vector.app.states.IState;
import com.vector.app.states.havenotsymptoms.HaveNotSymptoms;
import com.vector.app.states.havesymptoms.HaveSymptoms;
import com.vector.app.states.healthy.Healthy;
import com.vector.app.states.resist.Resist;

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
        for (Individual individual : population) {
            if (individual.getId().equals(id)) {
                return individual;
            }
        }
        return null;
    }

    public void deleteIfExited() {
        for (int i = 0; i < this.population.size(); i++) {
            if (!population.get(i).getIsInRoom()) {
                this.population.remove(i);
            }
        }
    }

    public List<Individual> getInfected() {
        List<Individual> individuals = new LinkedList<>();
        individuals.addAll(getHaveSymptoms());
        individuals.addAll(getHaveNotSymptoms());
        return individuals;
    }

    private List<Individual> getHaveSymptoms() {
        return getIndividuals(new HaveSymptoms());
    }

    private List<Individual> getHaveNotSymptoms() {
        return getIndividuals(new HaveNotSymptoms());
    }

    public List<Individual> getNotInfected() {
        List<Individual> individuals = new LinkedList<>();
        individuals.addAll(getResist());
        individuals.addAll(getHealthy());
        return individuals;
    }

    private List<Individual> getResist() {
        return getIndividuals(new Resist());
    }

    private List<Individual> getHealthy() {
        return getIndividuals(new Healthy());
    }

    public List<Individual> getPossibleInfected() {
        return getHealthy();
    }

    private List<Individual> getIndividuals(IState state) {
        List<Individual> individuals = new LinkedList<>();
        for (Individual individual : population) {
            if (individual.getState().equals(state)) {
                individuals.add(individual);
            }
        }
        return individuals;
    }
}
