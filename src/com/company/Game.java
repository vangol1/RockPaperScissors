package com.company;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
class MyException extends Exception{
    public MyException(String message){
        super(message);
    }
}
class Game {
    private String[] availableMoves;

    Game(String[] availableMoves) throws MyException {
        if (availableMoves.length < 3 || availableMoves.length % 2 == 0 || Helper.hasDuplicates(availableMoves))
            throw new MyException("Error! Wrong parameters");
        this.availableMoves = availableMoves.clone();
    }

    public void printResult(int yourMove, int computersMove, byte[] key){
        System.out.println("Your move: " + availableMoves[yourMove - 1]);
        System.out.println("Computer's move: " + availableMoves[computersMove]);
        rpc(yourMove - 1, computersMove);
        System.out.println("Key:" + Helper.toHexString(key));
    }

    public  void rpc(int yourMove, int computerMove){
        int mod = Math.floorMod(yourMove - computerMove, availableMoves.length);
        if (mod == 0)
            System.out.println("Draw!");
        else if(mod <= (availableMoves.length - 1) / 2)
            System.out.println("You win!");
        else
            System.out.println("Computer wins!");
    }
    public int readMove(){
        Scanner in = new Scanner(System.in);
        int yourMove;
        String stringMove = in.next();
        try {
            yourMove = Integer.parseInt(stringMove);
        }
        catch (NumberFormatException e){
            yourMove = -1;
        }
        return yourMove;
    }

    public void play() throws InvalidKeyException, NoSuchAlgorithmException {
        byte[] key = Helper.generateKey();
        int computersMove = Helper.generateMove(availableMoves);
        byte[] hmac = Helper.generateHMAC(key, availableMoves[computersMove]);
        System.out.println("HMAC: " + Helper.toHexString(hmac));
        int yourMove;
        do {
            Helper.showMenu(availableMoves);
            yourMove = readMove();
            if (yourMove > 0 && yourMove <= availableMoves.length) {
                printResult(yourMove, computersMove, key);
            }
        } while (yourMove < 0 || yourMove > availableMoves.length);
    }
}
