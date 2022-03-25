package com.mizael.cryptoapps;

public class Conversion {

    public static String toBinary(int input){
        String bin = "";
        final int[] octet = {128, 64, 32, 16, 8, 4, 2, 1};

        for(int bit: octet){
            if(input >= bit){
                bin += "1";
                input -= bit;
            }
            else{
                bin += "0";
            }
        }
        return bin;
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
