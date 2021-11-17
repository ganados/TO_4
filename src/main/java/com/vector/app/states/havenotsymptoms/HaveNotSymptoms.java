package com.vector.app.states.havenotsymptoms;

import com.vector.app.individual.Individual;
import com.vector.app.states.IState;

public class HaveNotSymptoms implements IState {
    @Override
    public void handle(final Individual individual) {
        individual.setState(new HaveNotSymptoms());
        System.out.println("Have no symptoms");
    }
}
