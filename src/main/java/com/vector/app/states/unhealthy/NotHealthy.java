package com.vector.app.states.unhealthy;

import java.util.Objects;

import com.vector.app.individual.Individual;
import com.vector.app.states.IState;

public class NotHealthy implements IState {

    private final String name = "notHealthy";

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
