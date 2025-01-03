package com.javarush.task.jdk13.task53.task5307;

import java.util.*;

public class Alphabet {
    private static ArrayList<Character> alphabet = new ArrayList<>();
    private static Map<Character, Integer> alphabetMap = new TreeMap<>();
    private static Alphabet INSTANCE = new Alphabet();
    public static int length = alphabet.size();
    private Set<Character> letters = new HashSet<>();

    private Alphabet() {
        addRussianABC();
        addEnglishABC();
        addSpecialCharacters();

        for (int j = 0; j < alphabet.size(); j++) {
            alphabetMap.put(alphabet.get(j), j);
        }
    }

    public boolean isLetter(Character charForCheck){
        return letters.contains(charForCheck);
    }

    private void addSpecialCharacters() {
//        alphabet.addAll(new ArrayList<>(Arrays.asList('.', ',', '-', ':', ';', '!')));
        for (char ch = '!'; ch <= '/'; ch++) {
//            System.out.println(ch + " (" + ((int) ch) + ")");
            alphabet.add(ch);
        }
        for (char ch = ':'; ch <= '@'; ch++) {
//            System.out.println(ch + " (" + ((int) ch) + ")");
            alphabet.add(ch);
        }
    }

    private void addRussianABC() {
        for (char ch = 'А'; ch <= 'я'; ch++) {
//            System.out.println(ch + " (" + ((int) ch) + ")");
            alphabet.add(ch);
            letters.add(ch);
        }
    }

    private void addEnglishABC() {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
//            System.out.println(ch + " (" + ((int) ch) + ")");
            alphabet.add(ch);
            letters.add(ch);
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
//            System.out.println(ch + " (" + ((int) ch) + ")");
            alphabet.add(ch);
            letters.add(ch);
        }
    }

    public static Alphabet getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Alphabet();
        }
        return INSTANCE;
    }

    public int getIndexOfChar(Character charOfAlphabet) {
        return alphabetMap.get(charOfAlphabet);
    }

    public Character getCharOfIndex(Integer index) {
        return alphabet.get(index);
    }

    public boolean containsChar(Character charOfMessage) {
        return alphabetMap.containsKey(charOfMessage);
    }

    public static void printAlphabet() {
        System.out.println("Alphabet ArrayList: " + alphabet.toString());
        System.out.println("Alphabet TreeMap: " + alphabetMap.keySet().toString());
    }

}
