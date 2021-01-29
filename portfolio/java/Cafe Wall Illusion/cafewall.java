// Jason Park
// 04/20/2020
/* This program will create the Cafe Wall optical illusion
using a custom Drawing Panel and Graphics object in Java.
This program includes two different methods that utilizes
for loops, value parameters, and a class constant. */



import java.awt.*;

public class CafeWall {

/* We introduce a class constant MORTAR that would appear between the brick
   layers to create the illusion. The constant is set at 2 pixels. */ 
public static final int MORTAR = 2;

   public static void main(String[] args) { 
   DrawingPanel panel = new DrawingPanel (650, 400);
   panel.setBackground(Color.GRAY);
   Graphics g = panel.getGraphics();
   
   drawRow(g, 0, 0, 20, 4);
   drawRow(g, 50, 70, 30, 5);
   drawGrid(g, 400, 20, 35, 2, 35);
   drawGrid(g, 250, 200, 25, 3, 10);
   drawGrid(g, 10, 150, 25, 4, 0);
   drawGrid(g, 425, 180, 20, 5, 10);

   
   }
   
   /* This method is used to create a single row of black
      and white boxes, and the blue lines inside the boxes
      in the illusion using a for loop and value parameters */
   public static void drawRow(Graphics g, int x, int y, int size, int pairs) {
      for (int i = 0; i < pairs; i++) {
         g.setColor(Color.BLACK);
         g.fillRect(i * 2 * size + x, y, size, size);
         g.setColor(Color.WHITE);
         g.fillRect(i * 2 * size + size + x, y, size, size);
         g.setColor(Color.BLUE);
         g.drawLine(i * 2 * size + x, y, i * 2 * size + size + x, size + y);
         g.drawLine(i * 2 * size + x + size, y, i * 2 * size + x, y + size);
      }
   }  
   
   /* This method combines rows, lines, their offset value, and a class constant
      to produce the grids in the illusion. This method uses a for
      loop, value parameters, and the class constant MORTAR */ 
   public static void drawGrid(Graphics g, int x, int y, int size, int pairs, int offset) {
      for (int i = 0; i < pairs * 2; i++) {
         drawRow(g, x + ((i % 2) * offset), y + ((i * size) + (MORTAR * i)), size, pairs);
      }
   }
}
