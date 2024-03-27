import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            ArrayList<String> fileData = readFile("src/input.txt");

            System.out.println("Количество слов в файле: " + fileData.size());

            System.out.println("Самое длинное слово: " + getLongestWord(fileData));

            Map<String, Integer> wordFrequency = getWordCount(fileData);

            System.out.println("Частота слов в порядке возрастания:");
            for (String word : wordFrequency.keySet()) {
                System.out.println(word + ": " + wordFrequency.get(word));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл не найден!");
        }
    }


    public static ArrayList<String> readFile(String path)
        throws IOException {
            String file = path;
            Scanner scanner = new Scanner(new File(file));
            ArrayList<String> words = new ArrayList<>();

            while (scanner.hasNext()) {
                words.add(scanner.next());
            }

            scanner.close();

            return words;
        }

    public static String getLongestWord(ArrayList<String> list) {
        String longest = "";
        for (String word : list) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }

        return longest;
    }

    public static Map<String,Integer> getWordCount(ArrayList<String> list) {
        HashMap<String, Integer> wordFrequency = new HashMap<>();

        for (String word : list) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        Map<String,Integer> sortedWords = wordFrequency.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));


        return sortedWords;
    }
}
