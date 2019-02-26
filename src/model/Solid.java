package model;

import java.util.List;

public abstract class Solid {

    List<Vertex> geometry;
    List<Integer> indicies;
    List<Part>parts;

    public List<Vertex> getGeometry() {
        return geometry;
    }

}
