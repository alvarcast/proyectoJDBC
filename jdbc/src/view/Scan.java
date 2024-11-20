package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Scan{

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
                    out = true;
                }
            } catch (InputMismatchException ex){
                System.err.println("Wrong input, try again");
            }

            scn.nextLine();
        } while (!out);

        return data;
    }

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
                    out = true;
                }
            } catch (InputMismatchException ex){
                System.err.println("Wrong input, try again. Remember to use ',' not '.'");
            }

            scn.nextLine();
        } while (!out);

        return data;
    }

    public static String scanText(String txt){
        Scanner scn = new Scanner(System.in);
        String data = "";

        while (data.isEmpty()){
            System.out.println(txt);
            data = scn.nextLine();
        }

        return data;
    }

    public static void waitForInput(){
        Scanner scn = new Scanner(System.in);

        System.out.println(" ");
        System.out.println(">>> Press enter to continue <<<");
        scn.nextLine();
    }
}

