// Jason Park
// 4/14/2020
// CSE 142
// TA: 
/* This program will produce a rocket ship using a combination of 
   static methods, nested for loops with print/prinln statements, and
   a class constant. */

public class DrawRocket {
public static final int SIZE = 3;

   public static void main(String[] args) {
      drawCone();
      drawDesign();
      drawTopHalf();
      drawBottomHalf();
      drawDesign();
      drawBottomHalf();
      drawTopHalf();
      drawDesign();
      drawCone();
   }
   
   // This method produces the top and bottom cone-shaped parts of the rocket
   public static void drawCone() {
      for (int line = 1; line <= (SIZE * 2 - 1); line++) {
         for (int spaces = 1; spaces <= (-line + 2 * SIZE); spaces++) {
            System.out.print(" ");
         }
         for (int forwardSlash = 1; forwardSlash <= (line); forwardSlash++) {
            System.out.print("/");
         }
         System.out.print("**");
         for (int backSlash = 1; backSlash <= (line); backSlash++) {
            System.out.print("\\");
         } 
         for (int spaces = 1; spaces <= (-line + 2 * SIZE); spaces++) {
            System.out.print(" ");
         }
         System.out.println();
       }
   }
   
   // This method produces the =+ design that is found on the rocket
   public static void drawDesign() {
      System.out.print("+");
      for (int i = 1; i <= 2 * SIZE; i++) {
         System.out.print("=*");
      }
      System.out.println("+");
   }
   
   // This method produces the upper half of the rocket's body
   public static void drawTopHalf() {
      for (int line = 1; line <= SIZE; line++) {
         System.out.print("|");
         for (int outerDots = 1; outerDots <= (-line + SIZE); outerDots++) {
            System.out.print(".");
         }
         for (int pointUp = 1; pointUp <= (line); pointUp++) {
            System.out.print("/\\");
         }
         for (int innerDots = 1; innerDots <= (-2 * line + 2 * SIZE); innerDots++) {
            System.out.print(".");
         } 
         for (int pointUp = 1; pointUp <= (line); pointUp++) {
            System.out.print("/\\");
         }   
         for (int outerDots = 1; outerDots <= (-line + SIZE); outerDots++) {
            System.out.print(".");
         }
         System.out.println("|");
      }
   }
   
   // This method produces the bottom half of the rocket's body
   public static void drawBottomHalf() {
      for (int line = SIZE; line >= 1; line--) {
         System.out.print("|");
         for (int outerDots = 1; outerDots <= (-line + SIZE); outerDots++) {
            System.out.print(".");
         }
         for (int pointDown = 1; pointDown <= (line); pointDown++) {
            System.out.print("\\/");
         }
         for (int innerDots = 1; innerDots <= (-2 * line + 2 * SIZE); innerDots++) {
            System.out.print(".");
         }    
         for (int pointDown = 1; pointDown <= (line); pointDown++) {
            System.out.print("\\/");
         }   
         for (int outerDots = 1; outerDots <= (-line + SIZE); outerDots++) {
            System.out.print(".");
         }
         System.out.println("|");
      }  
   }   
}
