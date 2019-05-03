package render;

import model.Vertex;
import rasterOperations.VisibilityZBuffer;
import transforms.Col;
import transforms.Point3D;
import transforms.Vec3D;

import java.util.Optional;
import java.util.function.Function;

public class RasterizerTriangle {

    Function<Vertex, Col>shader = (vertex)-> new Col(0xAA00FF);
    int width, height;
    VisibilityZBuffer vis;


    public RasterizerTriangle(VisibilityZBuffer vis){
        this.vis = vis;
        this.width = vis.getWidth();
        this.height = vis.getHeight();
    }

    public void setShader(Function<Vertex, Col> shader){
        this.shader = shader;
    };

    public void rasterize(Vertex a, Vertex b, Vertex c){


        a = a.dehomog();
        b = b.dehomog();
        c = c.dehomog();


        double xA = ((width - 1) * (a.getPosition().getX() + 1))/2;
        double yA =((height - 1) * (1 - a.getPosition().getY()))/2;
        double xB = ((width - 1) * (b.getPosition().getX() + 1))/2;
        double yB =((height - 1) * (1 - b.getPosition().getY()))/2;
        double xC = ((width - 1) * (c.getPosition().getX() + 1))/2;
        double yC =((height - 1) * (1 - c.getPosition().getY()))/2;

        double zA = a.getPosition().getZ();
        double zB = b.getPosition().getZ();
        double zC = c.getPosition().getZ();
        //TODO  radit podle yA, yB, yC

       if(yA > yB){
            double pY = yB;
            yB = yA;
            yA = pY;
            double pX = xB;
            xB = xA;
            xA = pX;
            double pZ = zB;
            zB = zA;
            zA = pZ;
       }
        if(yB > yC){
            double pY = yC;
            yC = yB;
            yB = pY;
            double pX = xC;
            xC = xB;
            xB = pX;
            double pZ = zC;
            zC = zB;
            zB = pZ;

        }
        if(yA > yB){
            double pY = yB;
            yB = yA;
            yA = pY;
            double pX = xB;
            xB = xA;
            xA = pX;
            double pZ = zB;
            zB = zA;
            zA = pZ;
        }



       for(int y = (int) Math.max(yA, 0); y < (int) Math.min(yB, height); y++){

            double s1 = (y-yA)/(yB - yA);
            double x1 = (xA*(1 - s1))+ (xB * s1);
            double z1 = (zA*(1 - s1))+ (zB * s1);

            double s2 = (y-yA)/(yC - yA);
            double x2 = (xA*(1 - s2))+ (xC * s2);
            double z2 = (zA*(1 - s2))+ (zC * s2);
            if(x1 > x2){
                double pom = x2;
                x2 = x1;
                x1 = pom;

                pom = z2;
                z2 = z1;
                z1 = pom;
            }
            for(int x = Math.max((int)x1,0); x < (int) Math.min(x2, width); x++){
                double t = (x - x1) / (x2 - x1);
                double z = z1 * (1 - t)+ z2 * t;
                vis.put(x, y, (float) z, new Col(0x00ff00));//shader.apply(new Vertex(new Point3D(x, y, z))));
                //vis.put(x, y, (float) z, a.get);
            }
        }
        for(int y = (int) Math.max(yB, 0); y < (int) Math.min(yC, height); y++){

            double s1 = (y-yB)/(yC - yB);
            double x1 = (xB*(1 - s1))+ (xC * s1);
            double z1 = (zB*(1 - s1))+ (zC * s1);

            double s2 = (y-yA)/(yC - yA);
            double x2 = (xA*(1 - s2))+ (xC * s2);
            double z2 = (zA*(1 - s2))+ (zC * s2);

            if(x1 > x2){
                double pom = x2;
                x2 = x1;
                x1 = pom;

                pom = z2;
                z2 = z1;
                z1 = pom;
            }
            for(int x = Math.max((int)x1,0); x < (int) Math.min(x2, width); x++){
                double t = (x - x1) / (x2 - x1);
                double z = z1 * (1 - t)+ z2 * t;
                vis.put(x, y, (float) z, new Col(0xff0000));//, shader.apply(new Vertex(new Point3D(x, y, z))));
            }
        }

    }
    public VisibilityZBuffer getVis() {
        return vis;
    }

}
