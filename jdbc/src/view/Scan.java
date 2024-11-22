package view;

import controller.Banner;
import controller.InputValidation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Scan{

    //Metodo para pedir un numero
    public static int scanInt(String txt){
        Scanner scn = new Scanner(System.in);
        boolean out = false;
        int data = 0;

        do {
            System.out.println(txt);

            try {
                data = scn.nextInt();
                if (data < 0){
                    System.err.println("Negative values aren't allowed, please try again");
                } else {
                    //Compruebo si el valor dado esta vetado. Si esta vetado llamo a Banner.banUser()
                    if (InputValidation.detectSQLInject(Integer.toString(data))){
                        Banner.banUser();
                    }
                    out = true;
                }
            } catch (InputMismatchException ex){
                System.err.println("Wrong input, try again");
            }

            scn.nextLine();
        } while (!out);

        return data;
    }

    //Metodo para pedir un numero decimal
    public static float scanFloat(String txt){
        Scanner scn = new Scanner(System.in);
        boolean out = false;
        float data = 0;

        do {
            System.out.println(txt);

            try {
                data = scn.nextFloat();
                if (data < 0){
                    System.err.println("Negative values aren't allowed, please try again");
                } else {
                    //Compruebo si el valor dado esta vetado. Si esta vetado llamo a Banner.banUser()
                    if (InputValidation.detectSQLInject(Float.toString(data))){
                        Banner.banUser();
                    }
                    out = true;
                }
            } catch (InputMismatchException ex){
                System.err.println("Wrong input, try again. Remember to use ',' not '.'");
            }

            scn.nextLine();
        } while (!out);

        return data;
    }

    //Metodo para pedir una cadena
    public static String scanText(String txt){
        Scanner scn = new Scanner(System.in);
        String data = "";

        while (data.isEmpty()){
            System.out.println(txt);
            data = scn.nextLine();
            //Compruebo si el valor dado esta vetado. Si esta vetado llamo a Banner.banUser()
            if (InputValidation.detectSQLInject(data)){
                Banner.banUser();
            }
        }

        return data;
    }

    //Metodo que espera hasta que se de cualquier valor para continuar (enter)
    public static void waitForInput(){
        Scanner scn = new Scanner(System.in);

        System.out.println(" ");
        System.out.println(">>> Press enter to continue <<<");
        scn.nextLine();
    }
}

