package Rc4;

public class RC4 {
    byte[] S = new byte[256];
    byte[] K = new byte[256];
    public RC4(byte[] key) {
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
            K[i] = key[i % key.length];
        }
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + K[i]) & 0xFF;
            swap(S, i, j);
        }
    }

    public void swap(byte[] S, int index1, int index2) {
        byte temp = S[index1];
        S[index1] = S[index2];
        S[index2] = temp;
    }

    public byte[] encrypt(byte[] plaintext) {
        byte[] ciphertext = new byte[plaintext.length];
        int iRC4 = 0;
        int jRC4 = 0;
        int t, k;
        for (int counter = 0; counter < plaintext.length; counter++) {
            iRC4 = (iRC4 + 1) & 0xFF;
            jRC4 = (jRC4 + S[iRC4]) & 0xFF;
            swap(S, iRC4, jRC4);
            t = (S[iRC4] + S[jRC4]) & 0xFF;
            k = S[t];
            ciphertext[counter] = (byte) (plaintext[counter] ^ k);
        }
        return ciphertext;
    }

    public byte[] rc4(byte[] plaintext) {
        byte[] ciphertext = new byte[plaintext.length];
        int iRC4 = 0;
        int jRC4 = 0;
        byte[] k = new byte[plaintext.length];
        int t;
        for (int counter = 0; counter < plaintext.length; counter++) {
            iRC4 = (iRC4 + 1) & 0xFF;
            jRC4 = (jRC4 + S[iRC4]) & 0xFF;
            swap(S, iRC4, jRC4);
            t = (S[iRC4] + S[jRC4]) & 0xFF;
            ciphertext[counter] = (byte) (plaintext[counter] ^ S[t]);
            k[counter] = S[t];
        }
        return k;
    }



    public byte[] decrypt(byte[] ciphertext){
        return encrypt(ciphertext);
    }
}
