package com.sfaci.mihorario;

public class Util {

    public static int diaOrdinal(String dia){
        switch (dia){
            case "Lunes":
                return 2;
            case "Martes":
                return 3;
            case"Miercoles":
                return 4;
            case "Jueves":
                return 5;
            case"Viernes":
                return 6;
            default:
                return -1;
        }
    }
}

