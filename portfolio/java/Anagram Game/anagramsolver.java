// Jason Park
// AnagramSolver allows the client to find all the anagrams of a given phrase

import java.util.*; // For access to Map interface and List

public class AnagramSolver {
   
   private Map<String, LetterInventory> dictionary;   // Dictionary of words + their inventories
   private List<String> holdingList;   // Holds client's input
   
   // Takes in parameter List<String> list to construct an anagram solver
   // that will use given list as its dictionary.
   // Assume dictionary is nonempty collection of nonempty sequences of
   // letters and contains no duplicates
   public AnagramSolver(List<String> list) {
      holdingList = list;
      dictionary = new HashMap<>();
      
      for (String word : holdingList) {
         LetterInventory wordInventory = new LetterInventory(word);
         dictionary.put(word, wordInventory);
      }
   }
   
   // Takes in parameters String s and int max to find and print all combinations of words
   // from dictionary that are anagrams of String s and include at most max words
   // pre: max is not less than 0
   //      (throws IllegalArgumentException if not)
   // post: Finds and prints all combinations of words from dictionary that are anagrams
   //       of String s and that include at most max words
   //       (unlimited words if max is 0)
   public void print(String s, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      LetterInventory sentence = new LetterInventory(s);
      Stack<String> printString = new Stack<>();
      List<String> pruneList = new ArrayList<>();
      pruneDict(sentence, pruneList);
      print(sentence, printString, pruneList, max);
   }
   
   // Takes in parameters LetterInventory sentence, Stack<String> printString,
   // List<String> pruneList, and int max to find and print all combinations of words
   // from dictionary that are anagrams of String s and include at most max words
   // (unlimited words if max is 0)
   private void print(LetterInventory sentence, Stack<String> printString, 
                      List<String> pruneList, int max) {
      if (sentence.isEmpty()) {  // base case
         System.out.println(printString);
      } else if (max == 0 || max != printString.size()) {
         for (String word : pruneList) {
            LetterInventory scrambledWord = dictionary.get(word);
            LetterInventory temp = sentence;
            sentence = sentence.subtract(scrambledWord);
            if (sentence != null) {    // recursive case
               printString.push(word);
               print(sentence, printString, pruneList, max);
               printString.pop();
               sentence = temp;
            } else {
               sentence = temp;
            }
         }
      }
   }
   
   // Takes in parameter LetterInventory sentence and List<String> pruneList to prune
   // dictionary to a smaller dictionary of only relevant words based on sentence
   private void pruneDict(LetterInventory sentence, List<String> pruneList) {
      for (String word : holdingList) {
         if (sentence.subtract(dictionary.get(word)) != null) {
            pruneList.add(word);
         }
      }
   }
}
