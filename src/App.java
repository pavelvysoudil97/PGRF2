import model.Cube;
import model.Pyramid;
import model.Vertex;
import rasterOperations.OurImageBuffer;
import rasterOperations.Raster;
import rasterOperations.VisibilityZBuffer;
import render.RasterizerLine;
import render.RasterizerTriangle;
import render.Renderer;
import render.Shader;
import transforms.Camera;
import transforms.Col;
import transforms.Point3D;
import transforms.Vec3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class App extends JFrame {

    static int width = 800;
    static int height = 600;
    static int FPS = 1000/30;

    private JPanel panel;
    private OurImageBuffer image = new OurImageBuffer(width, height);
    private Camera camera;
    private Renderer renderer;
    private VisibilityZBuffer vis;

    public static void main(String [ ]args){

        App app = new App();
        app.initWindow(width, height);
        app.initControlls();
        app.initScene();


        Shader shader = new Shader(){
            @Override
            public Col shade(Vertex v) {
                return new Col(0xffffff);
            }
        };

        // RasterizerTriangle rt = new RasterizerTriangle(vis , (vertex)-> new Col(0xff));

    }

    private void initWindow(int width, int height){

        vis = new VisibilityZBuffer(width,height);
        vis.init(Color.GREEN.getRGB());

        RasterizerLine rl = new RasterizerLine(vis);
        RasterizerTriangle rt = new RasterizerTriangle(vis);
        Renderer renderer = new Renderer(rt);
        Cube cube = new Cube();
        renderer.render(cube);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(width, height);
        setResizable(false);
        setTitle("PGRF2 - PLOCHY");
        panel = new GraphicsPanel(vis);
        add(panel);
/*

        Renderer renderer = new Renderer(vis.getImageBuffer());


        setLocationRelativeTo(null);

        //Refresh platna
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                draw();
            }
        }, 100, FPS);*/
    }

    private void initControlls(){
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("point x: " + e.getX() + "\n point y: " + e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }
        });
    }

    private void initScene(){
        camera = new Camera();
        camera = camera.withPosition(new Vec3D(-10,10,5));
        camera = camera.withZenith(-0.5);
        camera = camera.withAzimuth(0.5);

    }

    private void draw(){
        panel = new GraphicsPanel(vis);
    }

}
