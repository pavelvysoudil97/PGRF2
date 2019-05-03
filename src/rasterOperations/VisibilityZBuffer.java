package rasterOperations;

import com.sun.prism.paint.Color;
import transforms.Col;

import java.util.Timer;
import java.util.TimerTask;

public class VisibilityZBuffer {

    private OurImageBuffer imageBuffer;
    private ZBuffer<Float> zBuffer;

    public VisibilityZBuffer(int width, int height){
        this.zBuffer = new ZBuffer<>(width, height);
        this.imageBuffer = new OurImageBuffer(width, height);

    }

    public void put(int x, int y, float z, Col color){
       if(z >=0 && z< zBuffer.getPixel(x, y))
        {
            imageBuffer.setPixel(x, y, color.getRGB());
            zBuffer.setPixel(x, y, z);

        }//imageBuffer.setPixel(x, y, 0xff);
    }


    public void init(int color){
       for (int i = 0; i < zBuffer.getWidth(); i++) {
            for (int j = 0; j < zBuffer.getHeight(); j++) {
               zBuffer.setPixel(i, j, new Float(1));
            }
        }
        imageBuffer.clear(color);
    }


    public OurImageBuffer getImageBuffer() {
        return imageBuffer;
    }

    public int getWidth(){
        return imageBuffer.getWidth();
    }
    public int getHeight(){
        return imageBuffer.getHeight();
    }
}
