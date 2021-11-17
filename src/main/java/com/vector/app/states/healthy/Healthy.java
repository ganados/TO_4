package com.vector.app.states.healthy;

import com.vector.app.individual.Individual;
import com.vector.app.states.IState;

public class Healthy implements IState {
    @Override
    public void handle(final Individual individual) {
        System.out.println("healthy");
    }
}
