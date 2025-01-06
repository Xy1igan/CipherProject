package com.javarush.task.jdk13.task53.task5307;

import java.util.Scanner;

public class Menu {
    public int mainMenu() {
        System.out.println("Для шифрования файла data.txt введите \'1\'");
        System.out.println("Для расшифровки текста из файла encrypted.txt введите \'2\'");
        System.out.println("Для взлома текста методом BruteForce введите \'3\'");
        return new Scanner(System.in).nextInt();
    }

    public int selectKeyMenu() {
        System.out.println("Введите ключ шифрования");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
