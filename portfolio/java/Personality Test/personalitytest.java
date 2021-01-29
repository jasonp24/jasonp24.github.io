// Jason Park
// 05/25/20


/* This program processes an input file of data for a personality
test called the Keirsey Temperament Sorter. The program produces
an external output file that displays each individual's name,
percent of B answers, and personality according to Keirsey test.
*/

import java.io.*;    // For File
import java.util.*;  // For Scanner

public class Personality {
   public static final int DIMENSION = 4;  // number of dimensions of the test
   // Personality types: Extrovert/Introvert, Thinking/Feeling
   public static final String[] PERSONALITY_TYPES = {"E", "I", "T", "F"};  
   // Personality types: Sensing/iNtuition, Judging/Perceiving   
   public static final String[] PERSONALITY_TYPES2 = {"S", "N", "J", "P"};       
   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      giveIntro();
      
      System.out.print("input file name? ");
      String inputName = console.nextLine();
      Scanner input = new Scanner(new File(inputName));
      
      System.out.print("output file name? ");
      String outputName = console.nextLine();
      PrintStream output = new PrintStream(new File(outputName));
      
      while (input.hasNextLine()) {
         String name = input.nextLine();
         String results = input.nextLine().toLowerCase();  //possible options = A/a, B/b, -
         output.print(name + ": ");   
         
         int[] scoresA = new int [DIMENSION];
         int[] scoresB = new int [DIMENSION];
         
         computeScores(results, scoresA, scoresB);
         
         int[] bPercentages = computePercentage(scoresA, scoresB, output);
         
         String[] personality = createPersonality(bPercentages);
         printResults(personality, output);
      }
   }
   
   // introduces program to the user
   public static void giveIntro() {
      System.out.println("This program processes a file of answers to the");
      System.out.println("Keirsey Temperament Sorter.  It converts the");
      System.out.println("various A and B answers for each person into");
      System.out.println("a sequence of B-percentages and then into a");
      System.out.println("four-letter personality type.");
      System.out.println();
   }
   
   // Takes in parameters of user's results as String and arrays of scoresA and scoresB
   // to tally number of "A/a" and "B/b" responses and puts them in corresponding array
   // User responses with no answer are marked as a "-" and are not counted
   public static void computeScores(String results, int[] scoresA, int[] scoresB) {
      for (int i = 0; i < results.length(); i++) {
         char next = results.charAt(i);
         if (next == 'a') {
            tallyScores(scoresA, i);
         }  else if (next == 'b') {   // blank responses = "-" are ignored
            tallyScores(scoresB, i);
         }
      }
   }
   
   // Takes in parameters of scoresA and scoresB array and int i 
   // to tally answers into corresponding array index
   // Every 1st question = for E/I; Every 2nd + 3rd question = S/N;
   // Every 4th + 5th question = T/F; Every 6th + 7th question = J/P; and repeats
   public static void tallyScores(int[] scores, int i) {
      if ((i % 7) % 2 == 0) {
         scores[(i % 7) / 2]++;
      }  else {              // ((i % 7) % 2 == 1)
         scores[(i % 7) / 2 + 1]++;
      }
   }
   
   // takes parameters of arrays scoresA and scoresB to calculate 
   // the percentages of "B" answers for type of question. Creates a new
   // array to place results of total B answer percentages and outputs array
   // to external file by taking in PrintStream as parameter.
   // Returns array of int B percentages to main
   public static int[] computePercentage(int[] scoresA, int[] scoresB, PrintStream output) {
      double[] bPercentagesTemp = new double[DIMENSION];
      int[] bPercentages = new int[DIMENSION];
      for (int i = 0; i < bPercentagesTemp.length; i++) {
         bPercentagesTemp[i] = (double) scoresB[i] / (scoresA[i] + scoresB[i]) * 100;
         bPercentages[i] = (int) Math.round(bPercentagesTemp[i]);  // rounds to nearest integer
      }
      output.print(Arrays.toString(bPercentages) + " = ");
      return bPercentages;
   }
   
   // takes in array bPercentages as parameter to determine user's personality 
   // and returns the personality type as String array to main.
   // Personality is based on percentage of B answers user has given for
   // each personality type question
   public static String[] createPersonality(int[] bPercentages) {
      String[] personality = new String[DIMENSION];
      
      for (int i = 0; i <= bPercentages.length / 2; i += 2) {
         if (bPercentages[i] > 50) {
            personality[i] = PERSONALITY_TYPES[i + 1];
         }  else if (bPercentages[i] == 50) {
            personality[i] = "X";   // tie in personality percentages
         }  else {
            personality[i] = PERSONALITY_TYPES[i];
         }
      }
      
      for (int i = 1; i < bPercentages.length; i += 2) {
         if (bPercentages[i] > 50) {
            personality[i] = PERSONALITY_TYPES2[i];
         }  else if (bPercentages[i] == 50) {
            personality[i] = "X";  // tie in personality percentages
         }  else {
            personality[i] = PERSONALITY_TYPES2[i - 1];
         }
      }
      return personality;
   }
   
   // takes in String personality array and PrintStream as parameters to output the
   // personality results to external output file
   public static void printResults(String[] personality, PrintStream output) {
      for (int i = 0; i < personality.length; i++) {
         output.print(personality[i]);
      }
      output.println();
   }
}
