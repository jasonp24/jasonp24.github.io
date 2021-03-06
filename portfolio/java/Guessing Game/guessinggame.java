// Jason Park
// 05/04/2020


/* This program allows the user to play a simple guessing game
in which your program thinks up an integer and hints, and
allows the user to make guesses until the user gets it right.
This program uses an interactive Scanner console, a Random object
that generates pseudo-random numbers, if/else and while statements,
methods that return values, and a class constant for the max number used. */



import java.util.*;


public class Guess {
   public static final int MAX = 100;
   public static void main(String[] args) {
      
      Scanner console = new Scanner(System.in);
      Random rand = new Random();
      int number = rand.nextInt(MAX) + 1;
      
      giveIntro();
      int numGuesses = playGame(console, number);
      reportNumGuesses(numGuesses);
      
      /* while loop in main that plays multiple games and prompts
      the user for whether or not to play another game */
      String reply = playAgain(console);
      int sumGames = 1;
      int sumGuesses = numGuesses;
      int bestGame = numGuesses;
      while (reply.startsWith("y") || reply.startsWith("Y")) {
         number = rand.nextInt(MAX) + 1;
         numGuesses = playGame(console, number);
         reportNumGuesses(numGuesses);
         if (bestGame > numGuesses) {
            bestGame = numGuesses;
         }
         reply = playAgain(console);
         sumGuesses += numGuesses;
         sumGames++;
      }
      
      overallResults(sumGuesses, sumGames, bestGame);
      
   }
   
   /* this method gives the user the introduction of the game and the max number that the
   pseudo-random object can generate. */
   public static void giveIntro() {
      System.out.println("This program allows you to play a guessing game.");
      System.out.println("I will think of a number between 1 and");
      System.out.println(MAX + " and will allow you to guess until");
      System.out.println("you get it.  For each guess, I will tell you");
      System.out.println("whether the right answer is higher or lower");
      System.out.println("than your guess.");
      System.out.println();
   }
   
   /* This method is used to play one game with the user. It returns the number
   of guesses attempted until the correct answer is found. This method takes in
   the following parameters:
   -Scanner console: takes in user input
   -int number: takes in the pseudo-random integer generated by the program */
   public static int playGame (Scanner console, int number) {
      System.out.println("I'm thinking of a number between 1 and " + MAX + "...");
      System.out.print("Your guess? ");
      int guess = console.nextInt();
      int numGuesses = 1;
      while (guess != number) {
         hint(number, guess);
         System.out.print("Your guess? ");
         guess = console.nextInt();
         numGuesses++;
      }
      return numGuesses;
   }
   
   /* This method takes in the pseudo-random number and user input to
   produce a hint that will help the user guess the random number. This
   method takes in the following parameters that are used to determine which
   hint is outputted:
   -int number: takes in pseudo-random integer generated by the program
   -int guess: takes in the user-inputed guess */
   public static void hint (int number, int guess) {
      if (guess < number) {
         System.out.println("It's higher.");
      }
      else {
         System.out.println("It's lower.");
      }
   }
   
   /* This method takes in the number of guesses as a parameter and reports the number
   of guesses it took for the user to guess correctly. If the user guessed correctly in
   one guess, then the program will display the appropriate message */
   public static void reportNumGuesses(int numGuesses) {
      if (numGuesses != 1) {
         System.out.println("You got it right in " + numGuesses + " guesses");
      }
      else {
         System.out.println("You got it right in 1 guess");
      }
   }
   
   /* This method takes in the Scanner console to find user input
   to determine if the user wants to play additional games after
   the first time playing */
   public static String playAgain (Scanner console) {
      System.out.print("Do you want to play again? ");
      String reply = console.next();
      System.out.println();
      return reply;
   }
   
   /* This method takes in parameters of the user's game as statistics
   to output the total number of games played, the total number of
   guesses by the user in all games, the average guesses per game,
   and the best (fewest) number of guesses used in any single game*/
   public static void overallResults(int sumGuesses, int sumGames, int bestGame) {
      System.out.println("Overall results:");
      System.out.println("    total games   = " + sumGames);
      System.out.println("    total guesses = " + sumGuesses);
      System.out.println("    guesses/game  = " + roundNumber((double)sumGuesses / sumGames));
      System.out.println("    best game     = " + bestGame);
   }
   
   /* This method is takes in a double number as a parameter
   and is used to round a number to one decimal place */
   public static double roundNumber(double number) {
      return (Math.round(number * 10.0)) / 10.0;
   }
}
