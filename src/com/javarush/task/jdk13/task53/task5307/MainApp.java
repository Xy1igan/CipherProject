package com.javarush.task.jdk13.task53.task5307;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainApp {
    public static void main(String[] args) throws IOException {

//        String message = "тестовая строка для шифровки";
//        System.out.println(message);
//        Cipher cipher = new Cipher();
//        String encryptedMessage = cipher.encrypt(message, 1);
//        System.out.println(encryptedMessage);
//        String decryptedMessage = cipher.decrypt(encryptedMessage, 1);
//        System.out.println(decryptedMessage);
//        FileManager fm = new FileManager();
        Cipher cipher = new Cipher();
        cipher.encryptFile("ключ", "data.txt", "output.txt");
        cipher.decryptFile("ключ", "output.txt", "result.txt");
    }
}
