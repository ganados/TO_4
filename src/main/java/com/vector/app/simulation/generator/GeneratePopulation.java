package com.vector.app.simulation.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vector.app.individual.Ids;
import com.vector.app.individual.Individual;
import com.vector.app.simulation.population.Population;
import com.vector.app.states.IState;
import com.vector.app.states.havenotsymptoms.HaveNotSymptoms;
import com.vector.app.states.havesymptoms.HaveSymptoms;
import com.vector.app.states.healthy.Healthy;
import com.vector.app.states.resist.Resist;

public class GeneratePopulation {
    private static final int PROBABILITY_OF_ILL = 10;
    private static final int PROBABILITY_OF_RESIST = 2;
    private static final int PROBABILITY_OF_SYMPTOMS = 2;

    private static final Random random = new Random();

    public static Population generateNotResistPopulation(final int numerous) {
        List<Individual> individuals = new ArrayList<>();
        IState iState;
        for (int i = 0; i < numerous; i++) {
            individuals.add(getNotResistIndividual());
        }
        return Population.of(individuals);
    }

    public static Individual getNotResistIndividual() {
        IState iState;
        if(random.nextInt(PROBABILITY_OF_ILL) == 0){
            iState = new Healthy();
        }
        else if(random.nextInt(PROBABILITY_OF_SYMPTOMS) == 0) {
            iState = new HaveSymptoms();
        }
        else {
            iState = new HaveNotSymptoms();
        }
        return Individual.of(Ids.createID(), iState, true, 0, 0);
    }

    public static Population generateResistPopulation(final int numerous) {
        List<Individual> individuals = new ArrayList<>();
        for (int i = 0; i < numerous; i++) {
            individuals.add(generateIndividual());
        }
        return Population.of(individuals);
    }

    public static Individual generateIndividual() {
        IState iState;
        if(random.nextInt(PROBABILITY_OF_RESIST) == 0) {
            iState = new Resist();
        }
        else if(random.nextInt(PROBABILITY_OF_ILL) == 0){
            iState = new Healthy();
        }
        else if(random.nextInt(PROBABILITY_OF_SYMPTOMS) == 0) {
            iState = new HaveSymptoms();
        }
        else {
            iState = new HaveNotSymptoms();
        }
        return Individual.of(Ids.createID(), iState, true, 0, 0);
        // TODO: poprawic generowanie
    }
}
