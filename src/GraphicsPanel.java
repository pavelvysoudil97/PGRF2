import rasterOperations.OurImageBuffer;
import rasterOperations.VisibilityZBuffer;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GraphicsPanel extends JPanel {

    private VisibilityZBuffer visBuffer;

    public GraphicsPanel(VisibilityZBuffer visBuffer) {
        this.visBuffer = visBuffer;
       // setTimer();

    }

    public void setVisBuffer(VisibilityZBuffer visBuffer) {
        this.visBuffer = visBuffer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(visBuffer.getImageBuffer().getImageBuffer(), 0, 0, null);
    }
    private void setTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                repaint();
            }
        }, 0);
    }


}
