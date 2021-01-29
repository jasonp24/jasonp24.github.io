// Jason Park
// 04/28/2020
// Assignment #4

/* This program computes the overall score of two applicants
   for comparison that may be used for college admissions.
   This program uses an interactive Scanner console, if/else
   statements, and static methods/methods that return values. */


import java.util.*;

public class Admit {
   public static void main(String[] args) {
      giveIntro();
      Scanner console = new Scanner(System.in);
      
      double examScore1 = calcExamScore(console, 1);
      double gpaScore1 = calcGPAScore(console);
      
      double examScore2 = calcExamScore(console, 2);
      double gpaScore2 = calcGPAScore(console);
 
      double totalScore1 = calcTotal(examScore1, gpaScore1);
      double totalScore2 = calcTotal(examScore2, gpaScore2);
      
      reportResults(totalScore1, totalScore2);
   }
   
   // This method gives an introduction of the interactive program
   public static void giveIntro() {
      System.out.println("This program compares two applicants to");
      System.out.println("determine which one seems like the stronger");
      System.out.println("applicant.  For each candidate I will need");
      System.out.println("either SAT or ACT scores plus a weighted GPA.");
      System.out.println();
   }
   
   /* This method is used to return the exam score of the applicant based on
      their exam type */
   public static double calcExamScore (Scanner console, int applicantNumber) {
      System.out.println("Information for applicant #" + applicantNumber + ":");
      System.out.print("    do you have 1) SAT scores or 2) ACT scores? ");
      int applicantChoice = console.nextInt();
      double examScore;
         if (applicantChoice == 1) {
             examScore = calcSATScore(console);
         } else {
             examScore = calcACTScore(console);
         }
      System.out.println("    exam score = " + examScore);
      return examScore;
   }
   
   // This method is used to return user's SAT exam score based on input values
   public static double calcSATScore (Scanner console) {
      System.out.print("    SAT math? ");
      int math = console.nextInt();
      System.out.print("    SAT critical reading? ");
      int reading = console.nextInt();
      System.out.print("    SAT writing? ");
      int writing = console.nextInt();
      double examScore = getSAT(math, reading, writing);
      return examScore;
   }
   
   // This method is used to calculate user's SAT exam score
   public static double getSAT(int math, int reading, int writing) {
      double examScore = (2 * math + reading + writing) / 32.0;
      return roundNumber(examScore);
   }
   
   // This method is used to return user's ACT exam score based on input values
   public static double calcACTScore (Scanner console) {
      System.out.print("    ACT English? ");
      int english = console.nextInt();
      System.out.print("    ACT math? ");
      int math = console.nextInt();
      System.out.print("    ACT reading? ");
      int reading = console.nextInt();
      System.out.print("    ACT science? ");
      int science = console.nextInt();
      double examScore = getACT(english, math, reading, science);
      return examScore;
   }
   
   // This method is used to calculate user's ACT exam score
   public static double getACT(int english, int math,
                               int reading, int science) {
      double examScore = (english + 2 * math + reading + science) / 1.8;
      return roundNumber(examScore);
   }
   
   // This method is used to return user's GPA score based on input values
   public static double calcGPAScore (Scanner console) {
      System.out.print("    overall GPA? ");
      double actualGPA = console.nextDouble();
      System.out.print("    max GPA? ");
      double maxGPA = console.nextDouble();
      System.out.print("    Transcript Multiplier? ");
      double multiplier = console.nextDouble();
      double gpaScore = getGPA(actualGPA, maxGPA, multiplier);
      System.out.println("    GPA score = " + gpaScore);
      System.out.println();
      return gpaScore;
   }
   
   // This method is used to calculate user's GPA score
   public static double getGPA(double actualGPA, double maxGPA,
                               double multiplier) {
      double gpaScore = (actualGPA / maxGPA) * 100 * multiplier;
      return roundNumber(gpaScore);
   }
   
   /* This method is used to calculate user's Overall Score based
      on Exam and GPA score*/ 
   public static double calcTotal(double examScore, double gpaScore) {
   return examScore + gpaScore;
   }
   
   // This method reports the results of the applicant's score
   public static void reportResults(double totalScore1, double totalScore2) {
      System.out.println("First applicant overall score  = " + roundNumber(totalScore1));
      System.out.println("Second applicant overall score = " + roundNumber(totalScore2));
      compareStatus(totalScore1, totalScore2);
   }
   
   // This method returns the comparison status' of the applicants  
   public static void compareStatus(double totalScore1, double totalScore2) {     
         if (totalScore1 > totalScore2) {
            System.out.println("The first applicant seems to be better");
         } else if (totalScore1 < totalScore2) {
           System.out.println("The second applicant seems to be better");
         } else {
            System.out.println("The two applicants seem to be equal");
         }
   }
   
   // This method is used to round a number to one decimal place
   public static double roundNumber(double number) {
      return Math.round(number * 10.0) / 10.0;
   }
}
