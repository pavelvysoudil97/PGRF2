package render;

import model.Vertex;
import rasterOperations.VisibilityZBuffer;
import transforms.Col;

import java.util.function.Function;

public class RasterizerTriangle {

    Function<Vertex, Col>shader = (vertex)-> new Col(0xffffff);
    int width, height;
    public RasterizerTriangle(VisibilityZBuffer vis){
        this.width = vis.getWidth();
        this.height = vis.getHieght();
    }

    public void setShader(Function<Vertex, Col> shader){
        this.shader = shader;
    };

    public void rasterize(Vertex a, Vertex b, Vertex c){
        a = a.dehomog();
        b = b.dehomog();
        c = c.dehomog();

        double xA = ((width - 1) * (a.getPosition().getX() + 1) / 2);
        //TODO  radit podle yA, yB, yC
       /* double yA = () * (a.getPosition().getX());
        double xB = () * (a.getPosition().getX());
        double yB = () * (a.getPosition().getX());
        double xC = () * (a.getPosition().getX());
        double yC = () * (a.getPosition().getX());*/


       for(int y = (int) Math.max(yA, 0); y < yB; y++){

           double s1 = (y-yA)/(yB - yA);
           double x1 = xA*(1 - s1)+ xB * s1;
           double s2,x2,z1,z2;
           for(int x=Math.max((int)x1,0);x < x2; x++){
               double t;
               double z;
               vis.put(x,y,z,shader.apply())
           }
       }

    }
}
