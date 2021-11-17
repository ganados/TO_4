package com.vector.app.states.havesymptoms;

import com.vector.app.individual.Individual;
import com.vector.app.states.IState;

public class HaveSymptoms implements IState {
    @Override
    public void handle(final Individual individual) {
        individual.setState(new HaveSymptoms());
        System.out.println("Have symptoms");
    }
}
