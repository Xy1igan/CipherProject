package com.javarush.task.jdk13.task53.task5307;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Cipher {
    private Alphabet alphabet = Alphabet.getInstance();
    private static int shift = Integer.MIN_VALUE;
    private static String key = null;
    private static FileManager fm = new FileManager();

    public String encrypt(String message, String newKey) {
        setKey(newKey);
        char[] resultChar = new char[message.length()];     //результирующий массив
        char[] arrayMessage = message.toCharArray();        //массив символов из сообщения

        for (int i = 0; i < arrayMessage.length; i++) {           //процесс кодирования
            resultChar[i] = encrypt(arrayMessage[i], key);
        }
        return String.copyValueOf(resultChar);
    }

    public Character encrypt(Character symbol, String newKey) {    // кодирование посимвольно
        setKey(newKey);
        if (alphabet.containsChar(symbol)) {
            int index = (alphabet.getIndexOfChar(symbol) + shift) % Alphabet.length;
            return alphabet.getCharOfIndex(index);
        } else {
            return symbol;
        }
    }

    public void encryptFile(String newKey, String src, String dst) throws IOException {
        setKey(newKey);
        try (BufferedReader bufferedReader = fm.readFileToBuffer(src);
             BufferedWriter bufferedWriter = fm.writeBufferToFile(dst);) {
            while (bufferedReader.ready()) {
                char inputChar = (char) bufferedReader.read();
                char outputChar = encrypt(inputChar, key);
                bufferedWriter.write(outputChar);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void decryptFile(String newKey, String src, String dst) {
        setKey(newKey);
        try (BufferedReader bufferedReader = fm.readFileToBuffer(src);
             BufferedWriter bufferedWriter = fm.writeBufferToFile(dst);) {
            while (bufferedReader.ready()) {
                char input = (char) bufferedReader.read();
                char output = decrypt(input, key);
                bufferedWriter.write(output);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Character decrypt(Character symbol, String newKey) {
        setKey(newKey);
        if (alphabet.containsChar(symbol)) {
            int index = (alphabet.getIndexOfChar(symbol) + Alphabet.length - shift) % Alphabet.length;
            return alphabet.getCharOfIndex(index);
        } else {
            return symbol;
        }
    }

    public String decrypt(String message, String newKey) {
        setKey(newKey);
        char[] resultChar = new char[message.length()];     //результирующий массив
        char[] arrayMessage = message.toCharArray();        //массив символов из сообщения

        for (int i = 0; i < arrayMessage.length; i++) {           //процесс кодирования
            resultChar[i] = decrypt(arrayMessage[i], key);
        }
        return String.copyValueOf(resultChar);
    }

    private void setKey(String newKey) {
//        System.out.println("*****Шифрование словом " + newKey);
        setShift(newKey);
        key = newKey;
    }

    private void setShift(String newKey) {
        if (key == null) {
            this.shift = newKey.hashCode() % Alphabet.length;
//            System.out.println("***** Шифрование к ключем " + shift);
        } else if (shift == Integer.MIN_VALUE || key.hashCode() == newKey.hashCode()) {
            this.shift = newKey.hashCode() % Alphabet.length;
//            System.out.println("***** Шифрование к ключем " + shift);
        }
    }
}
