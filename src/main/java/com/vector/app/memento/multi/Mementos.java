package com.vector.app.memento.multi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vector.app.simulation.individual.Individual;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mementos {
    private Map<Integer, List<Individual>> mementos;

    public void addMemento(final int position, List<Individual> individuals) {
        this.mementos.put(position, individuals);
    }

    public List<Individual> getMemento(final int position) {
        return mementos.get(position);
    }

    public List<Individual> getMementoCopy(final int position) {
        return new ArrayList<>(mementos.get(position));
    }

    public String[] getIndexes() {
        return mementos.keySet().stream().map(Object::toString).toArray(String[]::new);
    }
}
