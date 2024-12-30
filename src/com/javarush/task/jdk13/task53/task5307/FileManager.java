package com.javarush.task.jdk13.task53.task5307;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    private final String FILE_NOT_FOUND = "Файл %s не сущействует \n";
    private final String FILE_EXIST = "Файл %s найден \n";
    private final String FILE_READING = "Чтение из файла %s \n";



    public BufferedReader readFileToBuffer(String src) throws IOException {
        if (isFile(src) && isExistFile(src)) {
            System.out.printf(FILE_READING, src);
            return Files.newBufferedReader(Path.of(src));
        } else throw new IOException(FILE_NOT_FOUND);
    }

    public BufferedWriter writeBufferToFile(String dst) throws IOException {
        Path path = Path.of(dst);
        if (isFile(dst)) {
            if (!isExistFile(dst)) {
                System.out.println("Попытка создания файла " + dst);
                createFile(path);
            }
            return newBufferedWriter(path);
        } return newBufferedWriter(path);
    }

    private BufferedWriter newBufferedWriter(Path path){
        try {
            return Files.newBufferedWriter(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//    public ArrayList<Character> readAllCharacters(String src) throws IOException {
//        ArrayList<Character> arrayListCharacter = new ArrayList<>();
//        char[] resultCharArray = new char[]
//        Collections.addAll(arrayListCharacter, Files.readAllBytes(Path.of(src)));
//        return arrayListCharacter.addAll();
//    }

    private boolean isExistFile(String fileDirection) {
        File file = new File(fileDirection);
        System.out.printf("Запущена проверка наличия файла %s \n", fileDirection);
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
        System.out.printf("Проверка на файл %s пройдена \n", fileDirection);

        return !file.isDirectory();
    }

    private void createFile(Path path){
        try {
            Files.createFile(path);
            System.out.printf("Файл s% создан \n", path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
