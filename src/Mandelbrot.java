/******************************************************************************
 *  Compilation:  javac Mandelbrot.java
 *  Execution:    java Mandelbrot xc yc size
 *  Dependencies: StdDraw.java
 *
 *  Plots the size-by-size region of the Mandelbrot set, centered on (xc, yc)
 *
 *  % java Mandelbrot -0.5 0 2
 *
 ******************************************************************************/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
 
public class Mandelbrot extends JFrame {
 
    private final int MAX_ITER = 200;
    //private final int MAX_ITER = 1000;
    private final double ZOOM = 200;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;
 
    public Mandelbrot() {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
 
    private BufferedImage getImage(double zoom){
        BufferedImage I;
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                zx = zy = 0;
                cX = (x - 400) / zoom;
                cY = (y - 300) / zoom;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                //146233
                I.setRGB(x, y, iter|(iter << 4));
            }
        }
        return I;
    }
    @Override
    public void paint(Graphics g) {
        for(double i = ZOOM; i < 1000; i++){
            g.drawImage(getImage(i), 0, 0, this);
        }
        
    }
 
    public static void main(String[] args) {
        new Mandelbrot().setVisible(true);
    }
}