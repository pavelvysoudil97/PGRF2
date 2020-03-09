package model;

import transforms.Col;
import transforms.Point3D;

public class Arrow extends Solid {

public Arrow(){
    int max = 1;

    geometry.add(new Vertex(new Point3D(0,0,0)));
    geometry.add(new Vertex(new Point3D(50,0,0)));
    geometry.add(new Vertex(new Point3D(0,50,0)));
    geometry.add(new Vertex(new Point3D(0,0,50)));

    indicies.add(0);
    indicies.add(1);
    indicies.add(0);
    indicies.add(2);
    indicies.add(0);
    indicies.add(3);

    parts.add(new Part(Type.LINES, 0, 3, new Col(255,0,255)));
}
}
