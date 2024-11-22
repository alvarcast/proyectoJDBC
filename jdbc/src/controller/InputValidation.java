package controller;

import view.Scan;

public class InputValidation {

    //Metodo para validar un numero entero dentro de un rango
    public static int checkNumRange(int min, int max, int var, String txt){
        do{
            var = Scan.scanInt(txt);
            if (var < min || var > max){
                System.err.println("Invalid number, try again");
            }
        }while (var < min || var > max);

        return var;
    }

    //Metodo para validar un numero decimal dentro de un rango
    public static float checkNumRange(int min, int max, float var, String txt){
        do{
            var = Scan.scanFloat(txt);
            if (var < min || var > max){
                System.err.println("Invalid number, try again");
            }
        }while (var < min || var > max);

        return var;
    }

    //Metodo para comprobar el formato de las fechas puestas por el usuario
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

    //Metodo que detecta si se ha introducido un valor vetado (SQL inject)
    public static boolean detectSQLInject(String input){
        boolean tryHack = false;

        String[] vetados = new String[8];
        vetados [0] = " or ";
        vetados [1] = " and ";
        vetados [2] = " = ";
        vetados [3] = " + ";
        vetados [4] = " is ";
        vetados [5] = "'";
        vetados [6] = " like ";
        vetados [7] = " join ";

        for (int i = 0; i < 8; i++){
            if(input.indexOf(vetados[i]) >= 0){
                tryHack = true;
                System.err.println("Trying to SQL inject? Get banned lol :D");
            }
        }

        return tryHack;
    }

    //Metodo que calcula la longitud de una cadena numerica
    public static boolean countChar(int count, int var){
        boolean tooLong;

        if (Integer.toString(var).length() > count){
            System.err.println("The value is too long, the maximum length for this input is " + count);
            tooLong = true;
        } else {
            tooLong = false;
        }

        return tooLong;
    }

    //Metodo que calcula la longitud de una cadena
    public static boolean countChar(int count, String var){
        boolean tooLong;

        if (var.length() > count){
            System.err.println("The value is too long, the maximum length for this input is " + count);
            tooLong = true;
        } else {
            tooLong = false;
        }

        return tooLong;
    }
}
