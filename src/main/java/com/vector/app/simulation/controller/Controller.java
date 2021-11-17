package com.vector.app.simulation.controller;

import java.util.List;

import com.vector.app.individual.Individual;
import com.vector.app.memento.multi.Mementos;
import com.vector.app.room.Room;
import com.vector.app.simulation.population.Population;
import com.vector.app.states.IState;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class Controller {

    private static final int MAX_DISTANCE = 2;

    private Room room;
    private Population population;
    private IState state;
    private Mementos mementos;

    public void makeSimulation(){
        List<Individual> individuals = this.population.getInfected();
        for(int i = 0; i< this.population.getPopulation().size(); i++) {
            Individual individual = individuals.get(i);
            for(int j = i; j< this.population.getPopulation().size(); j++) {
                Individual compareIndividual = individuals.get(j);
                double distance = individual.getDistance(compareIndividual);
                if(distance < MAX_DISTANCE) {

                }
            }
        }
    }


    // TODO: Time service (25 steps for 1 second), speedVector drawing,

}
