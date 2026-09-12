import java.util.Scanner;

public class sentence {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();

        String[] words = sentence.split(" ");

        StringBuilder result = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {

            result.append(words[i]);

            if (i != 0) {
                result.append(" ");
            }
        }

        System.out.println("Reversed Sentence: " + result);
    }
}