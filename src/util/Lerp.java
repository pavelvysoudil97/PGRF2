package util;

import model.Vertex;

public class Lerp <V extends Vectorizable<V>>{

    public V lerp(V a, V b, double t){
        return a.mul(1 - t).add(b.mul(t));
    }
}
