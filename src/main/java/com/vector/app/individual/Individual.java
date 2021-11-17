package com.vector.app.individual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.vector.app.states.IState;
import com.vector.app.vector.interfaces.IVector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor(staticName = "of")
public class Individual {
    private String id;
    private IState state;

    private Boolean isInRoom;

    private double positionX;
    private double positionY;

    public void setPosition(final IVector iVector){
        double[] components = iVector.getComponents();
        this.positionX += components[0];
        this.positionY += components[1];
    }

    public IState getState() {
        return state;
    }

    public void setState(final IState state) {
        this.state = state;
    }

    public double getDistance(final Individual individual) {
        return Math.sqrt(Math.pow(individual.getPositionX() - this.getPositionX(), 2) + Math.pow(individual.getPositionY() - this.getPositionY(), 2));
    }

    public Map<String, Double> getDistances(final List<Individual> individuals){
        Map<String, Double> distances = new HashMap<>();
        for(Individual individual : individuals){
            distances.put(individual.getId(), this.getDistance(individual));
        }
        return distances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individual that = (Individual) o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
