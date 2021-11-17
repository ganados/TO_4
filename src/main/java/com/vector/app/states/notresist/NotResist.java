package com.vector.app.states.notresist;

import com.vector.app.individual.Individual;
import com.vector.app.states.IState;

public class NotResist implements IState {
    @Override
    public void handle(final Individual individual) {
        individual.setState(new NotResist());
        System.out.println("Not resist");
    }
}
