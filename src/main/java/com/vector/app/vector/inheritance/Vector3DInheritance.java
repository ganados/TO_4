package com.vector.app.vector.inheritance;

import com.vector.app.vector.Vector2D;
import com.vector.app.vector.interfaces.IVector;

import lombok.ToString;

@ToString
public class Vector3DInheritance extends Vector2D {
    private double z;

    public Vector3DInheritance(final double x, final double y, final double z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public double abs() {
        double x = super.getComponents()[0];
        double y = super.getComponents()[1];
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public double cdot(final IVector iVector) {
        double[] componentsI = iVector.getComponents();
        double[] components = this.getComponents();
        double temp = 0;
        if (componentsI.length == 2) {
            components[2] = 0.0;
        }
        for (int i = 0; i < components.length; i++) {
            temp += components[i] * componentsI[i];
        }
        return temp;
    }

    @Override
    public double[] getComponents() {
        double[] components = new double[3];
        components[0] = super.getComponents()[0];
        components[1] = super.getComponents()[1];
        components[2] = this.z;
        return components;
    }

    public IVector getSrcV() {
        return new Vector3DInheritance(getComponents()[0], getComponents()[1], this.z);
    }

    public Vector3DInheritance cross(final IVector iVector) {
        double[] components = getComponents();
        double[] iVectorComponents = iVector.getComponents();
        double iVectorZ = 0;
        if (iVectorComponents.length == 3) {
            iVectorZ = iVectorComponents[2];
        }

        double x = components[1] * iVectorZ - components[2] * iVectorComponents[1];
        double y = components[2] * iVectorComponents[0] - components[0] * iVectorZ;
        double z = components[0] * iVectorComponents[1] - components[1] * iVectorComponents[0];

        return new Vector3DInheritance(x, y, z);
    }
}
