import rasterOperations.OurImageBuffer;
import rasterOperations.VisibilityZBuffer;

import javax.swing.*;
import java.awt.*;

public class GraphicsPanel extends JPanel {

    private VisibilityZBuffer visBuffer;

    public GraphicsPanel(VisibilityZBuffer visBuffer) {
        this.visBuffer = visBuffer;
    }

    public void setVisBuffer(VisibilityZBuffer visBuffer) {
        this.visBuffer = visBuffer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(visBuffer.getImageBuffer().getImageBuffer(), 0, 0, null);
    }


}
