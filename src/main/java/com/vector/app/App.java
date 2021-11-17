package com.vector.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.vector.app.individual.Ids;
import com.vector.app.individual.Individual;
import com.vector.app.memento.single.Memento;
import com.vector.app.simulation.checks.distance.CheckDistance;
import com.vector.app.simulation.checks.distance.IndividualParams;
import com.vector.app.simulation.checks.distance.TimeDistance;
import com.vector.app.simulation.generator.GeneratePopulation;
import com.vector.app.simulation.population.Population;

public class App {
    public static void main(String[] args) {
        Random random = new Random();
        List<Individual> individualList = new ArrayList<>();
        for (int j =0; j< 10; j++ ) {

            for (int i = 0; i < 75; i++) {
                individualList.add(
                    Individual.builder()
                            .id(Ids.createID())
                            .positionX(random.nextDouble() * random.nextInt(10000))
                            .positionY(random.nextDouble() * random.nextInt(10000))
                            .build());
            }
        }
        List<IndividualParams> individualParams = new LinkedList<>();
        for(Individual individual : individualList){
            Map<String, Double> distances = individual.getDistances(individualList);
            CheckDistance checkDistance = CheckDistance.of(individual, distances);
            TimeDistance timeDistance = TimeDistance.of(null, null);

            individualParams.add(IndividualParams.of(individual.getId(), checkDistance, timeDistance));
        }

        for(IndividualParams individualParam: individualParams){
            Map<String, Double> distances = individualParam.getCheckDistance().getDistances();
            for(String key : distances.keySet()) {
                System.out.printf("Id: %s\nDistance: %f\n\n%n", key, distances.get(key));
            }
        }
    }
}
