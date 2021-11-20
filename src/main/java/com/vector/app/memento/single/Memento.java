package com.vector.app.memento.single;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.vector.app.individual.Individual;
import com.vector.app.simulation.population.Population;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(staticName = "of")
public class Memento {
    private int counter;
    private Population population;

    @Override
    public String toString(){
        for(Individual individual : population.getPopulation()){
            System.out.println(counter);
            System.out.println(individual.toString());
        }
        return "";
    }
}
