package rasterOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZBuffer<T> implements Raster<T> {


    private List<T> position;
    private int width, height;


    public ZBuffer(int width, int height){
        this.height = height;
        this.width = width;
        position = new ArrayList<>(width * height);
        fill();
        System.out.print(position.size());
    }

    @Override
    public T getPixel(int x, int y) {

        return position.get(y * width + x);
    }

    @Override
    public void setPixel(int x, int y, T value) {
        position.set(y * width + x, value);
    }


    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    private void fill(){
        for (int i = 0; i <( width * height); i++) {
            position.add((T)new Object());
        }
    }
}
