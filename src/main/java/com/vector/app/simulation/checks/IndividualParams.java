package com.vector.app.simulation.checks;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor(staticName = "of")
public class IndividualParams {
    private Map<String, Double> distances;
    private Map<String, Integer> times;

    public double getIndividualDistance(final String id) {
        return distances.get(id);
    }

    public double getIndividualTime(final String id) {
        return times.get(id);
    }
}
