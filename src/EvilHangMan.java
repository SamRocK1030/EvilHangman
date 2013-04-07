import java.util.*;
import java.io.*;


public class EvilHangMan extends HangmanGame {
	//private String[] Wordlist = new String[235000];// to store the dictionary
	private LinkedList<String> Wordlist = new LinkedList<String>();
	private int numWords = 0;// count the number of possible secret words.
	private int secretStringLength;// the length of the secret string
	private boolean GuessResult = false;

	public EvilHangMan(int StringLength, int numGuesses) {
		guessesRemaining = numGuesses;
		secretStringLength = StringLength;
		Scanner Scanner = null;
		try {
			Scanner = new Scanner(new File("dictionary.txt"));// read the dictionary
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		int i = 0;
		while (Scanner.hasNext()) {
			String temp = Scanner.nextLine().toUpperCase();
			if (temp.length() == StringLength) {
				Wordlist.add(temp);
				i++;
				numWords++;
			}
		}

		for (i = 0; i < StringLength; i++) {
			currentState += "_ ";
		}
		Scanner.close();
	}


	public int numLettersRemaining() {
		return 26; // because they never get one right!
	}

	public boolean isWin() {
		return false;
	}

	public boolean gameOver() {
		if (guessesRemaining == 0)
			return true;
		else
			return false;
	}


	public boolean makeGuess(char ch) {
		GuessResult = false;
		letterGuess = ch;
		if (Character.isLetter(ch) && !RepeatInput(ch)) {
			// adjust the Wordlist in order to avoid the word with the letter
			// user guessed
			int tempWordNum = 0;
			ListIterator<String> L = Wordlist.listIterator();
			while (L.hasNext()) {
				String s = L.next();
				if (s.indexOf(ch) != -1 && numWords > 1){
					L.remove();
					numWords--;
					
				}
			}
			
		/*	for (int i = 0; i < numWords; i++) {
				for (int j = 0; j < secretStringLength; j++) {
					if (Wordlist[i].charAt(j) == ch) {
						break;
					} else {
						if (j == secretStringLength - 1) {
							if (Wordlist[i].charAt(j) != ch) {
								tempWordNum++;
							}
						}
					}
				}
			}
			// we choose the words that don't contain the letter the user
			// guessed, and they will be the new possible secret words.
			String[] temp = new String[tempWordNum];
			int tempIndex = 0;
			for (int i = 0; i < numWords; i++) {
				for (int j = 0; j < secretStringLength; j++) {
					if (Wordlist[i].charAt(j) == ch) {
						break;
					} else {
						if (j == secretStringLength - 1) {
							if (Wordlist[i].charAt(j) != ch) {
								temp[tempIndex] = Wordlist[i];
								tempIndex++;
							}
						}
					}
				}
			}*/
			if (numWords == 1) {

				secretWord = Wordlist.getFirst();
				GuessResult = true;
			} else {
				secretWord = Wordlist.getFirst();
				//numWords = tempWordNum;
				//Wordlist = temp;
				guessesRemaining--;
				GuessResult = false;
			}
			if (!GuessResult) {
				letterGuessHistory.add(letterGuess);
			}

		} else return false;
		
		return GuessResult;
	}

}