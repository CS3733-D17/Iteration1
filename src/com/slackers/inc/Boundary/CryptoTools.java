package com.slackers.inc.Boundary;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Scanner;
import java.util.prefs.Preferences;

public class CryptoTools {

    public static String encrypt(String value, Preferences p) throws GeneralSecurityException, IOException {
        if (p.get("key", null) == null) {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey sk = keyGen.generateKey();
            p.put("key", byteArrayToHexString(sk.getEncoded()));

        }

        SecretKeySpec sks = getSecretKeySpec(p);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
        byte[] encrypted = cipher.doFinal(value.getBytes());
        return byteArrayToHexString(encrypted);
    }

    public static String decrypt(String message, Preferences p) throws GeneralSecurityException, IOException {
        SecretKeySpec sks = getSecretKeySpec(p);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sks);
        byte[] decrypted = cipher.doFinal(hexStringToByteArray(message));
        return new String(decrypted);
    }



    private static SecretKeySpec getSecretKeySpec(Preferences p) throws NoSuchAlgorithmException, IOException {
        byte [] key = readKeyFile(p);
        SecretKeySpec sks = new SecretKeySpec(key, "AES");
        return sks;
    }

    private static byte [] readKeyFile(Preferences p) throws FileNotFoundException {
        String keyValue = p.get("key", null);
        return hexStringToByteArray(keyValue);
    }


    private static String byteArrayToHexString(byte[] b){
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++){
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    private static byte[] hexStringToByteArray(String s) {
        System.out.println(s);
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++){
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte)v;
        }
        return b;
    }

    /*public static void main(String[] args) throws Exception {
        String clearPwd= "my password is hello world";

        Preferences p1 = Preferences.userNodeForPackage(CryptoTools.class);

        p1.put("user", "Real Person");
        String encryptedPwd = CryptoTools.encrypt(clearPwd, p1);
        p1.put("pwd", encryptedPwd);

        // ==================
        Preferences p2 = Preferences.userNodeForPackage(CryptoTools.class);

        encryptedPwd = p2.get("pwd", "Hello");
        System.out.println(encryptedPwd);
        System.out.println(CryptoTools.decrypt(encryptedPwd, p2));
    }*/
}
