/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lowlevel;
import java.awt.*;
import javax.swing.*;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.*;
import java.util.zip.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.event.*;
import javax.swing.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/**
 *
 * @author Gaz
 */
public class HAL {

    public static String IP=null;
    public HAL()
    {
        //System.out.println("HAL object made.");
        //if (IP==null)
        //    IP= grabIP();
    }

    public static boolean CANVAS_LOGGING=false;//WARNING, adds all sys outs to vector to print (debug)
    public static final int BLACK=0;
    public static final int WHITE=1;
    Color colour;
    public void setColor(Graphics g, Color colour_)
    {
        colour=colour_;
        g.setColor(colour_);
    }

     public void setColor(Graphics g, int c)
     {
         Color cl = new Color(c);
         g.setColor(cl);
     }
    Color transparent;
    public void setColor(Graphics g, int red, int green, int blue, int alpha)
    {

        //if (transparent==null)
        {
            transparent=new Color(red,green,blue,alpha);
        }

        g.setColor(transparent);
    }
    public void resetTransparenctColour()
    {
        transparent=null;
    }

    public void setColor(Graphics g, Color c, int alpha)
    {
        if (transparent==null)
        {
             transparent=new Color(c.getRed(),c.getGreen(),c.getBlue(),alpha);
        }

        g.setColor(transparent);
    }

    public Color getColor()
    {
        return colour;
    }

    public void drawString(Graphics g, int x, int y,String s)
    {
        g.drawString(s,x,y);
    }

    public void drawRect(Graphics g, int x, int y, int WIDTH, int HEIGHT)
    {
        g.drawRect(x,y, WIDTH, HEIGHT);
    }

    public void fillRect(Graphics g, int x, int y, int WIDTH, int HEIGHT)
    {
        g.fillRect(x,y, WIDTH, HEIGHT);
    }

    public void drawRoundRect(Graphics g, int x, int y, int WIDTH, int HEIGHT)
    {
        g.drawRoundRect(x,y, WIDTH, HEIGHT,10,10);
    }

    public void fillRoundRect(Graphics g, int x, int y, int WIDTH, int HEIGHT)
    {
        g.fillRoundRect(x,y, WIDTH, HEIGHT,10,10);
    }

    public void drawTriangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3)
    {
        Polygon poly = new Polygon();
        poly.addPoint(x1, y1);
        poly.addPoint(x2, y2);
        poly.addPoint(x3, y3);
        g.drawPolygon(poly);
    }
    public void fillTriangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3)
    {
        Polygon poly = new Polygon();
        poly.addPoint(x1, y1);
        poly.addPoint(x2, y2);
        poly.addPoint(x3, y3);
        g.fillPolygon(poly);
    }

    public void drawCircle(Graphics g, int x, int y,int width, int height)
    {
         g.drawArc(x, y,width, height, 1, 360);
    }
    public void fillCircle(Graphics g, int x, int y,int width, int height)
    {
         g.fillArc(x, y,width, height, 1, 360);
    }

    Image tempImage;
    public Image loadImage(String path)
    {
        try {
            _("LOADIMAGE: Attempting to load: "+path);
            tempImage = ( new javax.swing.ImageIcon(getClass().getResource(path)).getImage() );
        } catch (Exception e) {
            _E("error loading image ("+path+") "+e.getMessage());
        }
        return tempImage;
    }

    public void bg(Graphics g, Color c, int WIDTH, int HEIGHT)
    {
        setColor(g,c);
        fillRect(g,0,0,WIDTH,HEIGHT);
    }

    public void drawImage(Graphics g, Image i, int x, int y, ImageObserver observer)
    {
        g.drawImage(i,x-(i.getWidth(observer)/2), y-(i.getHeight(observer)/2),observer);
    }

    public static int LINES_THAT_FIT_VERTICALLY=30;//allow to scale. todo
    public static Vector systemOuts=new Vector(0);
    public static void _(String s)
    {
        if (CANVAS_LOGGING)
        {
            systemOuts.add(s);
            if (systemOuts.capacity()>LINES_THAT_FIT_VERTICALLY)
            {
                systemOuts.remove(0);
            }
        }
        System.out.println(s);
    }

    public static final String ERROR_STRING="****ERROR**** ";
    public static void _E(String s)
    {
        if (CANVAS_LOGGING)
        {
            systemOuts.add(ERROR_STRING+s);
        }
        CustomCanvas.sfxError.playSound();
        System.out.println(ERROR_STRING+s);
    }

    private static final Random randomizer = new Random();
    // get random val between min and max
    public static final int getRand(int min, int max) {
        int r = Math.abs(randomizer.nextInt());
        return (r % ((max - min + 1))) + min;
    }

    public static void SLEEP(long snoozeFor)
    {
        try
        {
            Thread.sleep(snoozeFor);
        }
        catch(Exception e)
        {
            _E("Insomnia!");
        }
    }


  
  

}

