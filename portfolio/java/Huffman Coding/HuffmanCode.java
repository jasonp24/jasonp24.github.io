// Jason Park
// HuffmanCode represents a huffman code for a particular message
// by keeping track of a binary tree constructed using Huffman algorithm

import java.util.*; // For access to Scanner and PriorityQueue
import java.io.*; // For access to PrintStream

public class HuffmanCode {
   
   private HuffmanNode overallRoot; // To hold an object
   private Scanner console;   // To read user input
   
   // Takes in parameter int[] frequencies and constructs a HuffmanCode object
   // using the Huffman algorithm for making a code from an array of frequencies
   public HuffmanCode(int[] frequencies) {
      Queue<HuffmanNode> huffmanQueue = new PriorityQueue<>();
      for (int i = 0; i < frequencies.length; i++) {
         if (frequencies[i] > 0) {
            char letter = (char) i;
            int count = frequencies[i];
            HuffmanNode node = new HuffmanNode(letter, count);
            huffmanQueue.add(node);
         }
      }
      while (huffmanQueue.size() > 1) {
         HuffmanNode firstChild = huffmanQueue.remove();
         HuffmanNode secondChild = huffmanQueue.remove();
         int addedCount = firstChild.count + secondChild.count;
         HuffmanNode combine = new HuffmanNode(' ', addedCount, firstChild, secondChild);
         huffmanQueue.add(combine);
      }
      overallRoot = huffmanQueue.remove();
   }
   
   // Takes in parameter Scanner input and initilizes new HuffmanCode object by reading in
   // previously constructed code from .code file
   // Assume scanner is not null and contains data encoded in legal, valid standard format
   public HuffmanCode(Scanner input) {
      while(input.hasNextLine()) {
         int asciiValue = Integer.parseInt(input.nextLine());
         String code = input.nextLine();
         overallRoot = HuffmanCodeHelper(overallRoot, asciiValue, code);
      }
   }
   
   // Takes in parameter HuffmanNode current, int asciiValue, and String code and
   // initializes a new HuffmanCode object by reading in previously constructed
   // code from .code file, returning binary tree
   // Assume scanner is not null and contains data encoded in legal, valid standard format
   private HuffmanNode HuffmanCodeHelper(HuffmanNode current, int asciiValue, String code) {
      if (current == null) {
         current = new HuffmanNode(' ', 0);
      }
      char letter = (char) asciiValue;
      if (code.length() == 1) {  // base case
         if (code.equals("0")) {
            current.left = new HuffmanNode(letter, 0);
         } else {
            current.right = new HuffmanNode(letter, 0);
         }
      } else { // recursive case
         char nextMove = code.charAt(0);
         code = code.substring(1);
         if (nextMove == '0') {
            current.left = HuffmanCodeHelper(current.left, asciiValue, code);
         } else {
            current.right = HuffmanCodeHelper(current.right, asciiValue, code);
         }
      }
      return current;
   }
   
   // Takes in parameter PrintStream output and stores current huffman code to given
   // output stream in standard format
   public void save(PrintStream output) {
      saveHelper(output, overallRoot, "");
   }
   
   // Takes in parameter PrintStream output, HuffmanNode current, and String huffmanCode
   // to store current huffman code to given output stream in standard format
   private void saveHelper(PrintStream output, HuffmanNode current, String huffmanCode) {
      if(current.left == null && current.right == null) {   // base case
         int asciiValue = (int) current.letter;
         output.println(asciiValue);
         output.println(huffmanCode);
      } else {    // recursive case
         saveHelper(output, current.left, huffmanCode + "0");
         saveHelper(output, current.right, huffmanCode + "1");
      }
   }
   
   // Takes in parameters BitInputStream input and PrintStream output to read individual bits
   // from the input stream and write the corresponding characters to the output
   // Assume that the input stream contains legal encoding of characters
   public void translate(BitInputStream input, PrintStream output) {
      while (input.hasNextBit()) {
         translateHelper(input, output, overallRoot);
      }
   }
   
   // Takes in parameters BitInputStream input, PrintStream output, and HuffmanNode current
   // to read individual bits from input stream and write the corresponding characters to the output,
   // returning binary tree
   // Assume that the input stream contains legal encoding of characters
   private HuffmanNode translateHelper(BitInputStream input, PrintStream output, HuffmanNode current) {
      if (current.left == null && current.right == null) {  // base case
         output.write(current.letter);
      } else {    // recursive case
         int number = input.nextBit();
         if (number == 0) {
            current.left = translateHelper(input, output, current.left);
         } else {
            current.right = translateHelper(input, output, current.right);
         }
      }
      return current;
   }
   
   // Class for storing a single node of a binary tree of char and int
   // and implements the Comparable interface
   private static class HuffmanNode implements Comparable<HuffmanNode> {
      public char letter;
      public int count;
      public HuffmanNode left;
      public HuffmanNode right;
      
      // Takes in parameter char letter, and int count to
      // construct a HuffmanNode with given data
      public HuffmanNode(char letter, int count) {
         this(letter, count, null, null);
      }
      
      // Takes in parameters char letter, int count, HuffmanNode left, and HuffmanNode right
      // to construct a HuffmanNode with given data, left subtree, and
      // right subtree
      public HuffmanNode(char letter, int count, HuffmanNode left, HuffmanNode right) {
         this.letter = letter;
         this.count = count;
         this.left = left;
         this.right = right;
      }
      
      // Compares this HuffmanNode against the given HuffmanNode, comparing on
      // frequency and returns int result after comparing
      public int compareTo(HuffmanNode other) {
         int result = Integer.compare(this.count, other.count);
         return result;
      }
   }
}
