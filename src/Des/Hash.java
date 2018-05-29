package Des;

import Des.DESEn;

import java.util.ArrayList;
import java.util.Arrays;

public class Hash {
    public static byte[] HashFunction(byte[] text, DESEn cipher, String h0) {
        byte[] sB = h0.getBytes();
        byte[] sBF = new byte[text.length];
        int j = 0, k = 0;
        while (k < text.length) {
            if (j!=8){
                sBF[k]=sB[j];
                j++;
                k++;
            }else{
                j=0;
                sBF[k] = sB[j];
                k++;
                j++;
            }
        }
        byte[]  h = sBF;
        ArrayList<byte[]> blockedText = toBlocks(text);
        for (int i=0; i<blockedText.size(); i++){
            String stH = new String(h);
            String stB = new String(blockedText.get(i));
            stB = cipher.toBit(stB);
            stH = cipher.addNULL(cipher.toBit(stH));
            String Cip = cipher.DESen(stH, stB);
            h = xor(cipher.convertChiper(Cip).getBytes(), h);
        }


            return h;
    }

    public static ArrayList<byte[]> toBlocks(byte[] text){
        int zero = 0;
        ArrayList<byte[]> list = new ArrayList();
        byte[] bufferArray = new byte[8];
        int j = 0;
        for (int i = 0; i < text.length; i++){
            if (j!=8){
                bufferArray[j] = text[i];
                j++;
            }else{
                list.add(Arrays.copyOf(bufferArray, 8));
                j=0;
                bufferArray[j] = text[i];
                j++;
            }
        }
        if (j!=8){
            for (int i = j; i<bufferArray.length; i++){
                bufferArray[i] = (byte)zero;
            }
            list.add(Arrays.copyOf(bufferArray, 8));
        } else if(j==8){
            list.add(Arrays.copyOf(bufferArray, 8));
        }
        return list;
    }

    public static byte[] xor(byte[] array1, byte[] array2) {
        byte[] buffer = new byte[8];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) ((array1[i] ^ array2[i]));
        }
        return buffer;
    }
}