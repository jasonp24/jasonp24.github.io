// Jason Park
// AssassinManager is a class that allows a client to manage a game of Assassin

import java.util.*;  // To have access to List

public class AssassinManager {
   
   public AssassinNode frontKillRing;  //Reference to front of kill ring
   public AssassinNode frontGraveyard; //Reference to front of Graveyard
   
   // Takes in parameter List<String> to construct an AssassinManager object
   // as the initial kill ring. Adds names from list into kill ring in
   // same order they appear in list
   // Assume that names are nonempty strings and there are no duplicate names (ignoring case)
   // pre: List is not empty
   //      (throws IllegalArgumentException if not)
   // post: constructs AssassinManager object as initial kill ring
   public AssassinManager(List<String> names) {
      if (names.size() < 1) {
         throw new IllegalArgumentException();
      }
      for (int i = names.size() - 1; i >= 0; i--) {
         frontKillRing = new AssassinNode(names.get(i), frontKillRing);
      }
   }
   
   // Prints names of people in Kill Ring and reports who is stalking who in
   // the form: One name per line, indented 4 spaces
   // If only one person in kill ring, report that person is stalking themselves
   public void printKillRing() {
      AssassinNode current = frontKillRing;
      while(current.next != null) {
         System.out.println("    " + current.name + " is stalking " + current.next.name);
         current = current.next;
      }
      System.out.println("    " + current.name + " is stalking " + frontKillRing.name);
   }
   
   // Prints names of people in graveyard in reverse kill order
   // and reports who was killed by who in
   // the form: One per line, indented 4 spaces.
   // Produces no output if graveyard is empty
   public void printGraveyard() {
      AssassinNode current = frontGraveyard;
      while(current != null) {
         System.out.println("    " + current.name + " was killed by " + current.killer);
         current = current.next;
      }
   }
   
   // Takes in parameter String name and
   // returns true if given name is in current kill ring,
   // and returns false if otherwise.
   public boolean killRingContains(String name) {
      AssassinNode list = frontKillRing;
      return listContains(name, list);
   }
   
   // Takes in parameter String name and
   // returns true if given name is in current graveyard,
   // and returns false if otherwise.
   public boolean graveyardContains(String name) {
      AssassinNode list = frontGraveyard;
      return listContains(name, list);
   }
   
   // Returns true if game is over (kill ring has just one person in it)
   // and returns false if otherwise
   public boolean gameOver() {
      return (frontKillRing.next == null);
   }
   
   // Returns name of winner of game
   // Returns null if game is not over
   public String winner() {
      if (gameOver()) {
         return frontKillRing.name;
      } else { // Game is not over
         return null;
      }
   }
   
   // Takes in parameter String name to kill and transfer person
   // with given name from Kill Ring to Graveyard, ignoring case when
   // comparing name
   // pre: Parameter name is found in current kill ring
   //      (throws IllegalArguementException if not),
   //      Assassin game is not over
   //      (throws IllegalStateException if not)
   // post: Records killing of person with given name by transferring
   //       person from kill ring to graveyard
   public void kill(String name) {
      if (!killRingContains(name)) {
         throw new IllegalArgumentException();
      } else if (gameOver()) {
         throw new IllegalStateException();
      }
      
      AssassinNode current = frontKillRing;
      AssassinNode personKilled = frontGraveyard;
      // If person to kill is in front
      if (current.name.equalsIgnoreCase(name)) {
         personKilled = current;
         while(current.next != null) {
            current = current.next;
         }
         frontKillRing = frontKillRing.next;
      } else {    // person to kill is not in front
         while(!current.next.name.equalsIgnoreCase(name)) {
            current = current.next;
         }
         personKilled = current.next;
         current.next = current.next.next;
      }
      personKilled.killer = current.name;
      personKilled.next = frontGraveyard;
      frontGraveyard = personKilled;
   }
   
   // Takes in parameters String name
   // and AssassinNode list and returns true if name is in list
   // and false if otherwise, ignoring case when comparing names
   private boolean listContains(String name, AssassinNode list) {
      while(list != null) {
         if (list.name.equalsIgnoreCase(name)) {
            return true;
         }
         list = list.next;
      }
      return false;
   }
}
