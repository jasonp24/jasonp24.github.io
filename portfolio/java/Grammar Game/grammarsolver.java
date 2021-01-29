// Jason Park
// GrammarSolver reads an input file with a grammar in Backus-Naur Form (BNF)
// and allows the client to randomly generate elements of the grammar.

import java.util.*; // for access to Random and Map Interface

public class GrammarSolver {
   
   private Map<String, String[]> sentenceParts;    // Holds pairs of grammar
   
   // Takes in parameter List<String> grammar and stores this in
   // convenient way to be used to later generate parts of grammar
   // pre: Grammer is not empty and entry in grammar for same nonterminal does not already exist
   //      (throw IllegalArgumentException if not)
   // post: Stores grammar in convenient way to be used to later generate parts of grammar
   public GrammarSolver(List<String> grammar) {
      if (grammar.isEmpty()) {   // if grammar is empty
         throw new IllegalArgumentException();
      }
      sentenceParts = new TreeMap<>();
      for (String parts : grammar) {
         String[] components = parts.split("::=");
         String key = components[0];
         if (grammarContains(key)) { //if 2+ entries in grammar for same nonterminal
            throw new IllegalArgumentException();
         }
         String[] values = components[1].trim().split("[|]");
         sentenceParts.put(key, values);
      }
   }
   
   // Takes in parameter String symbol and returns true if given symbol is
   // a nonterminal of grammar and false if otherwise
   public boolean grammarContains(String symbol) {
      return sentenceParts.containsKey(symbol);
   }
   
   // Takes in parameters String symbol and int times to randomly generate,
   // with equal probability, given number of occurrences of given symbol,
   // returning result as array of strings
   // pre: Grammer contains given nonterminal symbol and times is positive
   //      (throws IllegalArgumentException if either condition is not met)
   // post: randomly generates number of occurrences of symbol and returns result as
   //       array of strings
   public String[] generate(String symbol, int times) {
      if (!grammarContains(symbol) || times < 0) {
         throw new IllegalArgumentException();
      }
      String[] sentences = new String[times];
      for (int i = 0; i < sentences.length; i++) {
         sentences[i] = generate(symbol, "");
      }
      return sentences;
   }
   
   // Takes in parameter String symbol and String sentence to randomly generate,
   // with equal probability, a single string of given symbol and
   // returning the string result
   private String generate(String symbol, String sentence) {
      Random r = new Random();
      
      String[] terminalOptions = sentenceParts.get(symbol);
      int randomNumber = r.nextInt(terminalOptions.length);
      String[] terminalChosen = terminalOptions[randomNumber].split("[ \t]+");
      
      for (String rules : terminalChosen) {
         if (!grammarContains(rules)) { //base case: when rule is terminal
            sentence += rules + " ";
         } else {                      //recursive case: if rule is non-terminal
            sentence += generate(rules, "") + " ";
         }
      }
      return sentence.trim();
   }
   
   // Returns string representation of all nonterminal symbols from
   // the grammar as a sorted, comma-seperated list enclosed in square
   // brackets
   public String getSymbols() {
      return sentenceParts.keySet().toString();
   }
}
