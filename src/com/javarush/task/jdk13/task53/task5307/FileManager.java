package com.javarush.task.jdk13.task53.task5307;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileManager {
    private final String FILE_NOT_FOUND = "Файл %s не сущействует \n";

    public BufferedReader readFileToBuffer(String src) throws IOException {
        if (isFile(src) && isExistFile(src)) {
            return Files.newBufferedReader(Path.of(src));
        } else {
            throw new IOException();
        }
    }

    public BufferedWriter writeBufferToFile(String dst) {
        Path path = Path.of(dst);
        if (isFile(dst)) {
            if (!isExistFile(dst)) {
                createFile(path);
            }
            return newBufferedWriter(path);
        }
        return newBufferedWriter(path);
    }

    private BufferedWriter newBufferedWriter(Path path) {
        try {
            return Files.newBufferedWriter(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isExistFile(String fileDirection) {
        File file = new File(fileDirection);
        if (!file.exists()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isFile(String fileDirection) {
        File file = new File(fileDirection);

        return !file.isDirectory();
    }

    private void createFile(Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkExistFile(String source) throws IOException {
        while (!Files.exists(Path.of(source))) {
            System.out.printf(FILE_NOT_FOUND, source);
            var paths = Path.of(source).toFile().getAbsolutePath();

            System.out.printf("Разместите файл %s в директории %s и нажмите Enter \n ", source, paths);
            new String(new Scanner(System.in).nextLine());
        }
    }

}
