
/*
 * SCHOOL: UNIVERSITY OF THE PEOPLE
 * COURSE: CS1103
 * PROFESSOR: MARC BUENO
 * ASSIGNMENT: UNIT 2 LAB 1-1 RECURSIVE SYNTAX
 * STUDENT: ANONYMOUS
 */

/*
  Some rules that capture the syntax of this assignment:
    
<sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]

<simple_sentence> ::= <noun_phrase> <verb_phrase>

<noun_phrase> ::= <proper_noun> | 
<determiner> [ <adjective> ]. <common_noun> [ who <verb_phrase> ]

<verb_phrase> ::= <intransitive_verb> | 
<transitive_verb> <noun_phrase> |
is <adjective> |
believes that <simple_sentence>

<conjunction> ::= and | or | but | because

<proper_noun> ::= Fred | Jane | Richard Nixon | Miss America

<common_noun> ::= man | woman | fish | elephant | unicorn

<determiner> ::= a | the | every | some

<adjective> ::= big | tiny | pretty | bald

<intransitive_verb> ::= runs | jumps | talks | sleeps

<transitive_verb> ::= loves | hates | sees | knows | looks for | finds
    
  This program implements these rules to generate random sentences. A lot of 
  sentences make no sense (but still follow the syntax).
  
  The program generates and outputs one random sentence every five seconds until
  it is halted (for example, by typing Control-C in the terminal window where it is
  running).
*/
public class RecursiveSyntax {
	
	// These arrays represent the tokens that are used to construct simple sentences using the
	// syntax rules provided in the description above.
   static final String[] conjunctions = { "and", "or", "but", "because", "although", "if" };
   
   static final String[] properNouns = { "David", "Dan", "Eugene"};
   
   static final String[] commonNouns = { "man", "woman", "fish", "elephant", "unicorn", "troll", "thing", "skunk", "child", "company", "family", "hippopotamus", "bird", "cat" }; 
   
   static final String[] determiners = { "a", "the", "every", "some", "this", "that", "my", "your", "his", "her", "its" }; 
   
   static final String[] adjectives = { "big", "tiny", "pretty", "bald", "cute", "active", "agitated", "aged", "blond", "clumsy", "slow", "sexy", "funny" }; 
   
   static final String[] intransitiveVerbs = { "runs", "jumps", "talks", "sleeps", "eats", "reads", "sits", "stinks" }; 
   
   static final String[] transitiveVerbs = { "loves", "hates", "sees", "knows", "looks for", "finds", "talks to", "looks at", "punches" }; 
   
   
   public static void main(String[] args) {
      while (true) {
         randomSentence(); 
	     System.out.println(".\n\n");
         try {
             Thread.sleep(5000);
         }
         catch (InterruptedException e) {
        	 System.out.println("Something went wrong!!!");
         }
      }
   }
   
   // The syntax rules for a randomSentence is:
   // <sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]
   // <simple_sentence> ::= <noun_phrase> <verb_phrase>
   static void randomSentence() {
	  int c = (int)(Math.random() * conjunctions.length);
      
	  // both randomNounPhrase and randomVerbPhrase
	  // have a chance of indirect recursion
	  // by calling each other, or recalling 
	  // randomSentence. Infinite recursion is 
	  // avoided through the use of probability. 
	  randomNounPhrase();
	  randomVerbPhrase();
	  
	  // 75% chance this recursion will occur
      if (Math.random() < 0.5) {
    	  System.out.print(" " + conjunctions[c]);
          randomSentence();
      }
	      
   }
   
   // Syntax rules for a randomNounPhrase:
   // <noun_phrase> ::= <proper_noun> | 
   // <determiner> [ <adjective> ]. <common_noun> [ who <verb_phrase> ]
   static void randomNounPhrase() {
	   
	   // represents the random tokens.
	   int p = (int)(Math.random() * properNouns.length);
	   int d = (int)(Math.random() * determiners.length);
	   int c = (int)(Math.random() * commonNouns.length);
	   
	   if (Math.random() > 0.5) {
		   System.out.print(" " + properNouns[p]);
	   } else {
		   System.out.print(" " + determiners[d]);
		   
		   // if an adjective is to be added, continue adding adjectives 
		   // until the condition is false.
		   while (Math.random() > 0.5) {
			   System.out.print(" " + adjectives[(int)(Math.random() * adjectives.length)]);
		   }
		   
		   System.out.print(" " + commonNouns[c]);
		   
		   if (Math.random() > 0.75) {
			   System.out.print(" who");
			   randomVerbPhrase();
		   }
	   }
   }
   
   
   /* randomVerb syntax rules:
    * <verb_phrase> ::= <intransitive_verb> | 
    * <transitive_verb> <noun_phrase> |
    * is <adjective> |
    * believes that <simple_sentence>
    */
   static void randomVerbPhrase() {
	   
	   // represents the random tokens
	   int intrans = (int)(Math.random() * intransitiveVerbs.length);
	   int trans = (int)(Math.random() * transitiveVerbs.length);
	   int a = (int)(Math.random() * adjectives.length);
	   double rnd = Math.random();

	   if (rnd < 0.25) {
		   System.out.print(" " + intransitiveVerbs[intrans]);
	   } else if (rnd < 0.50) {
		   System.out.print(" " + transitiveVerbs[trans]);
		   randomNounPhrase();
	   } else if (rnd < 0.75) {
		   System.out.print(" is " + adjectives[a]);
	   } else {
		   System.out.print(" believes that");
		   randomSentence();
	   }  
   }
}