package com.vector.app.individual;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import com.vector.app.states.IState;
import com.vector.app.vector.Vector2D;
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
    private static final double MAX_DISTANCE = 0.1;

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

    public LinkedHashMap<String, Double> getDistances(final List<Individual> individuals){
        LinkedHashMap<String, Double> distances = new LinkedHashMap<>();
        for(Individual individual : individuals){
            distances.put(individual.getId(), this.getDistance(individual));
        }
        return distances;
    }

    public LinkedHashMap<String, Integer> getTimes(final List<Individual> individuals, final LinkedHashMap<String, Double> distances){
        LinkedHashMap<String, Integer> times = new LinkedHashMap<>();
        for(Individual individual : individuals){
            if(times.get(individual.getId()) == null ){
                times.put(individual.getId(), 0);
            }
            else if(distances.get(individual.getId()) <= 2){
                int currentTime = times.get(individual.getId());
                times.put(individual.getId(), currentTime++);
            }
        }
        return times;
    }

    public void generatePosition(){
        Random random = new Random();
        double x = random.nextDouble() * MAX_DISTANCE;
        double y = random.nextDouble() * (MAX_DISTANCE - x);
        IVector iVector = new Vector2D(x, y);
        this.setPosition(iVector);
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
