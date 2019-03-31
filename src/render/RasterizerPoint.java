package render;

import model.Vertex;
import rasterOperations.VisibilityZBuffer;
import transforms.Col;
import transforms.Point3D;

import java.util.function.Function;

public class RasterizerPoint {

    Function<Vertex, Col> shader =(vertex) -> new Col(0xffffff);
    private VisibilityZBuffer vis;
    private int height;
    private int width;

    public RasterizerPoint(VisibilityZBuffer vis){
        this.vis = vis;
        this.height = vis.getHeight();
        this.width = vis.getWidth();
    }

    public void setShader(Function<Vertex, Col>shader){
        this.shader = shader;
    }

    public void rasterize(Vertex vertex){
        vertex = vertex.dehomog();

        vis.put((int)vertex.getPosition().getX(), (int)vertex.getPosition().getY(),(float)vertex.getPosition().getZ(), new Col(0xff0000));
    }

}
