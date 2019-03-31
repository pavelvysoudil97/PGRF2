package render;


import model.Part;
import model.Solid;
import model.Vertex;
import rasterOperations.OurImageBuffer;
import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Point3D;
import util.Lerp;

public class Renderer {

    private Mat4 viewMatrix = new Mat4Identity();
    private Mat4 modelMatrix = new Mat4Identity();
    private Mat4 transformMatrix = new Mat4Identity();

    private Mat4 finalMatrix = modelMatrix.mul(viewMatrix.mul(transformMatrix));

    private RasterizerTriangle rt;
    private RasterizerLine rl;
    private RasterizerPoint rp;
    private Lerp<Vertex> lerp = new Lerp<>();

    //private OurImageBuffer ourImageBuffer;

    public Renderer(RasterizerTriangle rt){
        this.rt = rt;
    }


    public void render(Solid solid){
        for(Part part : solid.getParts()){
            switch(part.getType()){
                case POINTS:
                    for (int i = 0; i < part.getCount(); i++) {
                        Vertex vertex = solid.getGeometry().get(solid.getIndicies().get(3 * i + part.getStart()));
                        renderPoint(vertex);
                    }
                    break;
                case LINES:

                    for (int i = 0; i < part.getCount(); i++) {
                        Vertex vA = solid.getGeometry().get(solid.getIndicies().get(3 * i + part.getStart()));
                        Vertex vB = solid.getGeometry().get(solid.getIndicies().get(3 * i + part.getStart() + 1));
                        renderLine(vA, vB);
                    }

                    break;
                case TRIANGLES:
                    for(int i = 0; i< part.getCount(); i++){
                        Vertex a,b,c;
                        a = solid.getGeometry().get(solid.getIndicies().get(3 * i + part.getStart()));
                        b = solid.getGeometry().get(solid.getIndicies().get(3 * i + part.getStart()) + 1);
                        c = solid.getGeometry().get(solid.getIndicies().get(3 * i + part.getStart()) + 2);
                        renderTriangle(a, b, c);
                    }
                    break;
            }
        }

    }

    private void renderPoint(Vertex vertex){
        if (vertex.getPosition().getZ() < 0){
            rp.rasterize(vertex);
        }
    }

    private void renderLine(Vertex vA, Vertex vB){

        if (vA.getPosition().getZ() < vB.getPosition().getZ()){
            Vertex pom = vA;
            vA = vB;
            vB = pom;
        }

        if(vB.getPosition().getZ() < 0 && vA.getPosition().getZ() > 0){
            //TODO dopocitat
            double t1 = -vB.getPosition().getZ() / (vA.getPosition().getZ() - vB.getPosition().getZ());
            Vertex ab =lerp.lerp(vB, vA, t1);
            rl.rasterize(vA, ab);
            return;
        }

        rl.rasterize(vA, vB);

    }

    private void renderTriangle(Vertex a, Vertex b, Vertex c){

        //PGRF1 - transformace a orez

        //TODO transformace
        a = a.mul(modelMatrix).mul(viewMatrix).mul(transformMatrix);
        b = b.mul(modelMatrix).mul(viewMatrix).mul(transformMatrix);
        c = c.mul(modelMatrix).mul(viewMatrix).mul(transformMatrix);


        //TODO orez - rychle

        if(     //x > w
                (a.getPosition().getX() > a.getPosition().getW() &&
                b.getPosition().getX() > b.getPosition().getW() &&
                c.getPosition().getX()> c.getPosition().getW()) ||
                        //x < -w
                (a.getPosition().getX() <  - a.getPosition().getW() &&
                        b.getPosition().getX() < -b.getPosition().getW() &&
                        c.getPosition().getX() < -c.getPosition().getW()) ||
                        // y < -w
                (a.getPosition().getY() <  - a.getPosition().getW() &&
                        b.getPosition().getY() < -b.getPosition().getW() &&
                        c.getPosition().getY() < -c.getPosition().getW()) ||
                        // y > w
                (a.getPosition().getY() >   a.getPosition().getW() &&
                        b.getPosition().getY() > b.getPosition().getW() &&
                        c.getPosition().getY() > c.getPosition().getW())  ||
                        // z < 0 || z > w
                ( (a.getPosition().getZ() < 0 && b.getPosition().getZ() < 0 && c.getPosition().getZ() < 0)||
                        a.getPosition().getZ() > a.getPosition().getW() &&
                                b.getPosition().getZ() > b.getPosition().getW() &&
                                c.getPosition().getZ()> c.getPosition().getW()) ){
            return;
        }
        {


        }
        //TODO orez z>= 0

        Vertex helpPoint;
        if(a.getPosition().getZ() < b.getPosition().getZ() ){
            Vertex t = a;
            a = b;
            b = t;
        }
        if(b.getPosition().getZ() < c.getPosition().getZ() ){
            Vertex t = b;
            b = c;
            c = t;
        }
        if(a.getPosition().getZ() < b.getPosition().getZ() ){
            Vertex t = a;
            a = b;
            b = t;
        }

        // detekovat 4 varianty
        // 1. - vsechny body pod Z - nezobrazeno
        if(a.getPosition().getZ() < 0){
            return;
        }
        // 2. - NEDORESENE hazi null
        if(b.getPosition().getZ() < 0 && a.getPosition().getZ() > 0){
            double t1 = -a.getPosition().getZ()/
                        (b.getPosition().getZ() - a.getPosition().getZ());
            double t2 = -a.getPosition().getZ()/
                    (c.getPosition().getZ() - a.getPosition().getZ());
            Vertex ab = lerp.lerp(a,b,t1);
            Vertex ac = lerp.lerp(a, c, t2);
            rt.rasterize(a,ab,ac);

            return;
        }

        if (c.getPosition().getZ() < 0 && b.getPosition().getZ() > 0){
            double t1 = -b.getPosition().getZ()/(c.getPosition().getZ() - b.getPosition().getZ());
            double t2 = -a.getPosition().getZ()/(c.getPosition().getZ() - a.getPosition().getZ());
            Vertex bc = lerp.lerp(b, c, t1);
            Vertex ac = lerp.lerp(a, c, t2);
            rt.rasterize(a, b, ac);
            rt.rasterize(bc, b, ac);
            return;
        }

        if(c.getPosition().getZ() > 0){
            rt.rasterize(a, b, c);
        }

    }
}
