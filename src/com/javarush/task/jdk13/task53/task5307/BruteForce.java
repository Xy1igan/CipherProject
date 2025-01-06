package com.javarush.task.jdk13.task53.task5307;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class BruteForce {
    private static Set<String> mostPopularWords = new TreeSet<>();
    private static final String sourceWords = "text_for_words.txt";
    private static FileManager fileManager = new FileManager();
    private Alphabet alphabet = Alphabet.getInstance();
    private Cipher cipher = new Cipher();

    private void createWordList() throws IOException {
        fileManager.checkExistFile(sourceWords);
        BufferedReader bufferedReader = fileManager.readFileToBuffer(sourceWords);
        ArrayList<Character> listCharactersOfNewWorld = new ArrayList<>();
        while (bufferedReader.ready()) {
            char currentChar = (char) bufferedReader.read();
            extractCharToWord(currentChar, listCharactersOfNewWorld);
        }
        bufferedReader.close();
        printCountWords(mostPopularWords);
    }

    private void extractCharToWord(char currentChar, ArrayList<Character> listCharactersOfNewWorld) {
        if (alphabet.isLetter(currentChar)) {
            listCharactersOfNewWorld.add(currentChar);
        } else {
            if (listCharactersOfNewWorld.size() > 2) {
                mostPopularWords.add(toString(listCharactersOfNewWorld));
            }
            listCharactersOfNewWorld.clear();
        }
    }

    private void printCountWords(Set<String> list) {
        System.out.printf("В словарь добавлено %s элементов \n", list.size());
    }

    private String toString(ArrayList<Character> newWorld) {
        StringBuilder result = new StringBuilder("");
        for (Character object : newWorld) {
            result.append(object);
        }
        return result.toString().toLowerCase();
    }

    public void bruteForce(String sourceFile) throws IOException {
        createWordList();
        fileManager.checkExistFile(sourceFile);
        Map<Integer, Integer> result = new HashMap<>();

        for (int shift = 0; shift < Alphabet.length; shift++) {
            int countWordsFound = findWords(sourceFile, shift);
            if (countWordsFound > 0) {
                result.put(shift, countWordsFound);
            }
        }
        printBruteForceResult(result);
    }

    private int findWords(String sourceFile, int shift) throws IOException {
        int result = 0;
        ArrayList<Character> newWord = new ArrayList<>();
        BufferedReader bufferedReader = fileManager.readFileToBuffer(sourceFile);
        while (bufferedReader.ready()) {
            char inputChar = (char) bufferedReader.read();
            char outputChar = cipher.decrypt(inputChar, shift);
            if (alphabet.isLetter(outputChar)) {
                newWord.add(outputChar);
            } else {
                result = checkNewWord(newWord, result);
                newWord.clear();
            }
            if (!bufferedReader.ready()) {
                result = checkNewWord(newWord, result);
            }
        }
        bufferedReader.close();
        return result;
    }

    private int checkNewWord(ArrayList<Character> newWord, int result) {
        String word = toString(newWord).toLowerCase();
        if (mostPopularWords.contains(word)) {
            result++;
        }
        return result;
    }

    private static void printBruteForceResult(Map<Integer, Integer> mapResult) {
        if (!mapResult.isEmpty()) {
            SortedMap<Integer, List<Integer>> sortedResult = sortResult(mapResult);
            for (Map.Entry entry : sortedResult.entrySet()) {
                var value = entry.getValue();
                var key = entry.getKey();
                System.out.printf("По ключу(-ам) %s найдено %s совпадающих слов \n", value, key);
            }

        }
    }

    private static SortedMap<Integer, List<Integer>> sortResult(Map<Integer, Integer> map){
        SortedMap<Integer, List<Integer>> sortedResult = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry entry : map.entrySet()) {
            Integer value = (Integer) entry.getValue();
            Integer key = (Integer) entry.getKey();
            List<Integer> newList = new ArrayList<>();
            if (sortedResult.isEmpty() || !sortedResult.containsKey(value)) {
                newList.add(key);
            } else{
                newList = sortedResult.get(value);
                newList.add(key);
            }
            sortedResult.put(value, newList);
        }
        return sortedResult;
    }
}
