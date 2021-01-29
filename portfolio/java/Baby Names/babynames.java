import java.io.*;     // for File
import java.util.*;   // for Scanner
import java.awt.*;    // for Drawing Panel

public class Names {
   public static final int DECADE = 14;
   public static final int YEAR = 1880;
   public static final int WIDTH = 70;
   public static final int HEIGHT = 550;
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("names.txt"));
      Scanner console = new Scanner(System.in);
      
      String name = getName(console);
      String gender = getGender(console);

      
      
      String line = search(input, name, gender);
      if (line.length() > 0) {
         Graphics g = createPanel();
         display (g, line);         
      }
   
   }
    
   // prompts user to enter name and returns name as string
   public static String getName(Scanner console) {
      System.out.print("name? ");
      String name = console.next().toLowerCase();
      return name;
   }
   
   // prompts user to enter gender and returns gender as string
   public static String getGender(Scanner console) {
      System.out.print("sex (M or F) ? ");
      String gender = console.next().toLowerCase();
      return gender;
   }
   
   // searches the given input for match on user name and gender
   // returning the line (if there is one); returns empty string if no match is found;
   // ignores case when looking for phrase
   public static String search(Scanner input, String name, String gender) {
      while (input.hasNextLine()) {
         String line = input.nextLine();
         Scanner data = new Scanner(line);
         String fileName = data.next();
         String fileGender = data.next();
         if (name.equals(fileName.toLowerCase()) && gender.equals(fileGender.toLowerCase())) {
            return line;
         }
      }
      System.out.print("name/sex combination not found");
      return "";  // returns blank line if search is not found
   }
   
   public static Graphics createPanel() {
      DrawingPanel panel = new DrawingPanel (WIDTH * DECADE, HEIGHT);
      Graphics g = panel.getGraphics();
      
      drawPanel(g);
      
      return g;
   }
   
   public static void drawPanel (Graphics g) {
      g.drawLine(0, 25, WIDTH * DECADE, 25);
      g.drawLine(0, 525, WIDTH * DECADE, 525);
      for (int i = 0; i < DECADE; i++) {
         g.drawLine(i * WIDTH, 0, i * WIDTH, HEIGHT + i * WIDTH);             // draws the lines of panel
         g.drawString(String.valueOf(YEAR + i * 10), i * WIDTH, HEIGHT);      // draws the text strings of panel
      }
   }
     
   public static void display (Graphics g, String line)  {
      Scanner data2 = new Scanner(line);
      String name = data2.next();
      String gender = data2.next();
      g.setColor(Color.RED);
      
      
      int x = 0;
      int y = 0;
      int y2 = 0;
      int count = 0;
      
      while (data2.hasNextInt() && count < DECADE) {
         int rank = data2.nextInt();
         y2 = y;
         if (rank > 0 && rank % 2 == 1) {
            y = (rank / 2) + 25;
         } else if (rank > 0 && rank % 2 == 0) {    
            y = (rank / 2) + 25 - 1;
         } else {    // rank = 0
            y = 525;
         }
         g.drawString(name + " " + gender + " " + rank, x, y);
         g.drawLine(x - WIDTH, y2, x, y);
         x += WIDTH;
         count++;
      }
      
   }
   
}
