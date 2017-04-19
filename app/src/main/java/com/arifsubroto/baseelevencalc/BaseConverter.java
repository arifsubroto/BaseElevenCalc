package com.arifsubroto.baseelevencalc;

/**
 * Created by LabTIA-42 on 16/04/2017.
 */

public class BaseConverter {
        // 120 base 11 to base (10) = 0 + 22 + 121 = 143
        // 5A base 11 to base (10) = 10 + 55 = 65

        // ASCII
        // 0 -> 48 | 9 -> 57 | A -> 65 | a -> 97
        // 212 = (2 * 32) + (1 * 31) + (2 * 30)

        public static String convertToBase(String num, int fromBase, int toBase){
            // Convert to decimal
            if(num.contains("-")){
                return "negative";
            }
            int dec = Integer.parseInt(toBase10(num, fromBase));

            // Convert to base K from decimal
            return ""+fromBase10(dec, toBase);
        }

        private static String fromBase10(int num, int base){
            if (num == 0) return "0";

            String s = "";
            while(num > 0){
                s = toChar(num % base)+s;
                num = num / base;
            }
            return s;
        }

        private static String toBase10(String num, int fromBase){
            int result = 0;
            for (int i = 0; i < num.length(); i++) {
                result = result + (toInt(num.charAt(i))*(int)Math.pow(fromBase, (num.length()-1-i)));
            }
            return ""+result;
        }

        private static int toInt(char c){
            if(c < '0' || c > 'Z') throw new IllegalArgumentException("invalid char");
            if((c >= '0') && (c <= '9')) return c - '0';
            return c - 'A' + 10;
        }

        public static char toChar(int i){
            if(i < 0 || i > 36) throw new IllegalArgumentException("invalid digit");
            if(i < 10) return (char) ('0'+i);
            return (char) ('A' + i - 10);
        }

}
