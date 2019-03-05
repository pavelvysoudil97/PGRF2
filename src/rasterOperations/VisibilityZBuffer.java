package rasterOperations;

public class VisibilityZBuffer {

    private OurImageBuffer imageBuffer;
    private ZBuffer<Float> zBuffer;

    public VisibilityZBuffer(int width, int height){
        this.zBuffer = new ZBuffer<>(width, height);
    }

    public void put(int x, int y, float z, int color){
        if(z >=0 && z< zBuffer.getPixel(x, y));
    }

    public void init(){

    }

    public int getWidth(){
        return zBuffer.getWidth();
    }
    public int getHieght(){

        return zBuffer.getHeight();
    }
}
