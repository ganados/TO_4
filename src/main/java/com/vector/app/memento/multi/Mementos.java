package com.vector.app.memento.multi;

import java.util.LinkedList;

import com.vector.app.memento.single.Memento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mementos {
    private LinkedList<Memento> mementos;

    public void addMemento(final Memento memento) {
        this.mementos.add(memento);
    }

    public Memento getMemento(final int position) {
        for (Memento memento : mementos) {
            if (memento.getCounter() == position) {
                return memento;
            }
        }
        return null;
    }
}
