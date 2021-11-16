package com.vector.app.individual;

import com.vector.app.vector.interfaces.IVector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor(staticName = "of")
public class Individual {
    private Boolean isResist;
    private Boolean isHealthy;
    private Boolean haveSymptoms;

    private double positionX;
    private double positionY;

    public void setPosition(IVector iVector){

    }
}
