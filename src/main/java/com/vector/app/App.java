package com.vector.app;

import java.util.HashMap;

import com.vector.app.memento.multi.Mementos;
import com.vector.app.simulation.controller.Controller;
import com.vector.app.simulation.generator.GeneratePopulation;
import com.vector.app.simulation.population.Population;
import com.vector.app.simulation.room.Room;

public class App {
    public static void main(String[] args) {

        Room room = new Room(40, 30);
        Population population = GeneratePopulation.generateNotResistPopulation(500, room);
        Mementos mementos = new Mementos(new HashMap<>());

        Controller controller = Controller.builder()
                .room(room)
                .population(population)
                .mementos(mementos)
                .build();
        controller.makeSimulation();
    }
}
