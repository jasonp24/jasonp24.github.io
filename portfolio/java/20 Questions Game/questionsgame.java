// Jason Park
// QuestionsGame is a class that represents a tree of questions and answers
// to determine an object when playing a game of 20 Questions

import java.util.*; // Access to Scanner
import java.io.*; // Access to PrintStream

public class QuestionsGame {
   
   private QuestionNode overallRoot;   // To hold an object
   private Scanner console;   // To read user input
   
   // Constructs a new QuestionsGame object
   // represting the object "computer"
   public QuestionsGame() {
      overallRoot = new QuestionNode("computer");
      console = new Scanner(System.in);
   }
   
   // post: Takes in parameter String prompt and asks the user a question,
   //       forcing an answer of "y" or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }
   
   // Takes in parameter Scanner input to read in a new tree from a file
   // and replaces current tree with the new tree using information from
   // the file
   // Assume the file is legal and in standard format
   public void read(Scanner input) {
      overallRoot = readHelper(input);
   }
   
   // Takes in parameter Scanner input to read in a new tree from a file
   // and replaces current tree with the new tree using information from
   // the file, returning QuestionsGame tree
   // Assume the file is legal and in standard format
   private QuestionNode readHelper(Scanner input) {
      String type = input.nextLine();
      QuestionNode root = new QuestionNode("");
      if (type.startsWith("A:")) {  // Base Case: Answer = Leaf Node
         String answer = input.nextLine();
         root = new QuestionNode(answer);
      } else { // Recursive Case : Question = Branch Node
         String question = input.nextLine();
         root = new QuestionNode(question, readHelper(input), readHelper(input));
      }
      return root;
   }
   
   // Takes in parameter PrintStream output to store the current questions
   // tree to an output file represented by the given PrintStream
   public void write(PrintStream output) {
      writeHelper(output, overallRoot);
   }
   
   // Takes in parameter PrintStream output and QuestionNode current to
   // store the current questions tree to an output file represented by
   // the given PrintStream
   private void writeHelper(PrintStream output, QuestionNode current) {
      if (current.left == null && current.right == null) {  // Base Case: Leaf Node
         output.println("A:");
         output.println(current.data);
      } else {    // Recursive Case: Branch Node
         output.println("Q:");
         output.println(current.data);
         writeHelper(output, current.left);
         writeHelper(output, current.right);
      }
   }
   
   // Uses the current question tree to play one complete game of 20 Questions
   // The computer wins if it guesses the user's object correctly, and prints out a message
   // saying so
   // Otherwise, if the computer loses, it will add the object the user was thinking
   // of to its knowledge base
   public void askQuestions() {
      overallRoot = askQuestions(overallRoot);
   }
   
   // Takes in parameter QuestionNode current and uses the current question tree
   // to play one complete game of 20 Questions, returning QuestionsGame tree
   // The computer wins if it guesses the user's object correctly and prints out a message
   // saying so
   // Otherwise, if the computer loses, it will add the object the user was thinking
   // of to its knowledge base
   private QuestionNode askQuestions(QuestionNode current) {
      if (current.left == null && current.right == null) {  // Base case: Leaf Node
         if (yesTo("Would your object happen to be " + current.data + "?")) {
            System.out.println("Great, I got it right!");
         } else {
            System.out.print("What is the name of your object? ");
            String answer = console.nextLine();
            QuestionNode userObject = new QuestionNode(answer);
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            String question = console.nextLine();
            if (yesTo("And what is the answer for your object?")) {
               current = new QuestionNode(question, userObject, current);
            } else {
               current = new QuestionNode(question, current, userObject);
            }
         }
      } else { // Recursive case: Branch Node
         if (yesTo(current.data)) {
            current.left = askQuestions(current.left);
         } else {
            current.right = askQuestions(current.right);
         }
      }
      return current;
   }
   
   // Class for storing a single node of a binary tree of Strings
   private static class QuestionNode {
      public String data;
      public QuestionNode left;
      public QuestionNode right;
      
      // Takes in parameter String data and constructs a leaf node with given data
      public QuestionNode(String data) {
         this(data, null, null);
      }
      
      // Takes in parameters String data, QuestionNode left, and QuestionNode right and
      // constructs a branch node with given data, left subtree, and
      // right subtree
      public QuestionNode(String data, QuestionNode left, QuestionNode right) {
         this.data = data;
         this.left = left;
         this.right = right;
      }
   }
}
