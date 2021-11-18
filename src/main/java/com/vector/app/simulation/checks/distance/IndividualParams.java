package com.vector.app.simulation.checks.distance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor(staticName = "of")
public class IndividualParams {
    private String id;
 //   private CheckDistance checkDistance;
    private TimeDistance timeDistance;
}
