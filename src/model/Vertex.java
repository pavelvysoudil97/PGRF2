package model;


import transforms.Mat4;
import transforms.Point3D;
import util.Vectorizable;

public class Vertex implements Vectorizable<Vertex> {

    private Point3D position;

    public Vertex(Point3D point){
        this.position = point;
    }

    public Point3D getPosition(){
        return position;
    }

    public Vertex mul(Mat4 mat){return new Vertex(new Point3D(position.mul(mat)));}
    @Override
    public Vertex mul(double t) {
        return new Vertex(position.mul(t));
    }

    @Override
    public Vertex add(Vertex a) {

        return new Vertex(position.add(a.getPosition()));
    }

    public Vertex dehomog(){
        return this.mul(1/getPosition().getW());
    }
}
