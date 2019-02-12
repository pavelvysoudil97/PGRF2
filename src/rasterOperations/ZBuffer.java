package rasterOperations;

public class ZBuffer<T> implements Raster<T> {


    private double[][] position;


    public ZBuffer(){

    }

    @Override
    public T getPixel() {
        return null;
    }

    @Override
    public void setPixel(int x, int y, Integer value) {

    }
}
