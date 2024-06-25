
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Hangman 
{
    static String word = "unix gnu bruh";
    static Set<Character> guessedLetters = new HashSet<>();
    static String game = new String(); 
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        char[] input;
        while (true)
        {
            constructGame();
            System.out.println(game);

            // winning conidtion is that no more _ are left in the game.
            if ( ! ( game.contains( "_" ) ) )
            {
                System.out.println("you won!");
                break;
            }
            
            System.out.print("\nenter your strings: ");
            input = scanner.nextLine().toCharArray();
            for (char c : input) 
            {   
                // basically if c doesnt have an index in word it returns -1. if its not -1 than it was there somewhere
                if ( ! ( word.indexOf(c) == -1 ) )
                {
                    guessedLetters.add(c);
                }
            }
        }
    }

    static void constructGame()
    {
        game = "";
        for (int i = 0; i < word.length(); i++)
        {
            if ( word.charAt( i ) == ' ' )
            {
                game = game + ' ';
            }
            else if ( guessedLetters.contains( word.charAt( i ) ))
            {
                game = game + word.charAt(i);
            }
            else
            {
                game = game + '_';
            }
        }
    }
}
