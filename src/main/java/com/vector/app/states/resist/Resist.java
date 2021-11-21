package com.vector.app.states.resist;

import java.util.Objects;

import com.vector.app.simulation.individual.Individual;
import com.vector.app.states.IState;

public class Resist implements IState {

    private final String name = "resist";

    @Override
    public void handle(final Individual individual) {
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
