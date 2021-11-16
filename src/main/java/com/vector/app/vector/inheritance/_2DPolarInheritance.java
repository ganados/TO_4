package com.vector.app.vector.inheritance;

import com.vector.app.vector.Vector2D;

public class _2DPolarInheritance extends Vector2D {

    public _2DPolarInheritance(final double x, final double y) {
        super(x, y);
    }

    public double getAngle() {

        return Math.toDegrees(Math.atan(getComponents()[1] / getComponents()[0]));
    }
}
