package com.javarush.task.jdk13.task53.task5307;

import java.io.IOException;

public class MainApp {
    static Cipher cipher = new Cipher();
    private static final String SELECT_MENU = "Выберите опцию (цифру): \n";
    private static final String MAIN_MENU = "1. Зашифровать текст \n" +
            "2. Расшифровать текст \n" +
            "3. Попробовать взломать текст (bruteforce)\n";
    private static final String MENU_SELECT_SOURCE = "Выберите источник текста";
    private static final String MENU_KEY = "Введите ключ шифра";

    public static void main(String[] args) throws IOException {
//        System.out.println(SELECT_MENU);

//        cipher.encryptFile("ключ", "data.txt", "output.txt");
//        cipher.decryptFile("ключ", "output.txt", "result.txt");

        BruteForce bruteForce = new BruteForce();
        bruteForce.createWordList();

    }


}
