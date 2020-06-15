package com.company;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) {
        try {
            Game game = new Game(args);
            game.play();
        } catch (MyException | InvalidKeyException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }
}
