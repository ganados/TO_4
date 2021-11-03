package com.vector.app;

import java.util.Arrays;

import com.vector.app.vector.Vector2D;
import com.vector.app.vector.inheritance._2DPolarInheritance;
import com.vector.app.vector.adapter.Polar2DAdapter;
import com.vector.app.vector.decorator.Vector3DDecorator;
import com.vector.app.vector.inheritance.Vector3DInheritance;


public class App {
    public static void main(String[] args) {
        Vector2D vector2D = new Vector2D(4, 5);
        Polar2DAdapter polar2DAdapter = new Polar2DAdapter(vector2D);
        _2DPolarInheritance polarInheritance = new _2DPolarInheritance(3, 2);

        System.out.println("1 cdot:\t\t" + vector2D.cdot(polar2DAdapter));
        System.out.println("2 cdot:\t\t" + vector2D.cdot(polarInheritance));
        System.out.println("3 abs:\t\t" + vector2D.abs());

        System.out.println("4 cdot:\t\t" + polarInheritance.cdot(vector2D));
        System.out.println("5 cdot:\t\t" + polarInheritance.cdot(polar2DAdapter));
        System.out.println("6 angle:\t" + polarInheritance.getAngle());

        System.out.println("7 cdot:\t\t" + polar2DAdapter.cdot(vector2D));
        System.out.println("8 cdot:\t\t" + polar2DAdapter.cdot(polarInheritance));
        System.out.println("9 angle:\t" + polar2DAdapter.getAngle());


        System.out.println("\n\n3D");

        Vector3DInheritance vector3DInheritance = new Vector3DInheritance(1, 2, 3);
        Vector3DDecorator vector3DDecorator = new Vector3DDecorator(new Vector2D(2, 5), 4);

        System.out.println("1 dot:\t\t" + vector3DInheritance.cdot(vector3DDecorator));
        System.out.println("2 cross:\t" + Arrays.toString(vector3DInheritance.cross(vector3DDecorator).getComponents()));

        System.out.println("3 dot:\t\t" + vector3DDecorator.cdot(vector3DInheritance));
        System.out.println("4 cross:\t" + Arrays.toString(vector3DDecorator.cross(vector3DInheritance).getComponents()));
    }
}
