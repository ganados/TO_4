package com.vector.app.simulation.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vector.app.individual.Individual;
import com.vector.app.simulation.population.Population;

public class GeneratePopulation {
    public static final int PROBABILITY_OF_ILL = 10;
    public static final int PROBABILITY_OF_SYMPTOMS = 2;

    private static final Random random = new Random();

    public static Population generateNotResistPopulation(final int numerous) {
        List<Individual> individuals = new ArrayList<>();
        for (int i = 0; i < numerous; i++) {
            Boolean isHealthy = random.nextInt(PROBABILITY_OF_ILL) == 0;
            boolean haveSymptoms = false;

            if (!isHealthy) {
                haveSymptoms = random.nextInt(PROBABILITY_OF_SYMPTOMS) == 0;
            }
            individuals.add(Individual
                    .builder()
                    .isResist(false)
                    .isHealthy(isHealthy)
                    .haveSymptoms(haveSymptoms)
                    .build()
            );
        }
        return new Population(individuals);
    }

    public static Population generateResistPopulation(final int numerous) {
        List<Individual> individuals = new ArrayList<>();
        for (int i = 0; i < numerous; i++) {
            individuals.add(generateIndividual());
        }
        return new Population(individuals);
    }

    public static Individual generateIndividual() {
        Boolean isResist = random.nextInt(PROBABILITY_OF_ILL) == 0;
        boolean isHealthy = false;
        boolean haveSymptoms = false;

        if (!isResist) {
            isHealthy = random.nextInt(PROBABILITY_OF_ILL) == 0;
        }

        if (!isHealthy) {
            haveSymptoms = random.nextInt(PROBABILITY_OF_SYMPTOMS) == 0;
        }
        return Individual
                .builder()
                .isResist(isResist)
                .isHealthy(isHealthy)
                .haveSymptoms(haveSymptoms)
                .build();
    }
}
