package model;

import transforms.Point3D;

public class Arrow extends Solid {

public Arrow(){
    int max = 1;

    geometry.add(new Vertex(new Point3D(0,0,0)));
    geometry.add(new Vertex(new Point3D(1,0,0)));
    geometry.add(new Vertex(new Point3D(0,1,0)));
    geometry.add(new Vertex(new Point3D(0,0,1)));

    indicies.add(0);
    indicies.add(1);
    indicies.add(0);
    indicies.add(2);
    indicies.add(0);
    indicies.add(3);

    parts.add(new Part(Type.LINES, 0, 3));
}
}
