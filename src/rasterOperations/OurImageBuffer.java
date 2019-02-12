package rasterOperations;

import java.awt.image.BufferedImage;

public class OurImageBuffer implements Raster<Integer> {

    private BufferedImage imageBuffer;

    public OurImageBuffer(int width, int height){
        imageBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
    @Override
    public Integer getPixel(int x, int y) {
        return imageBuffer.getRGB(x, y);
    }

    @Override
    public void setPixel(int x, int y, Integer value) {
        imageBuffer.setRGB(x, y, value);
    }

    @Override
    public int getWidth() {
        return imageBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return imageBuffer.getHeight();
    }
}
