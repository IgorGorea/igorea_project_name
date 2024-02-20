package unitTests;

import java.util.ArrayList;

public class Chocolate {
    public static ArrayList<Integer> choc(int length, int width) {
        ArrayList<Integer> tiles = new ArrayList<>();
        int maxLength = length;
        int maxWidth = width;
        for (int i = 0; i < maxLength; i++) {
            int number = length * maxWidth;
            tiles.add(number);
            length--;
        }
        for (int i = 0; i < maxWidth; i++) {
            int number = maxLength * width;
            tiles.add(number);
            width--;
        }

    return tiles;
    }
    public static String chocComparison(int length, int width, int k){
        ArrayList<Integer> arrayList = choc(length, width);
        ArrayList<Integer> falseList = new ArrayList<>();
        ArrayList<Integer> trueList = new ArrayList<>();
        String res;
        for(int a : arrayList){
//        for(int i=0; i<arrayList.toArray().length; i++){
            if(a == k){
                trueList.add(a);
//                return false;
            } else {
                falseList.add(a);
//                return true;
//                break;
            }
        }
        if(trueList.size() != 0){
            res = "YES";
        } else {
            res = "NO";
        }
        return res;
    }
}

