package model;

import transforms.Col;
import transforms.Point3D;

import java.awt.*;

public class Cube extends Solid {


    public Cube() {


        geometry.add(new Vertex(new Point3D(-1, -1, -1))); // 0.bod
        geometry.add(new Vertex(new Point3D(1, -1, -1))); // 1.bod
        geometry.add(new Vertex(new Point3D(1, 1, -1))); // 2.bod
        geometry.add(new Vertex(new Point3D(-1, 1, -1))); // 3.bod
        geometry.add(new Vertex(new Point3D(-1, -1, 1))); // 4.bod
        geometry.add(new Vertex(new Point3D(1, -1, 1))); // 5.bod
        geometry.add(new Vertex(new Point3D(1, 1, 1))); // 6.bod
        geometry.add(new Vertex(new Point3D(-1, 1, 1))); // 7.bod*/

        // spodní strana
        indicies.add(0);
        indicies.add(1);
        indicies.add(2);

        indicies.add(0);
        indicies.add(2);
        indicies.add(3);

        // přední strana
        indicies.add(0);
        indicies.add(1);
        indicies.add(5);

        indicies.add(0);
        indicies.add(4);
        indicies.add(5);

        // pravá strana
        indicies.add(1);
        indicies.add(2);
        indicies.add(6);

        indicies.add(1);
        indicies.add(5);
        indicies.add(6);

        //zadni strana
        indicies.add(2);
        indicies.add(3);
        indicies.add(7);

        indicies.add(2);
        indicies.add(6);
        indicies.add(7);

        // levá strana
        indicies.add(3);
        indicies.add(0);
        indicies.add(4);

        indicies.add(3);
        indicies.add(4);
        indicies.add(7);

        // horní strana
        indicies.add(4);
        indicies.add(5);
        indicies.add(6);

        indicies.add(4);
        indicies.add(6);
        indicies.add(7);



        parts.add(new Part(Type.TRIANGLES, 0, 12));
    }
}
