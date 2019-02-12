package rasterOperations;

public interface Raster<T> {

    T getPixel();
    void setPixel(int x, int y, Integer value);


    
}
