package com.vector.app.simulation.generator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import com.vector.app.simulation.individual.Ids;
import com.vector.app.simulation.individual.Individual;
import com.vector.app.simulation.room.Room;
import com.vector.app.simulation.individualParams.IndividualParams;
import com.vector.app.simulation.population.Population;
import com.vector.app.states.IState;
import com.vector.app.states.havenotsymptoms.HaveNotSymptoms;
import com.vector.app.states.havesymptoms.HaveSymptoms;
import com.vector.app.states.healthy.Healthy;
import com.vector.app.states.resist.Resist;

import static com.vector.app.states.Constants.PROBABILITY_OF_ENTRY;
import static com.vector.app.states.Constants.PROBABILITY_OF_ILL;
import static com.vector.app.states.Constants.PROBABILITY_OF_RESIST;
import static com.vector.app.states.Constants.PROBABILITY_OF_SYMPTOMS;

public class GeneratePopulation {


    private static final Random random = new Random();

    public static Population generateNotResistPopulation(final int numerous, final Room room) {
        List<Individual> individuals = new ArrayList<>();
        for (int i = 0; i < numerous; i++) {
            individuals.add(getNotResistIndividual(room));
        }
        return Population.of(individuals);
    }

    public static Individual getNotResistIndividual(final Room room) {
        IState iState;
        if (random.nextInt(PROBABILITY_OF_ILL) != 0) {
            iState = new Healthy();
        } else if (random.nextInt(PROBABILITY_OF_SYMPTOMS) == 0) {
            iState = new HaveSymptoms();
        } else {
            iState = new HaveNotSymptoms();
        }

        return getIndividual(room, iState);
    }

    public static Population generateResistPopulation(final int numerous, final Room room) {
        List<Individual> individuals = new ArrayList<>();
        for (int i = 0; i < numerous; i++) {
            individuals.add(generateResistIndividual(room));
        }
        return Population.of(individuals);
    }

    public static Individual generateResistIndividual(final Room room) {
        IState iState;
        if (random.nextInt(PROBABILITY_OF_RESIST) == 0) {
            iState = new Resist();
        } else if (random.nextInt(PROBABILITY_OF_ILL) == 0) {
            iState = new Healthy();
        } else if (random.nextInt(PROBABILITY_OF_SYMPTOMS) == 0) {
            iState = new HaveSymptoms();
        } else {
            iState = new HaveNotSymptoms();
        }
        return getIndividual(room, iState);
    }

    private static Individual getIndividual(final Room room, final IState iState) {
        double x = 0;
        double y = 0;
        if (random.nextInt(PROBABILITY_OF_ENTRY) == 0) {
            x = random.nextDouble() * room.getWidth();
        } else {
            y = random.nextDouble() * room.getHeight();
        }
        LinkedHashMap<String, Double> distances = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> times = new LinkedHashMap<>();
        IndividualParams individualParams = IndividualParams.of(distances, times);
        return Individual.of(Ids.createID(), iState, individualParams, true, x, y);
    }
}
