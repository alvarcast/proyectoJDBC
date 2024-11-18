package controller;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class Encrypt {

    //Información sobre encripción sacada de https://www.javatpoint.com/how-to-encrypt-password-in-java
    public static String crypt(String password){
        StringBuilder stringBuilder = new StringBuilder();
        byte[] bytes;
        String encrypted_password = "";

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());

            bytes = m.digest();
            for(int i=0; i< bytes.length ;i++)
            {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            encrypted_password = stringBuilder.toString();

        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        return encrypted_password;
    }
}
