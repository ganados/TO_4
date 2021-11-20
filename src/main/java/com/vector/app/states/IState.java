package com.vector.app.states;

import com.vector.app.individual.Individual;

public interface IState {
    void handle(final Individual individual);
    String getName();
}
