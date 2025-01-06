package com.javarush.task.jdk13.task53.task5307;

import java.io.IOException;

public class MainApp {
    static Cipher cipher = new Cipher();
    static Menu menu = new Menu();
    static BruteForce brute = new BruteForce();

    public static void main(String[] args) throws IOException {
        switch (menu.mainMenu()) {
            case 1 -> cipher.encryptFile(menu.selectKeyMenu(), "data.txt", "encrypted.txt");
            case 2 -> cipher.decryptFile(menu.selectKeyMenu(), "encrypted.txt", "decrypted.txt");
            case 3 -> brute.bruteForce("encrypted.txt");
        }
    }
}
