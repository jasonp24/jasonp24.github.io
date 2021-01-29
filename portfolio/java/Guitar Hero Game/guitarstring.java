// Jason Park
// GuitarString models a vibrating guitar string of a given frequency

import java.util.*;  // For Random class

public class GuitarString {
   
   // Number of samples per second as Sampling Rate
   public static final int SAMPLING_RATE = 44100;
   // Energy decay factor
   public static final double DECAY_RATE = 0.996;
   
   // Stores displacement values of guitar string
   private Queue<Double> ringBuffer;
   
   // Takes in parameter of double frequency to construct guitar string at rest
   // pre: frequency is greater than 0 or
   //      resulting ring buffer size is greater than 2
   //      (throws IllegalArgumentException if either condition is not met)
   // post: constructs guitar string of given frequency
   public GuitarString(double frequency) {
      if (frequency <= 0) {
         throw new IllegalArgumentException();
      }
      int size = (int) Math.round(SAMPLING_RATE / frequency);
      if (size < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();
      for (int i = 0; i < size; i++) {
         ringBuffer.add(0.0);
      }
   }
   
   // Takes in parameter double[] init to construct guitar string and
   // initialize contents of ring buffer to values in array
   // pre: contents of array is greater than or equal to 2
   //      (throws IllegalArgumentException if not)
   // post: Constructs guitar string that is used only for testing/debugging
   public GuitarString(double[] init) {
      if (init.length <= 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
   }
   
   // Replaces all elements in ring buffer with a random number between
   // -0.5 inclusive and +0.5 exclusive
   public void pluck() {
      Random rand = new Random();
      for (int i = 0; i < ringBuffer.size(); i++) {
         ringBuffer.remove();
         double randomValue = rand.nextDouble() - 0.5;
         ringBuffer.add(randomValue);   
      }
   }
   
   // Applies Karplus Strong update once
   public void tic() {
      double firstValue = ringBuffer.remove();
      double nextValue = ringBuffer.peek();
      double karpusUpdate = DECAY_RATE * (0.5 * (firstValue + nextValue));
      ringBuffer.add(karpusUpdate);
   }
   
   // Returns value at the front of the ring buffer
   public double sample() {
      return ringBuffer.peek();
   }
}
