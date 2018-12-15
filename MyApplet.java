import javax.swing.*;
import javax.swing.Timer;
import java.applet.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.*;
import java.io.*;

public class MyApplet extends Applet implements Runnable{

    Color bgColor;
    Color drawingColor;
    int xUpperPosition;
    int xLowerPosition;
    int yUpperPosition;
    int yLowerPosition;
    int alpha;

    final int WIDTH=1200;
    final int HEIGHT=400;

    public int getColor(String strColor) {
        if (strColor != null && strColor.charAt(0) == '#') {
            return Integer.parseInt(strColor.substring(1), 16);
        }
        return 0;
    }


    Thread thread = null;
    public void start() {
        if (thread == null)
            thread = new Thread(this);
        thread.start();
    }

    public void init() {

        setSize(WIDTH, HEIGHT);
        String str = getParameter("BgColor");
        bgColor = new Color(getColor(str));
        str = getParameter("DrawingColor");
        drawingColor = new Color(getColor(str));
        str = getParameter("UpperXPosition");
        xUpperPosition = Integer.parseInt(str);
        str = getParameter("UpperYPosition");
        yUpperPosition = Integer.parseInt(str);
        str = getParameter("LowerXPosition");
        xLowerPosition = Integer.parseInt(str);
        str = getParameter("LowerYPosition");
        yLowerPosition = Integer.parseInt(str);
    }

    @Override
    public void run() {
        alpha=1;
        while(true) {
            try {
                Thread.sleep(40);
                alpha++;
                if (alpha >= 360)
                    alpha = 1;
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } {
            }
        }
    }

    public void paint(Graphics g){

        super.paint(g);
        setBackground(bgColor);
        g.setColor(drawingColor);
        Graphics2D graphics=(Graphics2D)g.create();
        graphics.rotate(Math.toRadians(alpha), xLowerPosition,yLowerPosition);
        GeneralPath genPath=new GeneralPath();
        genPath.moveTo(xUpperPosition,yLowerPosition);
        genPath.lineTo(xUpperPosition, yUpperPosition);
        genPath.lineTo(xLowerPosition, yUpperPosition);
        genPath.lineTo(xLowerPosition, yLowerPosition);
        genPath.closePath();
        graphics.fill(genPath);
    }
}


