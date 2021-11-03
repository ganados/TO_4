package com.vector.app.vector.adapter;

import com.vector.app.vector.Vector2D;
import com.vector.app.vector.interfaces.IPolar2D;
import com.vector.app.vector.interfaces.IVector;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class Polar2DAdapter implements IVector, IPolar2D {
    private Vector2D srcVector;

    @Override
    public double abs() {
        return this.srcVector.abs();
    }

    @Override
    public double cdot(final IVector ivector) {
        return this.srcVector.cdot(ivector);
    }

    @Override
    public double[] getComponents() {
        return this.srcVector.getComponents();
    }

    @Override
    public double getAngle(){
        return Math.atan(this.getComponents()[1] / this.getComponents()[0]);
    }
}
