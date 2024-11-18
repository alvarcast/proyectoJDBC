package controller;

import view.Scan;

public class InputValidation {

    public static int checkNumRange(int min, int max, int var, String txt){
        do{
            var = Scan.scanInt(txt);
            if (var < min || var > max){
                System.err.println("Invalid number, try again");
            }
        }while (var < min || var > max);

        return var;
    }

    public static float checkNumRange(int min, int max, float var, String txt){
        do{
            var = Scan.scanFloat(txt);
            if (var < min || var > max){
                System.err.println("Invalid number, try again");
            }
        }while (var < min || var > max);

        return var;
    }
}
