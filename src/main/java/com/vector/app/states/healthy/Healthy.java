package com.vector.app.states.healthy;

import java.util.Objects;
import java.util.Random;

import com.vector.app.simulation.individual.Individual;
import com.vector.app.states.IState;
import com.vector.app.states.havenotsymptoms.HaveNotSymptoms;
import com.vector.app.states.havesymptoms.HaveSymptoms;

import static com.vector.app.states.Constants.PROBABILITY_OF_SYMPTOMS;

public class Healthy implements IState {

    private final String name = "healthy";

    @Override
    public void handle(final Individual individual) {
        if (!individual.getState().equals(new HaveSymptoms()) || !individual.getState().equals(new HaveNotSymptoms())) {
            if (new Random().nextInt(PROBABILITY_OF_SYMPTOMS) == 0) {
                individual.setState(new HaveSymptoms());
            } else {
                individual.setState(new HaveNotSymptoms());
            }
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IState that = (IState) o;
        return Objects.equals(this.getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
