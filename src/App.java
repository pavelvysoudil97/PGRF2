import model.*;
import rasterOperations.OurImageBuffer;
import rasterOperations.Raster;
import rasterOperations.VisibilityZBuffer;
import render.RasterizerLine;
import render.RasterizerTriangle;
import render.Renderer;
import render.Shader;
import transforms.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class App extends JFrame {

    static int width = 800;
    static int height = 600;

    private int startDragX;
    private int startDragY;
    private int moveX, moveY, moveZ = 0;

    private JPanel panel;
    private OurImageBuffer image = new OurImageBuffer(width, height);
    private Camera camera;
    private Renderer renderer;
    private VisibilityZBuffer vis;
    List<Solid> solids = new ArrayList<>();

    public static void main(String [ ]args){

        App app = new App();
        app.initWindow(width, height);
        app.initControlls();


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
        vis.init(Color.WHITE.getRGB());

        RasterizerLine rl = new RasterizerLine(vis);
        RasterizerTriangle rt = new RasterizerTriangle(vis);



        renderer = new Renderer(rt, rl);
        Cube cube = new Cube();
        Pyramid pyramid = new Pyramid(4);
        Surface surface = new Surface(5);
        //Arrow arrow = new Arrow();
        camera = new Camera(new Vec3D(9, 7, -2), -2.5,0.3, 0.3, true);

        renderer.setTransformMatrix(new Mat4PerspRH(Math.PI / 4, height / (float) width, 1, 200));
        renderer.setViewMatrix(camera.getViewMatrix());

        solids.add(cube);
        solids.add(pyramid);
        //solids.add(arrow);
        //solids.add(surface);

        renderer.render(solids);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(width, height+100);
        setResizable(false);
        setTitle("PGRF2 - PLOCHY");



        panel = new GraphicsPanel(vis);
        panel.setFocusable(true);
        panel.requestFocus();

        JLabel legendLabel = new JLabel();
        legendLabel.setText("Legend");
        add(legendLabel, BorderLayout.SOUTH);
        legendLabel.setText("<html>To control camera : Arrow Up,Down,Left,Right <BR> \n " +
                " Switch Projection : O - Ortoghonal / P - Perspective<BR>\n" +
                "\n To rotate with Cube : x - by X axis / y - by Y axis / z - by Z axis</html>");

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void initControlls(){
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                startDragX = e.getX();
                startDragY = e.getY();
                renderer.setViewMatrix(camera.getViewMatrix());
                renderer.render(solids);
                System.out.println("point x: " + e.getX() + "\n point y: " + e.getY());
            }


        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //vis.getImageBuffer().clear(Color.WHITE.getRGB());
                vis.init(Color.WHITE.getRGB());
                camera = camera.addAzimuth((Math.PI/1000) * (startDragX - e.getX()));
                camera = camera.addZenith((Math.PI / 1000) * (startDragY - e.getY()));
                startDragX = e.getX();
                startDragY = e.getY();
                renderer.render(solids);
                renderer.setViewMatrix(camera.getViewMatrix());
                panel.repaint();

                super.mouseDragged(e);
            }
        });

        panel.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {

                    // Arrow moves - up, down, left, right
                    case KeyEvent.VK_UP:
                        System.out.println("Camera position" + " x: " + camera.getPosition().getX() + " y: " + camera.getPosition().getY() + " z: " + camera.getPosition().getZ());
                        camera = camera.up(0.2);
                        renderer.setViewMatrix(camera.getViewMatrix());
                         break;

                    case KeyEvent.VK_DOWN:
                        camera = camera.down(0.2);
                        renderer.setViewMatrix(camera.getViewMatrix());
                        System.out.println("Camera position" + " x: " + camera.getPosition().getX() + " y: " + camera.getPosition().getY() + " z: " + camera.getPosition().getZ());
                        break;
                    case KeyEvent.VK_LEFT:
                        camera = camera.right(0.2);
                        renderer.setViewMatrix(camera.getViewMatrix());
                        System.out.println("Camera position" + " x: " + camera.getPosition().getX() + " y: " + camera.getPosition().getY() + " z: " + camera.getPosition().getZ());
                        break;
                    case KeyEvent.VK_RIGHT:
                        camera = camera.left(0.2);
                        renderer.setViewMatrix(camera.getViewMatrix());
                        break;

                    // W, A, S, D moves
                    case KeyEvent.VK_A:
                        System.out.println("Camera position" + " x: " + camera.getPosition().getX() + " y: " + camera.getPosition().getY() + " z: " + camera.getPosition().getZ());
                        camera = camera.left(0.2);
                        renderer.setViewMatrix(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_W:
                        System.out.println("Camera position" + " x: " + camera.getPosition().getX() + " y: " + camera.getPosition().getY() + " z: " + camera.getPosition().getZ());
                        camera = camera.forward(0.2);
                        renderer.setViewMatrix(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_S:
                        System.out.println("Camera position" + " x: " + camera.getPosition().getX() + " y: " + camera.getPosition().getY() + " z: " + camera.getPosition().getZ());
                        camera = camera.backward(0.2);
                        renderer.setViewMatrix(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_D:
                        System.out.println("Camera position" + " x: " + camera.getPosition().getX() + " y: " + camera.getPosition().getY() + " z: " + camera.getPosition().getZ());
                        camera = camera.right(0.2);
                        renderer.setViewMatrix(camera.getViewMatrix());
                        break;

                    //ROTACE
                    case KeyEvent.VK_X :
                        solids.set(0,renderer.rotateSolidByX(solids.get(0), 0.05));
                        break;
                    case KeyEvent.VK_Y :
                        solids.set(0,renderer.rotateSolidByY(solids.get(0), 0.05));
                        break;
                    case KeyEvent.VK_Z :
                        solids.set(0,renderer.rotateSolidByZ(solids.get(0), 0.1));
                        break;

                    //PROJEKCE
                    case KeyEvent.VK_O:
                        renderer.setTransformMatrix(new Mat4OrthoRH(10, 10, 1, 100));
                        break;
                    case KeyEvent.VK_P:
                        renderer.setTransformMatrix(new Mat4PerspRH(1, 1, 1, 100));
                        break;

                }
                vis.init(Color.WHITE.getRGB());
                renderer.render(solids);
                panel.repaint();

                super.keyReleased(e);
            }});
        setLocationRelativeTo(null);

        }




}
