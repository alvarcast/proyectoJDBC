package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Scan{

    public static int scanNum(String txt){
        int data = 0;
        Scanner scn = new Scanner(System.in);

        while (data == 0){
            System.out.println(txt);

            try {
                data = scn.nextInt();
            } catch (InputMismatchException ex){
                System.err.println("Se ha introducido un valor inválido, intentelo de nuevo.");
            }

            scn.nextLine();
        }

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

