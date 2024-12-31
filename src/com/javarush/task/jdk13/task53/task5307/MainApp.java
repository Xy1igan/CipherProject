package com.javarush.task.jdk13.task53.task5307;

import java.io.IOException;

public class MainApp {
    static Cipher cipher = new Cipher();

    public static void main(String[] args) throws IOException {

        cipher.encryptFile("ключ", "data.txt", "output.txt");
        cipher.decryptFile("люч", "output.txt", "result.txt");
    }
}
