import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    private static final String ABSOLUTE_PATH = "C:\\java\\Modul10\\Modul10\\src\\main\\resources\\file.txt";

    public static void main(String[] args) {
        File file = new File(ABSOLUTE_PATH);
        chekIsFileExist(file);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String line = bufferedReader.readLine();
            Pattern pattern1 = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");
            Pattern pattern2 = Pattern.compile("^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$");
            while (line != null) {
                Matcher matcher1 = pattern1.matcher(line);
                Matcher matcher2 = pattern2.matcher(line);
                boolean matchFound1 = matcher1.find();
                boolean matchFound2 = matcher2.find();
                if (matchFound1 || matchFound2) {
                    System.out.println(line);
                }
                line = bufferedReader.readLine();

            }
        }catch (IOException e){
            System.err.println(e.getMessage());
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
