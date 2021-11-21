package com.vector.app.states;

import com.vector.app.simulation.individual.Individual;

public interface IState {
    void handle(final Individual individual);

    String getName();
}
