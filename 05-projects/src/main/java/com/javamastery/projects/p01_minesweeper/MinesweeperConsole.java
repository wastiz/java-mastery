package com.javamastery.projects.p01_minesweeper;

import java.util.Random;
import java.util.Scanner;

/** Консольная версия. Запусти main после того, как пройдут тесты. Команды: "o 3 4" — открыть, "f 3 4" — флаг. */
public class MinesweeperConsole {
    public static void main(String[] args) {
        Minesweeper game = Minesweeper.random(9, 9, 10, new Random());
        Scanner in = new Scanner(System.in);
        while (game.status() == Minesweeper.Status.PLAYING) {
            System.out.println(game.render());
            System.out.print("> ");
            String[] cmd = in.nextLine().trim().split("\\s+");
            try {
                int r = Integer.parseInt(cmd[1]), c = Integer.parseInt(cmd[2]);
                if (cmd[0].equals("f")) game.toggleFlag(r, c);
                else game.reveal(r, c);
            } catch (RuntimeException e) {
                System.out.println("Формат: o <row> <col> | f <row> <col>");
            }
        }
        System.out.println(game.render());
        System.out.println(game.status() == Minesweeper.Status.WON ? "Победа! 🎉" : "Бум! 💥");
    }
}
