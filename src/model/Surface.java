package model;

import transforms.Bicubic;
import transforms.Cubic;
import transforms.Point3D;

public class Surface extends Solid {

    Point3D[]rb = new Point3D[16];

public Surface(){

    rb[0] = new Point3D(5,5,5);
    rb[1] = new Point3D(6,6,5);
    rb[2] = new Point3D(7,6,5);
    rb[3] = new Point3D(8,5,5);
    rb[4] = new Point3D(5,5,6);
    rb[5] = new Point3D(6,6,6);
    rb[6] = new Point3D(7,5,6);
    rb[7] = new Point3D(8,6,6);
    rb[8] = new Point3D(5,5,7);
    rb[9] = new Point3D(6,6,7);
    rb[10] = new Point3D(7,5,7);
    rb[11] = new Point3D(8,6,7);
    rb[12] = new Point3D(5,5,8);
    rb[13] = new Point3D(6,6,8);
    rb[14] = new Point3D(7,5,8);
    rb[15] = new Point3D(8,6,8);

    Bicubic bc = new Bicubic(Cubic.BEZIER, rb);
    int index = 0;

    for (int u = 0; u <= 1; u += 0.1) {
        for (int v = 0; v <= 1; v += 0.1) {
            Vertex vertex = new Vertex(bc.compute(u, v));
            getGeometry().add(vertex);
            getIndicies().add(index++);
         }
        
    }
    // getParts().add(new Part());
}

}
