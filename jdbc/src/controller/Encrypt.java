package controller;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class Encrypt {

    //Información sobre encripción sacada de https://www.javatpoint.com/how-to-encrypt-password-in-java
    //Encripción de contraseña por seguridad, algoritmo MD5
    public static String crypt(String password){
        MessageDigest m;
        StringBuilder stringBuilder = new StringBuilder();
        byte[] bytes;
        String encrypted_password = "";

        try {
            m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());

            bytes = m.digest();
            for (byte aByte : bytes) {
                stringBuilder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            encrypted_password = stringBuilder.toString();

        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        return encrypted_password;
    }
}
