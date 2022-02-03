package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String parseFile(Predicate<Character> filter) {
        StringBuilder result = new StringBuilder();
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test((char) data)) {
                    result.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
