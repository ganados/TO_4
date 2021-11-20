package com.vector.app.states.havesymptoms;

import java.util.Objects;
import java.util.Random;

import com.vector.app.individual.Individual;
import com.vector.app.states.IState;
import com.vector.app.states.resist.Resist;

import lombok.Setter;

import static com.vector.app.states.Constants.PROBABILITY_OF_RECOVER;

@Setter
public class HaveSymptoms implements IState {
    private final String name = "haveSymptoms";
    private int illnessCounter = 0;

    @Override
    public void handle(final Individual individual) {
        int mod = this.getIllnessCounter() / 25;
        if (mod >= 20 && mod < 30) {
            if (new Random().nextInt(PROBABILITY_OF_RECOVER) == 0) {
                individual.setState(new Resist());
            }
        } else if (mod >= 30) {
            individual.setState(new Resist());
        }
        incIllnessCounter();
    }

    public int getIllnessCounter() {
        return this.illnessCounter;
    }

    public void incIllnessCounter() {
        this.illnessCounter++;
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
