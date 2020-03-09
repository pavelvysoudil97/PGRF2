package model;

import transforms.Bicubic;
import transforms.Col;
import transforms.Cubic;
import transforms.Point3D;

public class Surface extends Solid {

    Point3D[]rb = new Point3D[16];

public Surface(int pointsOfEdge){

     rb[0] =     new Point3D(8,6,8);

     rb[1] =    new Point3D(8,6,8);

     rb[2] =    new Point3D(8,6,8);

     rb[3] =    new Point3D(8,6,8);

     rb[4] =    new Point3D(8,6,8);

     rb[5] =    new Point3D(8,6,8);

     rb[6] =    new Point3D(8,6,8);

     rb[7] =    new Point3D(8,6,8);

     rb[8] =    new Point3D(8,6,8);

     rb[9] =    new Point3D(8,6,8);

    rb[10] =    new Point3D(8,6,8);

    rb[11] =    new Point3D(8,6,8);

    rb[12] =    new Point3D(8,6,8);

    rb[13] =    new Point3D(8,6,8);

    rb[14] =    new Point3D(8,6,8);

    rb[15] =    new Point3D(8,6,8);


    Bicubic bc = new Bicubic(Cubic.BEZIER, rb);
    int index = 0;

    for (double u = 0;  u < pointsOfEdge; u++) {
        for (int v = 0;  v < pointsOfEdge; v++) {
            getGeometry().add(new Vertex(bc.compute( (double)u / pointsOfEdge , (double) v / pointsOfEdge)));
        }
    }
    for (double u = 0; u < pointsOfEdge - 1; u++) {
        for (double v = 0; v < pointsOfEdge - 1; v++) {
            getIndicies().add((int)(u * (pointsOfEdge) + v));
            getIndicies().add((int)(u * (pointsOfEdge ) + v + 1));
            getIndicies().add((int)((u + 1) * (pointsOfEdge ) + v+1));

            getIndicies().add((int)(u * (pointsOfEdge ) + v));
            getIndicies().add((int)((u + 1) * pointsOfEdge + v));
            getIndicies().add((int)((u + 1) * (pointsOfEdge) + v+1));
         }
    }
    getParts().add(new Part(Type.TRIANGLES, 0, ((pointsOfEdge -1) * 2)*((pointsOfEdge -1)),new Col(0,255,0)));
}
}
