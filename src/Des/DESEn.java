package Des;

import java.math.BigInteger;

public class DESEn {

        //Convert plaintext to bit
        public String toBit(String plain) {
            String plaintext="";
            String dev8[] = devn(1,plain);
            for (int i=0;i<dev8.length;i++) {
                String temp = new BigInteger(dev8[i].getBytes()).toString(2);
                int tempLength=temp.length();
                while (tempLength<8) {
                    plaintext+="0";
                    tempLength++;
                }
                plaintext+=temp;
            }
            return plaintext;
        }

        //Initial Permutation
        public String InP(String plain) {
            String plainIP="";
            final int[] table = {
                    58, 50, 42, 34, 26, 18, 10, 2,
                    60, 52, 44, 36, 28, 20, 12, 4,
                    62, 54, 46, 38, 30, 22, 14, 6,
                    64, 56, 48, 40, 32, 24, 16, 8,
                    57, 49, 41, 33, 25, 17, 9,  1,
                    59, 51, 43, 35, 27, 19, 11, 3,
                    61, 53, 45, 37, 29, 21, 13, 5,
                    63, 55, 47, 39, 31, 23, 15, 7
            };
            for(int i=0;i<table.length;i++){
                char temp = plain.charAt(table[i]-1);
                plainIP = plainIP+temp;
            }
            return plainIP;
}

    //Add padding
        public String addNULL(String plain) {
            String newPlain=plain;
            int temp= plain.length();
            while (temp<64){
                newPlain = newPlain+"0";
                temp = newPlain.length();
            }
            return newPlain;
        }

        //Expansion
        public String ExP(String plain) {
            String newPlain="";
            int [] table ={ 32, 1, 2, 3, 4, 5,
                    4 , 5, 6, 7, 8, 9,
                    8 , 9,10,11,12,13,
                    12,13,14,15,16,17,
                    16,17,18,19,20,21,
                    20,21,22,23,24,25,
                    24,25,26,27,28,29,
                    28,29,30,31,32,1 };
            for(int i=0;i<table.length;i++){
                char temp=plain.charAt(table[i]-1);
                newPlain=newPlain+temp;
            }
            return newPlain;
        }

        //Permutation after s-box
        public String Permutation(String plain) {
            String newPlain="";
            int table[] ={ 16,  7, 20, 21, 29, 12, 28, 17,
                    1, 15, 23, 26,	5, 18, 31, 10,
                    2,  8, 24, 14, 32, 27,	3,  9,
                    19, 13, 30,  6, 22, 11,	4, 25};

            for(int i=0;i<table.length;i++){
                char temp = plain.charAt(table[i]-1);
                newPlain = newPlain+temp;
            }
            return newPlain;
        }

        //Divide plaintext to n character
        public String[] devn(int n, String plain) {
            int idx = 0;
            int temp=plain.length()/n, temp2 = plain.length()%n;
            if(temp2>0) temp+=1;
            String newPlain[] =  new String[temp];
            int begin=0, end=n;
            for(int i=0;i<temp;i++){
                newPlain[idx]=plain.substring(begin, end);
                begin = end;
                end = end+n;
                if (end>plain.length()) end=plain.length();
                idx++;
            }
            return newPlain;
        }

        //Final Permutation
        public String FinP(String plain) {
            String newPlain="";
            int table[]={40, 8, 48,	16, 56,	24, 64,	32,
                    39, 7, 47,	15, 55,	23, 63,	31,
                    38, 6, 46,	14, 54,	22, 62,	30,
                    37, 5, 45,	13, 53,	21, 61,	29,
                    36, 4, 44,	12, 52,	20, 60,	28,
                    35, 3, 43,	11, 51,	19, 59,	27,
                    34, 2, 42,	10, 50,	18, 58,	26,
                    33, 1, 41,	9,  49,	17, 57,	25};
            for(int i=0;i<table.length;i++){
                char temp = plain.charAt(table[i]-1);
                newPlain = newPlain+temp;
            }
            return newPlain;

        }

        //Permutation Choice 2
        public String PC2(String plain) {
            String newPlain="";
            int table[] ={14, 17, 11, 24,  1,  5,
                    3, 28, 15,  6, 21, 10,
                    23, 19, 12,  4, 26,  8,
                    16,  7, 27, 20, 13,  2,
                    41, 52, 31, 37, 47, 55,
                    30, 40, 51, 45, 33, 48,
                    44, 49, 39, 56, 34, 53,
                    46, 42, 50, 36, 29, 32};
            for(int i=0;i<table.length;i++){
                char temp = plain.charAt(table[i]-1);
                newPlain = newPlain+temp;
            }
            return newPlain;
        }

        //Permutation Choice 2
        public String PC1(String plain) {
            String newPlain="";
            int tableLeft[] = {57, 49, 41, 33, 25, 17,  9,
                    1, 58, 50, 42, 34, 26, 18,
                    10,  2, 59, 51, 43, 35, 27,
                    19, 11,  3, 60, 52, 44, 36};
            int tableRight[] ={63, 55, 47, 39, 31, 23, 15,
                    7, 62, 54, 46, 38, 30, 22,
                    14,  6, 61, 53, 45, 37, 29,
                    21, 13,  5, 28, 20, 12, 4};
            for(int i=0;i<tableLeft.length;i++){
                char temp = plain.charAt(tableLeft[i]-1);
                newPlain = newPlain+temp;
            }

            for(int i=0;i<tableRight.length;i++){
                char temp = plain.charAt(tableRight[i]-1);
                newPlain = newPlain+temp;
            }
            return newPlain;
        }

        //Shifting the key
        public String leftShift(int idx, String plain) {
            String newPlain=plain;
            for(int i=0;i<idx;i++){
                newPlain = newPlain.substring(1, newPlain.length())+newPlain.charAt(0);
            }
            return newPlain;
        }

        //XOR plain and key
        public String XOR(String plain, String key) {
            String newPlain="";
            char temp, temp1, temp2;
            for( int i=0; i<plain.length() ; i++)
            {
                temp = plain.charAt(i);
                temp1 = key.charAt(i);
                if(temp==temp1) {
                    temp2='0';
                } else temp2='1';
                newPlain+=temp2;
            }
            return newPlain;
        }

        //S-Box
        public String Sbox(String plain) {
            int[][] S = { {
                    14, 4,  13, 1,  2,  15, 11, 8,  3,  10, 6,  12, 5,  9,  0,  7,
                    0,  15, 7,  4,  14, 2,  13, 1,  10, 6,  12, 11, 9,  5,  3,  8,
                    4,  1,  14, 8,  13, 6,  2,  11, 15, 12, 9,  7,  3,  10, 5,  0,
                    15, 12, 8,  2,  4,  9,  1,  7,  5,  11, 3,  14, 10, 0,  6,  13
            }, {
                    15, 1,  8,  14, 6,  11, 3,  4,  9,  7,  2,  13, 12, 0,  5,  10,
                    3,  13, 4,  7,  15, 2,  8,  14, 12, 0,  1,  10, 6,  9,  11, 5,
                    0,  14, 7,  11, 10, 4,  13, 1,  5,  8,  12, 6,  9,  3,  2,  15,
                    13, 8,  10, 1,  3,  15, 4,  2,  11, 6,  7,  12, 0,  5,  14, 9
            }, {
                    10, 0,  9,  14, 6,  3,  15, 5,  1,  13, 12, 7,  11, 4,  2,  8,
                    13, 7,  0,  9,  3,  4,  6,  10, 2,  8,  5,  14, 12, 11, 15, 1,
                    13, 6,  4,  9,  8,  15, 3,  0,  11, 1,  2,  12, 5,  10, 14, 7,
                    1,  10, 13, 0,  6,  9,  8,  7,  4,  15, 14, 3,  11, 5,  2,  12
            }, {
                    7,  13, 14, 3,  0,  6,  9,  10, 1,  2,  8,  5,  11, 12, 4,  15,
                    13, 8,  11, 5,  6,  15, 0,  3,  4,  7,  2,  12, 1,  10, 14, 9,
                    10, 6,  9,  0,  12, 11, 7,  13, 15, 1,  3,  14, 5,  2,  8,  4,
                    3,  15, 0,  6,  10, 1,  13, 8,  9,  4,  5,  11, 12, 7,  2,  14
            }, {
                    2,  12, 4,  1,  7,  10, 11, 6,  8,  5,  3,  15, 13, 0,  14, 9,
                    14, 11, 2,  12, 4,  7,  13, 1,  5,  0,  15, 10, 3,  9,  8,  6,
                    4,  2,  1,  11, 10, 13, 7,  8,  15, 9,  12, 5,  6,  3,  0,  14,
                    11, 8,  12, 7,  1,  14, 2,  13, 6,  15, 0,  9,  10, 4,  5,  3
            }, {
                    12, 1,  10, 15, 9,  2,  6,  8,  0,  13, 3,  4,  14, 7,  5,  11,
                    10, 15, 4,  2,  7,  12, 9,  5,  6,  1,  13, 14, 0,  11, 3,  8,
                    9,  14, 15, 5,  2,  8,  12, 3,  7,  0,  4,  10, 1,  13, 11, 6,
                    4,  3,  2,  12, 9,  5,  15, 10, 11, 14, 1,  7,  6,  0,  8,  13
            }, {
                    4,  11, 2,  14, 15, 0,  8,  13, 3,  12, 9,  7,  5,  10, 6,  1,
                    13, 0,  11, 7,  4,  9,  1,  10, 14, 3,  5,  12, 2,  15, 8,  6,
                    1,  4,  11, 13, 12, 3,  7,  14, 10, 15, 6,  8,  0,  5,  9,  2,
                    6,  11, 13, 8,  1,  4,  10, 7,  9,  5,  0,  15, 14, 2,  3,  12
            }, {
                    13, 2,  8,  4,  6,  15, 11, 1,  10, 9,  3,  14, 5,  0,  12, 7,
                    1,  15, 13, 8,  10, 3,  7,  4,  12, 5,  6,  11, 0,  14, 9,  2,
                    7,  11, 4,  1,  9,  12, 14, 2,  0,  6,  10, 13, 15, 3,  5,  8,
                    2,  1,  14, 7,  4,  10, 8,  13, 15, 12, 9,  0,  3,  5,  6,  11
            } };
            String temp[] = devn(6, plain);
            String newPlain="";
            for(int i=0;i<temp.length;i++){
                String newTemp = temp[i];
                String CL ="";
                CL += newTemp.charAt(0);
                CL += newTemp.charAt(5);
                String CR = "";
                CR += temp[i].substring(1, 5);
                int row = Integer.parseInt(CL, 2);
                int col = Integer.parseInt(CR, 2);
                String tempPlain = Integer.toBinaryString(S[i][16*row+col]);
                int tplength = tempPlain.length();
                while (tplength<4) {
                    newPlain+="0";
                    tplength++;
                }
                newPlain+=tempPlain;
            }
            return newPlain;
        }

        //Generate all sub-key
        public String[] keyGen(String key) {
            int idx[] = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
            String[] newPlain = new String[16];
            String pc1 = PC1(key);
            String temp=pc1;
            for(int i=0;i<16;i++){
                String left = leftShift(idx[i],temp.substring(0, 28));
                String right =  leftShift(idx[i],temp.substring(28, temp.length()));
                temp=left+right;
                newPlain[i] = PC2(left+right);
            }
            return newPlain;
        }

        //Des Block Encryption
        public String DESen(String plain, String key) {
            String cplain = InP(plain);
            String chiper="";
            String Key[] = keyGen(key);
            String left = cplain.substring(0, 32);
            String right = cplain.substring(32, cplain.length());
            String temp=left, temp2=left;
            for(int i=0;i<16;i++){
                temp=left;
                left = right;
                right = XOR(F(right,Key[i]), temp);
            }
            chiper = right+left;
            chiper = FinP(chiper);
            return chiper;
        }

        //Chiper Function
        public String F(String plain, String key) {
            String newPlain = Permutation(Sbox(XOR(ExP(plain),key)));
            return newPlain;
        }

        //Convert Bit to String(text)
        public String convertChiper(String chiper) {
            String conv ="";
            String dev8[] = devn(8,chiper);
            for(int i=0;i<dev8.length;i++){
                int temp = Integer.parseInt(dev8[i], 2);
                conv += Character.toString((char)temp);
            }
            return conv;
        }

        //Chiper Decryption
        public String DESde(String chiper, String key) {
            String cChiper = InP(chiper);
            String plain="";
            String Key[] = keyGen(key);
            String left = cChiper.substring(0, 32);
            String right = cChiper.substring(32, cChiper.length());
            int j=16;
            for(int i=0;i<16;i++){
                j--;
                String temp=left;
                left = right;
                right = XOR(F(right,Key[j]), temp);
            }
            plain = right + left;
            plain = FinP(plain);
            return plain;
        }
    }
