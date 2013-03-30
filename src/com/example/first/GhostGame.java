package com.example.first;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * In the game of Ghost, user and a computer take turns building up an English 
 * word from left to right. Each player adds 1 letter per turn. The goal is to
 * not complete the spelling of a word: if any player adds a letter that completes a 
 * word (of 4+ letters), or if any player adds a letter that produces a string that 
 * cannot be extended into a word, this player loses. User start first.
 * 
 * This is a class that models logic of a Ghost Game. Game accepts user's symbol
 * using acceptUserLetter() method, then calculates its own symbol. Game continues 
 * until !isGameOver().  
 *  
 * How computer chooses a symbol: computer chooses all words from 'words' list, 
 * which have getWords() as a substring. Then computer tries to choose words which
 * are supposed to be completed on user's turn (with length 5,7,9,...) and chooses 
 * correct symbol (symbol with length equal to getWord().length()) from any random word.
 * If such words do not exist, then computer chooses the longest word). 
 */
public class GhostGame implements Serializable{
 // instant variables
 public static enum Winner {USER, COMPUTER};
 private StringBuilder theWord;// the word user and computer build
 private boolean gameOver; 
 private Winner winner;    
 private ArrayList<ArrayList<String>> words; // words read from the file
 private ArrayList<String> alphabet; // just an English alphabet
 
 // constructor
 /**
  * Creates instance of a game
  */
 public GhostGame(ArrayList<ArrayList<String>> words){
   theWord = new StringBuilder(); 
   gameOver = false;
   winner = null;
   this.words = words;
   
   char j='a';
   alphabet = new ArrayList<String>(); //
   // creates an alphabet
   for(int i=0;i<26;i++){
        alphabet.add(j+"");
        j++; 
   }
 }
 
 // queries
 /**
  * Returns true if this game is over
  * @return Returns true if this game is over
  */
 public boolean isGameOver(){
  return gameOver; 
 }
 
 /**
  * Returns winner of this game. Can be either Winner.COMPUTER || Winner.USER
  * @returns Returns winner of this game
  */
 public Winner getWinner(){
	 return winner;
 }
 
 
 /**
  * @return Returns list of strings that end on user turn (words with length of 5,7,9,..), but 
  * also words' symbols with position 'theWord.length()' do not supposed to contain
  * any symbols that will end game. (So returns all words which are completed on user's
  * turn, but which cannot make computer lose on computer's next turn - this is strategy of 
  * computer to win)  
  */
 private ArrayList<String> getWordsEndingOnUserTurn(ArrayList<String> possibleWords){
   ArrayList<String> list = new ArrayList<String>();
   // adds to list all possible words to continue with, but which ends on
   // user's turn
   for(String everyWord: possibleWords)
    if(everyWord.length() > theWord.length() && everyWord.length() % 2 != 0) 
      list.add(everyWord);
   // remove words which have symbols which completes any word from dictionary
   // on next turn
   removeRestrictedSymbolsFrom(list);  
   return list;
 }
 
 /**
  * @return Chooses and returns correct bucket of all words which 
  * start with getWord().charAt(0) symbol. (All words from dictionary are
  * contained in 25 buckets of 'words' list)  
  */
 private ArrayList<String> getProperList(){
   int i;
   for(i=0;i<26;i++)
     if(alphabet.get(i).equals(theWord.toString().charAt(0)+""))
       break;
   return words.get(i);   
 }
 
 /**
  * @return Returns all words which have getWord() as a substring, and which
  * have length >= getWord().length() + 1 
  * @require theWord.length() > 0 
  * @ensure returns list of words where every word.length() >= getWord().length() + 1 
  *         && getWord().indexOf(word) == 0
  */
 public ArrayList<String> getPossibleWords(){
   ArrayList<String> list = new ArrayList<String>();
   ArrayList<String> tempWords = getProperList(); // gets right list
   //
   for(String everyWord: tempWords)
     if(everyWord.length()-1 >= theWord.length() && everyWord.indexOf(theWord.toString()) == 0)
       list.add(everyWord);
   return list;
 }
 
 
 /**
  * Returns true if computer added the symbol to the getWord(), which did not cause
  * the game to be over.
  * @return true if isGameOver(), and returns false otherwise
  */
 private boolean kompPohoduvOk(){
   ArrayList<String> possibleWords = getPossibleWords(); 
   if(possibleWords.size() > 0 && !checkIfWordIsCompleted(theWord.toString()))
     return true;
   else return false;
 }
 
 /**
  * @return Returns true if for every word from 'words' list, this condition holds:
  *   word.indexOf(getWord()) !=0 && word.length()==getWord.length())
  */
 private boolean checkIfWordIsCompleted(String word){
   ArrayList<String> rightList = getProperList();
   if(word.length() >= 4){
     for(String everyWord: rightList)
       if(everyWord.indexOf(word) == 0 && everyWord.length()==word.length())
          return true;
   }
   return false;
 }
 
 /**
  * Returns current word we are playing with (we build this word on every turn by
  * adding 1 symbol at a time by user and computer)
  * @returns Current word we are playing with
  */
 public String getWord(){
  return theWord.toString(); 
 }
 
 // methods
 /**
  * Method accepts symbol from a user, adds it to theWord. Then the game checks if 
  * this game is over and if yes, sets the winner. Otherwise, the game computes and adds 
  * computer's symbol to theWord, checks if game is over, and if yes, sets a winner.
  * @require letter equals any symbol from "a",...,"z" list && !gameIsOver()
  * @ensure  (getWord().charAt(getWord().length()-1)+"").equals(letter) && 
  * 		     getWinner == Winner.USER && isGameOver() == true &&
  *              old.getWord.length() + 1 == this.getWord().length()
  * 		 ||
  *  		 (getWord().charAt(getWord().length()-2)+"").equals(letter) &&
  *              getWinner == Winner.COMPUTER && isGameOver() == true &&
  *              old.getWord.length() + 2 == this.getWord().length()
  *          || 
  *          (getWord().charAt(getWord().length()-2)+"").equals(letter) &&
  *          isGameOver() == false
  * 		 old.getWord.length() + 2 == this.getWord().length()
  */
 public void acceptUserLetter(String letter){
   if(!isGameOver()){
     theWord.append(letter); // add user's letter to theWord
     ArrayList<String> possibleWords = getPossibleWords(); // all words valid to continue with
     // if user's symbol does not end game
     if(possibleWords.size() > 0 && !checkIfWordIsCompleted(theWord.toString())){  
         ArrayList<String> wordsEndingOnUserTurn = getWordsEndingOnUserTurn(possibleWords);  
         if(wordsEndingOnUserTurn.size() > 0) 
           addLetterFromTheList(wordsEndingOnUserTurn); // adds letter from words ending on user turn 
         else 
           addLetterFromTheLongestWord(possibleWords); // adds letter from longest non-restricted word
         checkComputersWord(); // checks if comps symbol does not end game 
     }
     else setWinner(Winner.COMPUTER);
   }
 }
 
 /**
  * Chooses any random word from the 'words' list, which has length 5,7,9,..
  * and does not finish game. If such word exists, then adds symbol to theWord or sets winner  
  * @require isGameOver()==false && wordsEndingOnUserTurn!= null
  * @ensure old.getWord().length() +1 == this.getWord().length()
  */
 private void addLetterFromTheList(ArrayList<String> wordsEndingOnUserTurn){
  Collections.shuffle(wordsEndingOnUserTurn);  // make words randomly sorted
  removeRestrictedSymbolsFrom(wordsEndingOnUserTurn); // removes words which can finish game
  if(wordsEndingOnUserTurn.size()>0)
   theWord.append(wordsEndingOnUserTurn.get(0).charAt(theWord.toString().length()));
  else setWinner(Winner.USER);
 }
 
 /**
  * Removes all words, which can end the game, from 'list'. 
  * @require list != null
  * @ensure Removes every word from 'list', where this word has someSymbol at getWord().length() position, and
  *         words.contain(getWord()+someSymbol)
  */
 private void removeRestrictedSymbolsFrom(ArrayList<String> list){
   ArrayList<String> tempList = getWordsEndingWith1MoreSymbol(); 
   HashSet<String> restrictedSymbols = new HashSet<String>();
   // add restricted symbols to set (symbols computer can't use not to finish game)
   for(String word: tempList)
     restrictedSymbols.add(word.charAt(word.length()-1)+"");
   Iterator<String> it = list.iterator();
   // remove words which have restricted symbols from list
   while(it.hasNext()){
    String word = it.next();
    if(restrictedSymbols.contains(word.charAt(theWord.length())+""))
      it.remove();
   }
 }
 
 /**
  * @return Returns list of all words, where every word satisfies properties:
  *         word.length == getWord.length()+1 && word.indexOf(getWord()) == 0
  */
 private ArrayList<String> getWordsEndingWith1MoreSymbol(){
   ArrayList<String> list = new ArrayList<String>();
   ArrayList<String> tempWords = getProperList(); // gets right bucket
   //
   for(String everyWord: tempWords)
     if(everyWord.length() == theWord.length()+1)
       list.add(everyWord);
   return list;
 }
 
 // tipa dobavlyae letter z longest non-restricted word
 // jakscho nema takuh, to sets winner usera
 /**
  * Computes and adds symbol to theWord from the longest possible word, or sets 
  * user as a winner if such word is not found
  * @require possibleWords!= null
  * @ensure  if possibleWord.size()>0 
  *             then old.getWord().length()+1 == this.getWord().length()
  *          else isGameOver() == true && getWinner == Winner.USER
  */
 private void addLetterFromTheLongestWord(ArrayList<String> possibleWords){
  // shukaemo najdovshe slovo, i dobavlyaemo jogo
   java.util.Comparator<String> comp = new java.util.Comparator<String>(){
     public int compare(String t1,String t2){
       if(t1.length() < t2.length())
         return -1;
       else if(t1.length() == t2.length())
         return 0;
       else return 1;
     } 
   };
  Collections.sort(possibleWords, comp); 
  if(possibleWords.size()>0)
   theWord.append(possibleWords.get(possibleWords.size()-1).charAt(theWord.toString().length()));
  else setWinner(Winner.USER);
 }
 
 /**
  * Sets winner of the game
  * @require winner != null
  * @ensure isGameOver() == true && getWinner() == winner
  */
 private void setWinner(Winner winner){
     gameOver = true;
     this.winner = winner;
 }
 
 // if comp pohoduv ne ok, to vstanovutu winnera
 /**
  * If computer's symbol finished the game, then user is set as a winner
  * @ensure kompPohoduvOk()==false, then getWinner() == Winner.USER
  */
 private void checkComputersWord(){
   if(!kompPohoduvOk())
           setWinner(Winner.USER);
 }
 
 // implementation for serializable interface
 /**
  * Writes object to the file
  * @param out
  * @throws IOException
  */
 private void writeObject(java.io.ObjectOutputStream out)
	     throws IOException{
	 out.defaultWriteObject();
 }
 
 /**
  * Reads object from the file
  * @param in
  * @throws IOException
  * @throws ClassNotFoundException
  */
 private void readObject(java.io.ObjectInputStream in)
	     throws IOException, ClassNotFoundException{
	 in.defaultReadObject();
 }
/**
 * @throws ObjectStreamException
 */
 private void readObjectNoData() 
	     throws ObjectStreamException{
 }
}
