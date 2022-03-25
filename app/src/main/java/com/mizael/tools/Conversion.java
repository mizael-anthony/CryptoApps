package com.mizael.tools;

public class Conversion {

    // Conversion String en Nombre izany hoe en Tableau de nombre ASCII
    //TODO: Mila tohizana
    public static int[] toNumber(String input){
        int[] num = new int[input.length()];
        for (int i = 0; i < input.length(); i++)
            num[i] = (int)input.charAt(i);

        return num;
    }

    public static String toBinary(int input){
        StringBuilder bin = new StringBuilder();
        final int[] octet = {128, 64, 32, 16, 8, 4, 2, 1};

        for(int bit: octet){
            if(input >= bit){
                bin.append("1");
                input -= bit;
            }
            else{
                bin.append("0");
            }
        }
        return bin.toString();
    }


    public static int toDecimal(String input){
        int dec = 0;

        for(int i = 0; i < input.length(); i++){
            if(input.charAt(input.length() - i - 1) == '0'){
                continue;
            }
            dec += (int)Math.pow(2, i);
        }

        return dec;
    }

}

