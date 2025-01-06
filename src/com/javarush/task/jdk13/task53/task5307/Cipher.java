package com.javarush.task.jdk13.task53.task5307;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Cipher {
    private Alphabet alphabet = Alphabet.getInstance();
    private static FileManager fm = new FileManager();

    public void encryptFile(int shiftKey, String src, String dst) throws IOException {
        fm.checkExistFile(src);
        BufferedReader bufferedReader = fm.readFileToBuffer(src);
        BufferedWriter bufferedWriter = fm.writeBufferToFile(dst);
        while (bufferedReader.ready()) {
            char inputChar = (char) bufferedReader.read();
            char outputChar = encrypt(inputChar, shiftKey);
            bufferedWriter.write(outputChar);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }

    public void decryptFile(int shiftKey, String src, String dst) throws IOException {
        BufferedReader bufferedReader = fm.readFileToBuffer(src);
        BufferedWriter bufferedWriter = fm.writeBufferToFile(dst);
        while (bufferedReader.ready()) {
            char input = (char) bufferedReader.read();
            char output = decrypt(input, shiftKey);
            bufferedWriter.write(output);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }

    public Character decrypt(Character symbol, int shiftKey) {
        if (alphabet.containsChar(symbol)) {
            int index = (alphabet.getIndexOfChar(symbol) + Alphabet.length - shiftKey) % Alphabet.length;
            return alphabet.getCharOfIndex(index);
        } else {
            return symbol;
        }
    }

    private Character encrypt(Character symbol, int shiftKey) {
        if (alphabet.containsChar(symbol)) {
            int index = (alphabet.getIndexOfChar(symbol) + shiftKey) % Alphabet.length;
            return alphabet.getCharOfIndex(index);
        } else {
            return symbol;
        }
    }
}
