package com.javarush.task.jdk13.task53.task5307;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeSet;

public class FileManager {
    private final String FILE_NOT_FOUND = "Файл %s не сущействует \n";
    private final String FILE_EXIST = "Файл %s найден \n";
    private final String FILE_READING = "Чтение из файла %s \n";
    private final String TRY_TO_CREATE_FILE = "Попытка создания файла %s \n";
    private final String CHECK_EXIST_FILE = "Запущена проверка наличия файла %s \n";
    private final String FILE_IS_CREATED = "Файл s% создан \n";


    public BufferedReader readFileToBuffer(String src) throws IOException {
        if (isFile(src) && isExistFile(src)) {
            System.out.printf(FILE_READING, src);
            return Files.newBufferedReader(Path.of(src));
        } else {
            System.out.printf(FILE_NOT_FOUND, src);
            throw new IOException();
        }
    }

    public BufferedWriter writeBufferToFile(String dst) {
        Path path = Path.of(dst);
        if (isFile(dst)) {
            if (!isExistFile(dst)) {
                System.out.printf(TRY_TO_CREATE_FILE, dst);
                createFile(path);
            }
            return newBufferedWriter(path);
        }
        return newBufferedWriter(path);
    }

    public char[] readAllCharacters(String src) throws IOException {
        String newString = new String(Files.readAllBytes(Path.of(src)));
        char[] result = newString.toCharArray();
        return result;
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
        System.out.printf(CHECK_EXIST_FILE, fileDirection);
        if (!file.exists()) {
            System.out.printf(FILE_NOT_FOUND, file);
            return false;
        } else {
            System.out.printf(FILE_EXIST, file);
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
            System.out.printf(FILE_IS_CREATED, path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
