package render;

import model.Vertex;
import rasterOperations.VisibilityZBuffer;
import transforms.Col;
import transforms.Point3D;

import java.util.function.Function;

public class RasterizerLine {

    Function<Vertex, Col>shader = (vertex) ->new Col(0xff00AA);
    VisibilityZBuffer vis;

    private int height;
    private int width;

    public RasterizerLine(VisibilityZBuffer vis){
        this.vis = vis;
        this.height = vis.getHeight();
        this.width = vis.getWidth();
    }

    public void setShader(Function<Vertex, Col>shader){
        this.shader = shader;
    }

    public void rasterize(Vertex a, Vertex b){
        a.dehomog();
        b.dehomog();

        double xA = ((width - 1) * (a.getPosition().getX() + 1))/2;
        double yA =((height - 1) * (1 - a.getPosition().getY()))/2;
        double xB = ((width - 1) * (b.getPosition().getX() + 1))/2;
        double yB =((height - 1) * (1 - b.getPosition().getY()))/2;

        double zA = a.getPosition().getZ();
        double zB = b.getPosition().getZ();


        for (int y = (int) Math.max(yA, 0); y < (int) Math.min( yB, height); y++) {
            double s1 = (y - yA) / (yB - yA);
            double x =((xA*(1 - s1)) + (xB * s1));
            double z =((zA*(1 - s1)) + (zB * s1));

            vis.put((int)x, (int) y,(float)z,shader.apply(new Vertex(new Point3D(x, y, z))));
        }

    }
}
