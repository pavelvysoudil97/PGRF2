import model.Vertex;
import rasterOperations.VisibilityZBuffer;
import render.RasterizerTriangle;
import render.Shader;
import transforms.Col;

public class App {

    public static void main(String [ ]args){


        Shader shader = new Shader() {
            @Override
            public Col shade(Vertex v) {
                return new Col(0xffffff);
            }
        };

        VisibilityZBuffer vis = new VisibilityZBuffer(100, 100);
        RasterizerTriangle rt = new RasterizerTriangle(vis , (vertex)-> new Col(0xff));




    }

}
