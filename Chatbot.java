import java.io.IOException;
import java.util.Scanner;

public class Chatbot {
    public static void main(String[] args) {
        MyBot myBot = new MyBot();

        Scanner scanner = new Scanner(System.in);

        System.out.println(" <<---------------------------(:: Chatbot ::)--------------------------->> ");
        System.out.println("Chatbot : Hello! To train the chatbot, type ' train chatbot ' ");
        System.out.println("If you wish to end the conversation, type ' exit '");

        while (true) {
            System.out.print("You : ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Chatbot : Goodbye!");
                System.out.println(" <<---------------------------(:: Chatbot ::)--------------------------->> ");
                break;
            } else if (userInput.equalsIgnoreCase("train chatbot")) {
                System.out.print("Enter the question: ");
                String trainQuestion = scanner.nextLine();

                System.out.print("Enter the answer: ");
                String trainAnswer = scanner.nextLine();

                try {
                    myBot.trainMe(trainQuestion, trainAnswer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                myBot.answer1(userInput);
            }
        }
        scanner.close();
    }
}