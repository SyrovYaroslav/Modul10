import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Task3 {
    private static final String ABSOLUTE_PATH = "C:\\java\\Modul10\\Modul10\\src\\main\\resources\\words.txt";

    public static void main(String[] args) {
        File file = new File(ABSOLUTE_PATH);
        chekIsFileExist(file);
        HashMap<String, Integer> wordsList = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String line = bufferedReader.readLine();
            while (line != null) {
                String corectLine = line.replaceAll("\\s+", " ");
                String[] lineArr = corectLine.split(" ");
                for (String word: lineArr) {
                    if (wordsList.containsKey(word)) {
                        int value = wordsList.get(word) + 1;
                        wordsList.put(word, value);
                    } else {
                        wordsList.put(word, 1);
                    }
                }
                line = bufferedReader.readLine();
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }

        for (Map.Entry<String, Integer> entry : wordsList.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

    private static void chekIsFileExist(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
