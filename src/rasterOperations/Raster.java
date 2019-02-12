package rasterOperations;

public interface Raster<T> {

    T getPixel(int x, int y);
    void setPixel(int x, int y, T value);
    int getWidth();
    int getHeight();

    
}
