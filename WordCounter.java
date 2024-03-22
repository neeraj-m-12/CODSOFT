import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Word Counter!");
        System.out.println("Please enter '1' to input text or '2' to provide a file:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String text = "";
        switch (choice) {
            case 1:
                System.out.println("Enter your text:");
                text = scanner.nextLine();
                break;
            case 2:
                System.out.println("Enter the path to the file:");
                String filePath = scanner.nextLine();
                try {
                    text = readFile(filePath);
                } catch (IOException e) {
                    System.err.println("Error reading file: " + e.getMessage());
                    return;
                }
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
                return;
        }

        String[] words = text.split("\\s+|\\p{Punct}+");
        int totalWords = words.length;

        // Ignoring common words or stop words (for demonstration purposes, you can add more)
        String[] stopWords = {"the", "and", "or", "is", "are", "in", "on", "of"};
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            if (!isStopWord(word, stopWords)) {
                wordFrequency.put(word.toLowerCase(), wordFrequency.getOrDefault(word.toLowerCase(), 0) + 1);
            }
        }

        System.out.println("Total number of words: " + totalWords);
        System.out.println("Number of unique words: " + wordFrequency.size());
        System.out.println("Word Frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    private static boolean isStopWord(String word, String[] stopWords) {
        for (String stopWord : stopWords) {
            if (word.equalsIgnoreCase(stopWord)) {
                return true;
            }
        }
        return false;
    }
}