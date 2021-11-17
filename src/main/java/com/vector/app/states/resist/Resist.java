package com.vector.app.states.resist;

import com.vector.app.individual.Individual;
import com.vector.app.states.IState;

public class Resist implements IState {
    @Override
    public void handle(final Individual individual) {
        individual.setState(new Resist());
        System.out.println("resist");
    }
}
