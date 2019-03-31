package rasterOperations;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Console;

public class OurImageBuffer implements Raster<Integer> {

    private BufferedImage imageBuffer;

    public OurImageBuffer(int width, int height){
        imageBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void clear(int color){
        Graphics gr = imageBuffer.getGraphics();
        gr.setColor(new Color(color));
        gr.fillRect(0, 0, imageBuffer.getWidth(), imageBuffer.getHeight());
    }
    @Override
    public Integer getPixel(int x, int y) {
        return imageBuffer.getRGB(x, y);
    }

    @Override
    public void setPixel(int x, int y, Integer value) {
        imageBuffer.setRGB(x, y, value.intValue());
    }

    @Override
    public int getWidth() {
        return imageBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return imageBuffer.getHeight();
    }

    public BufferedImage getImageBuffer(){
        return imageBuffer;
    }
}
