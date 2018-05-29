package Rc4;

import Rc4.RC4;

import java.util.ArrayList;

public class Task1 {
    public static void main(String[] args) {
        String key1 = "ZufarKayumov";
        byte[] key = key1.getBytes();
        String s = "Группа 09-622";
        byte[] text = s.getBytes();
        RC4 crypt = new RC4(key);
        byte[] SC = crypt.rc4(text);
        int k = 16;
        byte[] R = new byte[k];
        ArrayList<Byte> S = new ArrayList<>(); // создание Arraylist

        for(int i=0;i<k;i++) {
            S.add((byte) i); // Заполнение его элементами от 0 до k
            System.out.print(S.get(i)+" ");
        }
        System.out.println();
        for (int i = 0; i < k; i++) {     //Построение подстановок на множестве M= {0,1,…,k}
            R[i] = S.get((int)RC4M(k-i, SC, i%SC.length));
            for (int j = 0; j<k;j++){
                if (j==S.get((int)RC4M(k-i, SC, i%SC.length))){
                    S.remove((int)RC4M(k-i, SC, i%SC.length));
                    break;
                }
            }
        }
        for(int i=0;i<R.length;i++) {
            System.out.print(R[i]+" ");
        }
    }
    public static byte RC4M (int k, byte[] S, int i){
        boolean f = true;
        byte c = 0, s = 0;
        int y = (256 - (256%k));
        while (f) {
            c = S[i];
            if (c < y) {
                f = false;
                s = (byte) (c%k);
            }
            if (c<0){
                s = (byte) (-1*c%k);
            }
        }
        return s;
    }
}
