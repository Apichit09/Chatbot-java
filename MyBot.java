import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Set;

public class MyBot {
    HashMap<String, String> knowledge = new HashMap<String, String>();
    static String question;
    int a = 1;

    public MyBot() {
        knowledge.put("Hi", "Hello... Pleased to meet you!");
        knowledge.put("Hello", "Hi yo");
        knowledge.put("how are you?", "Great! And you?");
        knowledge.put("what time is it", "Look at your watch!");

        try (BufferedReader br = new BufferedReader(new FileReader("Source.txt"))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] information = sCurrentLine.split("_");
                String k = information[0];
                String v = information[1];
                knowledge.put(k, v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void answer1(String message) {
        question = message;
        answer(question);
    }

    public void answer(String question) {
        Set<String> keys = knowledge.keySet();

        for (String key : keys) {
            String lowerKey = key.toLowerCase();
            String lowerQuestion = question.toLowerCase();

            if (lowerKey.equals(lowerQuestion)) {
                String str = knowledge.get(key);
                System.out.println("ChatBot : " + str);
                return;
            }
        }

        for (String key : keys) {
            String lowerKey = key.toLowerCase();
            String lowerQuestion = question.toLowerCase();

            if (lowerKey.contains(lowerQuestion)) {
                String str = knowledge.get(key);
                System.out.println("ChatBot : " + str);
                return;
            }
        }

        reply(question);
    }

    public void reply(String question) {
        System.out.println("ChatBot: Sorry, I'm dumb! How should I reply?");
        System.out.println("Type ' train chatbot ' and teach me.");
    }

    public void trainMe(String question, String ans) throws IOException {
        knowledge.put(question, ans);
        String s = "_";
        String content = question + s + ans;

        Path path = Paths.get("Source.txt");
        Files.write(path, content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        System.out.println("Thanks, I'll remember that");
    }
}
