package jangl3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IOUtils {
    public static String loadFile(String path) {
        StringBuilder builder;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load file: " + path);
        }

        return builder.toString();
    }
}
