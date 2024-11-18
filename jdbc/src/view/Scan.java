package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Scan{

    public static int scanInt(String txt){
        boolean out = false;
        int data = 0;
        Scanner scn = new Scanner(System.in);

        do {
            System.out.println(txt);

            try {
                data = scn.nextInt();
                out = true;
            } catch (InputMismatchException ex){
                System.err.println("Wrong input, try again");
            }

            scn.nextLine();
        } while (!out);

        return data;
    }

    public static float scanFloat(String txt){
        boolean out = false;
        float data = 0;
        Scanner scn = new Scanner(System.in);

        do {
            System.out.println(txt);

            try {
                data = scn.nextFloat();
                out = true;
            } catch (InputMismatchException ex){
                System.err.println("Wrong input, try again\"");
            }

            scn.nextLine();
        } while (!out);

        return data;
    }

    public static String scanText(String txt){
        String data = "";
        Scanner scn = new Scanner(System.in);

        while (data.isEmpty()){
            System.out.println(txt);
            data = scn.nextLine();
        }

        return data;
    }

    public static void waitForInput(){
        System.out.println(" ");
        System.out.println(">>> Press enter to continue <<<");
        Scanner scn = new Scanner(System.in);
        scn.nextLine();
    }
}

