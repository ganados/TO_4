package com.vector.app.simulation.infection;

import com.vector.app.individual.Individual;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class CheckInfection {
    private Individual firstIndividual;
    private Individual secondIndividual;
    private int time = 0;
}
