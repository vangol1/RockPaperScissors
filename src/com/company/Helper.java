package com.company;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;

public class Helper {
    public static boolean hasDuplicates(String[] arr){
        HashSet<String> hashSet = new HashSet<>();
        for(String element : arr){
            if(!hashSet.add(element))
                return true;
        }
        return false;
    }
    public static String toHexString(byte[] bytes){
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
    public static byte[] generateKey(){
        SecureRandom random = new SecureRandom();
        byte key[] = new byte[32];
        random.nextBytes(key);
        return key;
    }
    public static byte[] generateHMAC(byte[] sharedKey, String text) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec key = new SecretKeySpec(sharedKey, "AES");
        mac.init(key);
        byte[] hmac = mac.doFinal(text.getBytes());
        return hmac;
    }
    public static int generateMove(String[] availableMoves){
        Random random = new Random();
        int moveIndex = random.nextInt(availableMoves.length);
        return moveIndex;
    }
    public static void showMenu(String[] availableMoves){
        System.out.println("Available moves:");
        for(int i = 0; i < availableMoves.length; i ++){
            System.out.println(i + 1 + " - " + availableMoves[i]);
        }
        System.out.println("0 - exit");
        System.out.println("Enter your move: ");
    }
}
