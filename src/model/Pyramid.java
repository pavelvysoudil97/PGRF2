package model;

import transforms.Point3D;



public class Pyramid extends Solid {


    public Pyramid(double inputSize) {

        double size = inputSize / 2;

        geometry.add(new Vertex(new Point3D(0, 0, size)));
        geometry.add(new Vertex(new Point3D(size, 0, size)));
        geometry.add(new Vertex(new Point3D(size, size, size)));
        geometry.add(new Vertex(new Point3D(0, size, size)));
        geometry.add(new Vertex(new Point3D(0.5 * size, 0.5 * size, size * 2)));

        indicies.add(0); indicies.add(1); indicies.add(2);
        indicies.add(0);  indicies.add(2); indicies.add(3);
        indicies.add(0); indicies.add(1); indicies.add(4);
        indicies.add(1); indicies.add(2); indicies.add(4);
        indicies.add(2); indicies.add(3); indicies.add(4);
        indicies.add(0); indicies.add(3); indicies.add(4);


        parts.add(new Part(Type.TRIANGLES, 0, 6));
    }
}

