package rasterOperations;

public class VisibilityZBuffer {

    private OurImageBuffer imageBuffer;
    private ZBuffer<Float> zBuffer;

    public VisibilityZBuffer(int width, int height){
        this.zBuffer = new ZBuffer<>(width, height);
    }

    public void putPixel(int x, int y, float z, int value){

    }

    public void init(){

    }
}
