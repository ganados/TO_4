package com.vector.app.vector.decorator;

import com.vector.app.vector.Vector2D;
import com.vector.app.vector.interfaces.IVector;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class Vector3DDecorator implements IVector {
    private IVector iVector;
    private double z;

    @Override
    public double abs() {
        double x = iVector.getComponents()[0];
        double y = iVector.getComponents()[1];
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public double cdot(final IVector ivector) {
        double[] componentsI = ivector.getComponents();
        double[] components = getComponents();
        double temp = 0;
        for (int i = 0; i < components.length; i++) {
            temp += components[i] * componentsI[i];
        }
        return temp;
    }

    @Override
    public double[] getComponents() {
        double[] components = new double[3];
        components[0] = iVector.getComponents()[0];
        components[1] = iVector.getComponents()[1];
        components[2] = this.z;
        return components;
    }

    public IVector getSrcVector() {
        return this.iVector;
    }

    public Vector3DDecorator cross(final IVector iVector) {
        double[] components = getComponents();
        double[] iVectorComponents = iVector.getComponents();
        double iVectorZ = 0;
        if (iVectorComponents.length == 3) {
            iVectorZ = iVectorComponents[2];
        }

        double x = components[1] * iVectorZ - components[2] * iVectorComponents[1];
        double y = components[2] * iVectorComponents[0] - components[0] * iVectorZ;
        double z = components[0] * iVectorComponents[1] - components[1] * iVectorComponents[0];

        return new Vector3DDecorator(new Vector2D(x, y), z);
    }
}
