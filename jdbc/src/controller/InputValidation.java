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

    public static String checkDate(){
        String pattern = "^(2013|20[1-9][3-9]|202[0-5])-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
        String date;

        do {
            date = Scan.scanText("Introduce date (YYYY-MM-DD):");

            if (!date.matches(pattern)){
                System.err.println("Invalid date format, please try again");
            }
        } while (!date.matches(pattern));

        return date;
    }
}
