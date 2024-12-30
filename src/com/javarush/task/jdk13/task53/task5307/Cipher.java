package com.javarush.task.jdk13.task53.task5307;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Cipher {
    private Alphabet alphabet = Alphabet.getInstance();
    private static int shift;

//    public String encrypt(String message, String key) {
//        char[] resultChar = new char[message.length()];     //результирующий массив
//        char[] arrayMessage = message.toCharArray();        //массив символов из сообщения
//
//        for (int i = 0; i < arrayMessage.length; i++) {           //процесс кодирования
//            resultChar[i] = encrypt(arrayMessage[i], key);
//        }
//        return String.copyValueOf(resultChar);
//    }

    public Character encrypt(Character symbol, String key) {    // кодирование посимвольно
        setShift(key);
        if (alphabet.containsChar(symbol)) {
            int index = (alphabet.getIndexOfChar(symbol) + shift) % Alphabet.length;
            return alphabet.getCharOfIndex(index);
        } else {
            return symbol;
        }
    }

    public void encryptFile(String key, String src, String dst) throws IOException {
        FileManager fm = new FileManager();
        BufferedReader bufferedReader = fm.readFileToBuffer(src);
        BufferedWriter bufferedWriter = fm.writeBufferToFile(dst);
        try {
            while (bufferedReader.ready()) {
                char inputChar = (char) bufferedReader.read();
                char outputChar = encrypt(inputChar, key);
                bufferedWriter.write(outputChar);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            bufferedReader.close();
            bufferedWriter.flush();
            bufferedWriter.close();
        }
    }

    public void decryptFile(String key, String src, String dst){
        FileManager fm = new FileManager();
        try(BufferedReader bufferedReader = fm.readFileToBuffer(src);
            BufferedWriter bufferedWriter = fm.writeBufferToFile(dst);)
        {
            while (bufferedReader.ready()){
                char input = (char) bufferedReader.read();
                char output = decrypt(input, key);
                bufferedWriter.write(output);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        bufferedWriter.flush();
//        bufferedWriter.close();
//        bufferedReader.close();
    }

    public Character decrypt(Character symbol, String key){
        setShift(key);
        if (alphabet.containsChar(symbol)) {
            int index = (alphabet.getIndexOfChar(symbol) + Alphabet.length - shift) % Alphabet.length;
            return alphabet.getCharOfIndex(index);
        } else {
            return symbol;
        }
    }

    public String decrypt(String message, String key) {
        char[] resultChar = new char[message.length()];     //результирующий массив
        char[] arrayMessage = message.toCharArray();        //массив символов из сообщения

        for (int i = 0; i < arrayMessage.length; i++) {           //процесс кодирования
            resultChar[i] = decrypt(arrayMessage[i], key);
        }
        return String.copyValueOf(resultChar);
    }

    private void setShift(String key){
        this.shift = key.hashCode() % Alphabet.length;
    }
}
