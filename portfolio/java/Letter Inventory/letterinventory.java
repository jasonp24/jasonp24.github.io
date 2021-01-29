// Jason Park
// The LetterInventory represents a list of integers that keeps
// track of an inventory of alphabetic letters

public class LetterInventory {
   private int[] elementData;    // list of integers
   private int size;    // number of elements in list
   
   // Constant representing the length of the alphabet
   public static final int ALPHABET_LENGTH = 26;
   
   // Takes in a parameter String data to construct an inventory (counts) of
   // each alphabetic letter's lowercase equivalent in String data
   public LetterInventory(String data) {
      elementData = new int[ALPHABET_LENGTH];
      size = 0;
      data = data.toLowerCase();
      for (int i = 0; i < data.length(); i++) {
         char token = data.charAt(i);
         if (Character.isLetter(token)) {
            elementData[token - 'a']++;
            size++;
         }
      }
   }
   
   // Constructs an empty inventory of alphabetic letters
   public LetterInventory() {
      this("");
   }
   
   // Method that returns sum of all counts in
   // inventory as an integer.
   public int size() {
      return size;
   }
   
   // Method that returns true if inventory is empty
   // and false if inventory is not empty.
   public boolean isEmpty() {
      return size == 0;
   }
   
   // Takes in parameter of char letter and returns integer count of how many
   // of this letter's lowercase equivalent are in inventory
   // pre: char letter is an alphabetic character (throws IllegalArgumentException if not)
   // post: returns count of how many of this letter are in inventory,
   //       if any
   public int get(char letter) {
      if (!Character.isLetter(letter)) {
         throw new IllegalArgumentException("nonalphabetic character: " + letter);
      }
      int index = charIndex(letter);
      return elementData[index];
   }
   
   // Returns String representation of inventory with all
   // lowercase letters in sorted alphabetic order, surrounded by
   // square brackets.
   public String toString() {
      if (size == 0) {
         return "[]";
      } else {
         String result = "[";
         for (int i = 0; i < ALPHABET_LENGTH; i++) {
            for (int j = 0; j < elementData[i]; j++) {
               int letterInt = i + 'a';
               result += (char) letterInt;
            }
         }
         result += "]";
         return result;
      }
   }
   
   // Takes in parameters of char letter and int value to set the count for given letter's
   // lowercase equivalent to given value in inventory.
   // pre: letter is an alphabetic character and value is positive,
   //      (throws IllegalArgumentException if not)
   // post: allows client to set number of occurrences of individual letter in inventory
   public void set(char letter, int value) {
      if (!Character.isLetter(letter) || value < 0) {
         throw new IllegalArgumentException("nonalphabetic character or negative value entered");
      }
      int index = charIndex(letter);
      if (value == 0) {
         size -= elementData[index];
         elementData[index] = 0;
      } else {    //value > 0
         elementData[index] = value;
         size += value;
      }
   }
   
   // Takes in parameter of other Inventory to construct and return
   // new Inventory object after summing the counts for each
   // letter in each Inventory together.
   public LetterInventory add(LetterInventory other) {
      LetterInventory addResult = new LetterInventory();
      for (int i = 0; i < ALPHABET_LENGTH; i++) {
         addResult.elementData[i] = this.elementData[i] + other.elementData[i];
      }
      addResult.size = this.size + other.size;
      return addResult;
   }
   
   // Takes in parameter of other Inventory to construct and return
   // new Inventory object after subtracting the counts for each
   // letter in the other inventory from this inventory's count.
   // pre: after subtraction, any resulting count is positive,
   //      (returns null if resulting count is negative)
   // post: returns newly constructed Inventory object
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory subtractResult = new LetterInventory();
      for (int i = 0; i < ALPHABET_LENGTH; i++) {
         subtractResult.elementData[i] = this.elementData[i] - other.elementData[i];
         if (subtractResult.elementData[i] < 0) {
            return null;
         }
      }
      subtractResult.size = this.size - other.size;
      return subtractResult;
   }
   
   // Takes in parameter of char letter and returns the displacement of
   // letter from the fixed character 'a' as its lowercase integer equivalent
   private int charIndex (char letter) {
      return (Character.toLowerCase(letter) - 'a');
   }
}
