// Jason Park
// 4/7/2020
// CSE 142
// TA: 
// Assignment #1

/* This program will output a cumulative song in which
  successive verses build on previous verses */
  
public class Song {
   public static void main (String[] args) {
      stanzaOne();
      System.out.println();
      stanzaTwo();
      System.out.println();
      stanzaThree();
      System.out.println();
      stanzaFour();
      System.out.println();
      stanzaFive();
      System.out.println();
      stanzaSix();                
      System.out.println();
      stanzaSeven();
   }
   
   // produces first stanza of poem
   public static void stanzaOne() {
      System.out.println("There was an old woman who swallowed a fly.");
      ending();  
   }
   // produces second stanza of poem
   public static void stanzaTwo() {
      System.out.println("There was an old woman who swallowed a spider,");
      System.out.println("That wriggled and iggled and jiggled inside her.");
      successiveVerseOne();
      ending();  
   }
   // produces third stanza of poem
   public static void stanzaThree() {
      System.out.println("There was an old woman who swallowed a bird,");
      System.out.println("How absurd to swallow a bird.");
      successiveVerseTwo();
      successiveVerseOne();
      ending();
   }
   // produces fourth stanza of poem
   public static void stanzaFour() {
      System.out.println("There was an old woman who swallowed a cat,");
      System.out.println("Imagine that to swallow a cat.");
      successiveVerseThree();
      successiveVerseTwo();
      successiveVerseOne();
      ending();
   }
   // produces fifth stanza of poem
   public static void stanzaFive() {
      System.out.println("There was an old woman who swallowed a dog,");
      System.out.println("What a hog to swallow a dog.");
      successiveVerseFour();
      successiveVerseThree();
      successiveVerseTwo();
      successiveVerseOne();
      ending();
   }
   // produces custom sixth stanza of poem
   public static void stanzaSix() {                                      
      System.out.println("There was an old woman who swallowed a bat,");
      System.out.println("It would be better if she swallowed a rat.");
      System.out.println("She swallowed the bat to catch the dog,");
      successiveVerseFour();
      successiveVerseThree();
      successiveVerseTwo();
      successiveVerseOne();
      ending();
   }
   // produces final seventh stanza of poem
   public static void stanzaSeven() {
      System.out.println("There was an old woman who swallowed a horse,");
      System.out.println("She died of course.");
   }
   // produces redundant ending found in last two lines of each stanza
   public static void ending() {                                        
      System.out.println("I don't know why she swallowed that fly,");
      System.out.println("Perhaps she'll die.");         
   }
   // produces first verse that will be used to build upon 
   public static void successiveVerseOne() {
      System.out.println("She swallowed the spider to catch the fly,");
   }
   // produces second successive verse built upon first verse
   public static void successiveVerseTwo() {
      System.out.println("She swallowed the bird to catch the spider,");
   }
   // produces third successive verse built upon previous successive verse
   public static void successiveVerseThree() {
      System.out.println("She swallowed the cat to catch the bird,");
   }
   // produces fourth successive verse built upon previous successive verse
   public static void successiveVerseFour() {
      System.out.println("She swallowed the dog to catch the cat,");   
   }
   
}
