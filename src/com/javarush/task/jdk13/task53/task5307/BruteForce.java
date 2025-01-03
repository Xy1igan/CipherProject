package com.javarush.task.jdk13.task53.task5307;

import java.io.*;
import java.util.*;

public class BruteForce {
    private static Set<String> mostPopularWords = new TreeSet<>();
    private static final String sourceWords = "text_for_words.txt";
    private static FileManager fileManager = new FileManager();
    private Alphabet alphabet = Alphabet.getInstance();

    public void createWordList() throws IOException {
        BufferedReader bufferedReader = fileManager.readFileToBuffer(sourceWords);
        char currentChar;
        ArrayList<Character> listCharactersOfNewWorld = new ArrayList<>();
        while (bufferedReader.ready()) {
            currentChar = (char) bufferedReader.read();
            if (alphabet.isLetter(currentChar)) {
                listCharactersOfNewWorld.add(currentChar);
            } else {
                if (listCharactersOfNewWorld.size() > 2) {
                    mostPopularWords.add(convertListToString(listCharactersOfNewWorld));
                }
                listCharactersOfNewWorld.clear();
            }
        }
        bufferedReader.close();
    }

    private static String convertListToString(ArrayList<Character> newWorld) {
        StringBuilder result  = new StringBuilder("");
        for (Character object: newWorld){
            result.append(object);
        }
//        System.out.println(result.toString().toLowerCase());
        return result.toString().toLowerCase();
    }
}
