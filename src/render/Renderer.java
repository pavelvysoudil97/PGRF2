package render;


import model.Part;
import model.Solid;
import model.Vertex;
import transforms.Mat4;
import transforms.Point3D;
import util.Lerp;

public class Renderer {

    private Mat4 viewMatrix;
    private RasterizerTriangle rt;
    private RasterizerLine rl;
    private RasterizerPoint rp;
    private Lerp<Vertex> lerp = new Lerp();

    public void render(Solid solid){
        for(Part part : solid.getParts()){
            switch(part.getType()){
                case LINES:

                    break;
                case TRIANGLES:
                    for(int i = 0; i< part.getCount(); i++){
                        Vertex a,b,c;
                        a = solid.getGeometry().get(solid.getIndicies().get(3 * i + part.getStart()));
                        b = solid.getGeometry().get(solid.getIndicies().get(3 * i + part.getStart()));
                        c = solid.getGeometry().get(solid.getIndicies().get(3 * i + part.getStart()));
                        renderTriangle(a, b, c);
                    }
                    break;
            }
        }
    }

    private void renderTriangle(Vertex a, Vertex b, Vertex c){
        //TODO transformace
        //TODO orez - rychle
        //TODO orez z>= 0

        Vertex helpPoint;
        if(a.getPosition().getZ() < b.getPosition().getZ() ){
            Vertex t = a;
            a=b;
            b=t;
        }
        if(a.getPosition().getZ() < c.getPosition().getZ() ){
            Vertex t = a;
            a=c;
            c=t;
        }
        if(a.getPosition().getZ() < b.getPosition().getZ() ){
            Vertex t = a;
            a=b;
            b=t;
        }

        // detekovat 4 varianty

        if(a.getPosition().getZ() < 0){
            return;
        }

        if(b.getPosition().getZ() < 0){
            double t1 = -b.getPosition().getZ()/
                        (a.getPosition().getZ() - b.getPosition().getZ());
            Vertex ab = lerp.lerp(b,a,t1);
            Vertex ac = ;
            rt.rasterize(a,ab,ac)
        }

    }
}
