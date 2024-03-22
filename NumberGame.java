import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int maxAttempts = 5; 
        int score = 0; 

        do {
            int randomNumber = random.nextInt(100) + 1; 

            System.out.println("Guess a number between 1 and 100 (You have " + maxAttempts + " attempts):");

            int userGuess;
            boolean guessCorrect = false;
            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
                userGuess = scanner.nextInt();

                if (userGuess == randomNumber) {
                    guessCorrect = true;
                    score += (maxAttempts - attempt + 1); 
                    System.out.println("Congratulations! You guessed the number in " + attempt + " attempts.");
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("Your guess is too low. Try again.");
                } else {
                    System.out.println("Your guess is too high. Try again.");
                }
            }

            if (!guessCorrect) {
                System.out.println("You ran out of guesses. The number was " + randomNumber);
            }

            // Ask if the user wants to play again
            System.out.println("Do you want to play again? (yes/no):");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }

            // Reset attempts and score for a new round
            maxAttempts = 5;
            score = 0;

        } while (true);

        System.out.println("Thank you for playing! Your final score is: " + score);
        scanner.close();
    }
}