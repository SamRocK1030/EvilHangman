/**
 * <p>A class that keeps track of the current state of a game of hangman.</p>
 * <p> The class is constructed with a secret word and some number of guesses.</p>
 * <p>Every time a letter is guessed, the state of the game is updated appropriately
 * integrating the guessed letter into the word, updating the number of guesses
 * remaining, etc.</p>
 * 
 * <p>This class can then be used by a user interface to administer a regular game of Hangman.</p>
 */
import java.util.*;
public class NormalHangMan extends HangmanGame
{
    private int lettersLeft;//to store the number of the letters in the secret word has not been guessed correctly

    /**
     * Constructor sets up the game to be played with a word and some number of
     * guesses.  The class should have private variables that keep track of:
     * <li>The original secret word
     * <li>The number of guesses remaining
     * <li>The number of letters that still need to be guessed
     * <li>The current state of word to be guessed (e.g. "L A B _ R A _ _ R Y")
     * @param secretWord the word that the player is trying to guess
     * @param numGuesses the number of guesses allowed
     */
    public NormalHangMan(String secretWord, int numGuesses, TreeSet<Character> LetterHistory){
        this.secretWord = secretWord;
        guessesRemaining = numGuesses;
        lettersLeft = secretWord.length();
        for(int i = 0; i < secretWord.length(); i++)
        {
            currentState += "_ ";
            for(int j = i; j > 0; j--)
            {
                if(secretWord.charAt(i) == secretWord.charAt(j-1))
                {
                    lettersLeft--;//If the letter appears many times in the secret word, it will be counted just once.
                    break;
                }
            }
        }
        letterGuessHistory = LetterHistory;
        
    }   

    @Override
	public int numLettersRemaining()
    {
        return lettersLeft;
    }
    
    @Override
	public boolean isWin()
    {
        if(guessesRemaining == 0)
            return false;//if the user have no chance to guess again, it means the user loses.
        else
            return true;
    }
    
    @Override
	public boolean gameOver()
    {
        if(guessesRemaining == 0 || lettersLeft == 0)
            return true;
        else
            return false;
    }
 
    @Override
	public boolean makeGuess(char ch)
    {
    	if (Character.isLetter(ch) == false) 
    		return false;
        
    	boolean currentGuessIsCorrect = true;
        letterGuess = ch;
        int i;
        for(i = 0; i < secretWord.length(); i++)
        {
            if(secretWord.charAt(i) == ch)//if the user guess right, adjust the current state.
            {
                String tempState = "";
                for(int j = 0; j < secretWord.length(); j++)
                {
                    if(secretWord.charAt(j) == ch)
                    {
                        tempState = tempState + ch + " ";
                    }
                    else
                    {
                        tempState = tempState + currentState.charAt(2*j) + currentState.charAt(2*j+1); 
                    }
                }
                currentState = tempState;
                currentGuessIsCorrect = true;
                break;
            }
            else
            {
                currentGuessIsCorrect = false;
            }
        }
        if(!RepeatInput(ch))
        {
            letterGuessHistory.add(letterGuess);

            if(currentGuessIsCorrect)
            {
                lettersLeft--;
            }
            else
            {
                guessesRemaining--;
            }
            return currentGuessIsCorrect;
        }
        else return false;
    }
}