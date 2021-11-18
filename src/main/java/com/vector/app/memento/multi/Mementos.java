package com.vector.app.memento.multi;

import java.util.List;

import com.vector.app.memento.single.Memento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mementos {
    private List<Memento> mementos;

    public void addMemento(final Memento memento){
        this.mementos.add(memento);
    }
}
