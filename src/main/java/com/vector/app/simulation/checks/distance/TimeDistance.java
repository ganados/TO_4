package com.vector.app.simulation.checks.distance;

import java.util.Map;

import com.vector.app.individual.Individual;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor(staticName = "of")
public class TimeDistance {
    private Individual first;
    private Map<String, Integer> times;
}
