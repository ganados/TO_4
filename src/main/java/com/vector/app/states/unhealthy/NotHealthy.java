package com.vector.app.states.unhealthy;

import com.vector.app.individual.Individual;
import com.vector.app.states.IState;

public class NotHealthy implements IState {
    @Override
    public void handle(final Individual individual) {
        individual.setState(new NotHealthy());
        System.out.println("Not healthy");
    }
}
