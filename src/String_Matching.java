package src;


public class String_Matching {
    public static int getDistance(String string1,String string2){ //Menggunakan Hamming Distance
        int value = 0;
        for (int i = 0; i<string1.length();i++){
            if (string1.charAt(i) != string2.charAt(i)){
                value++;
            }
        }
        return value;
    }
    

}
