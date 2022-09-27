import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

public class Task2 {
    private static final String READ_PATH = "C:\\java\\Modul10\\Modul10\\src\\main\\resources\\file.txt";
    private static final String WRITE_PATH = "C:\\java\\Modul10\\Modul10\\src\\main\\resources\\user.json";
    private static ArrayList<User> userArray = new ArrayList<>();

    public static void main(String[] args) {
        File fileToRead = new File(READ_PATH);
        chekIsFileExist(fileToRead);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))){
            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();
            while (line != null) {
                String[] lineArr = line.split(" ");
                User user = new User(lineArr[0], lineArr[1]);
                userArray.add(user);
                line = bufferedReader.readLine();
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userArray);
        File fileToWrite = new File(WRITE_PATH);
        chekIsFileExist(fileToWrite);
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileToWrite))){
            bufferedOutputStream.write(json.getBytes());
        } catch (IOException e) {
            System.err.println("EXCEPTION !!! " + e.getMessage());
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
