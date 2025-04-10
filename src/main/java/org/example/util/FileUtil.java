package org.example.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static List<String> readFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Failed to read file: " + path);
            return new ArrayList<>();
        }
    }

    public static void writeFile(String path, List<String> lines) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to write file: " + path);
        }
    }
}
