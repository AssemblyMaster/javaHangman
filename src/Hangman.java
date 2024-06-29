
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.LinkedList;

public class Hangman 
{
    static String word = "missingno";
    static String hint = "figureitout";
    static Set<Character> guessedLetters = new HashSet<>();
    static String game = new String();
    static LinkedList<String> games = new LinkedList<>(); 
    public static void main(String[] args)
    {
        int lives = 7;
        Scanner scanner = new Scanner(System.in);
        char[] input;
        boolean blockHit = false;
        int maxSize = 1;
        LinkedList<Character> duplicateLetters = new LinkedList<>();

        for (int i = 0; i < args.length; i++)
        {
            if ( args[i].equals("--word") || args[i].equals("-w") )
            {
                try
                {
                    word = args[ i + 1 ];
                }
                catch ( ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("pleases input a word");
                    System.exit(1);
                }

            }
            if ( args[i].equals("--hint") || args[i].equals("-h") )
            {
                try
                {
                    hint = args[ i + 1 ];
                }
                catch ( ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("pleases input a hint");
                    System.exit(1);
                }
            }
            if ( args[i].equals("--lives") || args[i].equals("-l") )
            {
                try
                {
                    lives = Integer.parseInt( args[ i + 1 ] ); 
                }
                catch ( NumberFormatException e )
                {
                    System.out.println("not a valid number of lives");
                    System.exit(1);
                } 
                catch ( ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("pleases input a number");
                    System.exit(1);
                }
                if ( 0 >= lives )
                {
                    System.out.println("lives cannot be negative or zero");
                    System.exit(1);
                }
            }
            if ( args[i].equals("--size") || args[i].equals("-s"))
            {
                try 
                {
                    maxSize = Integer.parseInt( args[ i + 1 ] );
                }
                catch ( NumberFormatException e )
                {
                    System.out.println("not a valid size");
                    System.exit(1);
                } 
                catch ( ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("pleases input a number");
                    System.exit(1);
                }
                if ( 0 >= maxSize)
                {
                    System.out.println("size cannot be negative or zero");
                    System.exit(1);
                }

            }
        } 

        System.out.println("your hint is: " + hint);
        while ( ! (lives <= 0 ) )
        {
            constructGame();
            System.out.println("\nyou have " + lives + " lives left" );
            System.out.println(game);

            
            // if ( howMany_inLatestGame(true) == howMany_inLatestGame(false) && ! isFirstMistake )
            // {
            //     lives--;
            // }


            if ( ! ( game.contains( "_" ) ) )
            {
                System.out.println("you won!");
                System.exit(0);
            }
            
            while (true)
            {   
                duplicateLetters.clear();
                System.out.print("\nenter your strings: ");
                input = scanner.nextLine().toCharArray();

                if ( input.length > maxSize)
                {
                    System.out.println("input is too big. try a smaller string");
                    continue;
                }
                // for (char ch : input)
                // {
                //     if ( guessedLetters.contains(ch) )
                //     {
                //         duplicateLetters.add(ch);
                //     }
                // }
                // if ( ! guessedLetters.isEmpty() )
                // {
                //     System.out.println("you already guessed some of the letters in that string. ignoring them");
                //     input.
                // }
                break;
            }

            for (char c : input) 
            {   
                // basically if c doesnt have an index in word it returns -1. if its not -1 than it was there somewhere
                if ( ! ( word.indexOf(c) == -1 ) )
                {
                    blockHit = true;
                    guessedLetters.add(c);
                }
            }
            if ( ! blockHit )
            {
                lives--;
            }
            blockHit = false;

        }
        System.out.println("you lost");
        scanner.close();
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
        games.add(game);
    }
    
    static int howMany_inLatestGame(boolean indexOne)
    {
        int count = 0;
        // true = last node, false = secondlast node
        char[] chars = indexOne? games.getLast().toCharArray() : games.get(games.size() - 2).toCharArray();
        for (char ch : chars)
        {
            if ( ch == '_' )
            {
                count++;
            }
        }
        return count;
    }
}
