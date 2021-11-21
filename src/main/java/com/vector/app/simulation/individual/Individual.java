package com.vector.app.simulation.individual;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import com.vector.app.simulation.individualParams.IndividualParams;
import com.vector.app.simulation.population.Population;
import com.vector.app.simulation.room.Room;
import com.vector.app.states.IState;
import com.vector.app.states.havenotsymptoms.HaveNotSymptoms;
import com.vector.app.vector.Vector2D;
import com.vector.app.vector.interfaces.IVector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static com.vector.app.states.Constants.MAX_DISTANCE;
import static com.vector.app.states.Constants.PROBABILITY_OF_INFECT;
import static com.vector.app.states.Constants.PROBABILITY_OF_NEGATIVE;
import static com.vector.app.states.Constants.PROBABILITY_OF_RETURN;

@Setter
@Getter
@Builder
@AllArgsConstructor(staticName = "of")
public class Individual {

    private String id;
    private IState state;
    private IndividualParams individualParams;

    private Boolean isInRoom;

    private double positionX;
    private double positionY;

    @Override
    public String toString() {
        return state.getName() + "-" + positionX + ";" + positionY;
    }

    public void setPosition(final IVector iVector) {
        double[] components = iVector.getComponents();
        this.positionX += components[0];
        this.positionY += components[1];
    }

    public void generatePosition(final Room room, final Random random) {
        double x = random.nextDouble() * MAX_DISTANCE;
        double y = random.nextDouble() * (MAX_DISTANCE - x);
        if (random.nextInt(PROBABILITY_OF_NEGATIVE) < 2) {
            x *= -1;
            y *= -1;
        }

        IVector iVector = new Vector2D(x, y);
        this.setPosition(iVector);
        if (getPositionX() > room.getWidth()) {
            if (random.nextInt(PROBABILITY_OF_RETURN) == 0) {
                this.setPositionX(getPositionX() - 1);
            } else {
                this.setIsInRoom(false);
            }
        } else if (getPositionX() < 0) {
            if (random.nextInt(PROBABILITY_OF_RETURN) == 0) {
                this.setPositionX(getPositionX() + 1);
            } else {
                this.setIsInRoom(false);
            }
        } else if (getPositionY() > room.getHeight()) {
            if (random.nextInt(PROBABILITY_OF_RETURN) == 0) {
                this.setPositionY(getPositionY() - 1);
            } else {
                this.setIsInRoom(false);
            }
        } else if (getPositionY() < 0) {
            if (random.nextInt(PROBABILITY_OF_RETURN) == 0) {
                this.setPositionY(getPositionY() + 1);
            } else {
                this.setIsInRoom(false);
            }
        }
    }

    public IState getState() {
        return state;
    }

    public void setState(final IState state) {
        this.state = state;
    }

    public void handle() {
        this.state.handle(this);
    }

    public void handle(final Individual individual) {
        if (individual.getState().equals(new HaveNotSymptoms())) {
            if (new Random().nextInt(PROBABILITY_OF_INFECT) == 0) {
                this.handle();
            }
        } else {
            this.handle();
        }
    }

    public double getDistance(final Individual individual) {
        return Math.sqrt(Math.pow(individual.getPositionX() - this.getPositionX(), 2) + Math.pow(individual.getPositionY() - this.getPositionY(), 2));
    }

    public Map<String, Double> getDistances(final List<Individual> individuals) {
        Map<String, Double> distances = individualParams.getDistances();
        for (Individual individual : individuals) {
            distances.put(individual.getId(), this.getDistance(individual));
        }
        return distances;
    }

    public Map<String, Integer> getTimes(final List<Individual> individuals) {
        Map<String, Integer> times = individualParams.getTimes();
        Map<String, Double> distances = individualParams.getDistances();
        for (Individual individual : individuals) {
            if (times.get(individual.getId()) == null) {
                times.put(individual.getId(), 0);
            } else if (distances.get(individual.getId()) <= 2) {
                int currentTime = times.get(individual.getId());
                times.put(individual.getId(), ++currentTime);
            } else {
                times.put(individual.getId(), 0);
            }
        }
        return times;
    }

    public void clearParams(final Population population) {
        Map<String, Integer> times = getIndividualParams().getTimes();
        Map<String, Double> distances = getIndividualParams().getDistances();
        times.keySet().removeIf(s -> population.getIndividual(s) == null);
        distances.keySet().removeIf(s -> population.getIndividual(s) == null);
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
