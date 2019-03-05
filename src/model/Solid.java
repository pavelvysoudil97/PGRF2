package model;

import java.util.List;

public abstract class Solid {

    List<Vertex> geometry;
    List<Integer> indicies;
    List<Part>parts;

    public List<Vertex> getGeometry() {
        return geometry;
    }

    public void setGeometry(List<Vertex> geometry) {
        this.geometry = geometry;
    }

    public List<Integer> getIndicies() {
        return indicies;
    }

    public void setIndicies(List<Integer> indicies) {
        this.indicies = indicies;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
