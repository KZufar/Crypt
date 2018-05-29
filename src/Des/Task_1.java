package Des;

public class Task_1 {

    public static void main(String[] args) {
//        int x = 5, y = 7;
//
//        x = x ^ y; // x == 2
//        y = x ^ y; // y == 5
//        x = x ^ y; // x == 7
//        int g, h;
//        g = (180 + 80) % 256;
//        h = (180 + 80) & 0xFF;
//        System.out.println(g);
//        System.out.println(h);
       /* byte[] key = {1, 2, 3, 4, 5, 6, 7};
        String s = "ZufarKay";
        byte[] text = s.getBytes();
        DES crypt = new DES(key);
        byte[] ciphertext = crypt.encrypt(text);

        for (int i = 0; i < ciphertext.length; i++) {

            System.out.print(ciphertext[i] + " ");
        }
        System.out.println();
        byte[] key1 = {7, 6, 5, 4, 3, 2, 1};
        DES crypt1 = new DES(key1);

        byte[] text1 = crypt1.decrypt(ciphertext);
        String f = new String(text1);
        System.out.println(f);
        for (int i = 0; i < ciphertext.length; i++) {

            System.out.print(text1[i] + " ");
        }
        System.out.println();*/

//        String MD = "12ferwr3";
//        String key4 = "ZufarKay";
//        key4 = cr.toBit(key4);
//        MD = cr.addNULL(cr.toBit(MD));
//        String cipher = cr.DESen(MD, key4);
//        System.out.println(cipher);
//        String temp = cr.convertChiper(cipher);
//        System.out.println(temp);
//        String c = cr.DESde(cipher, key4);
//        System.out.println(cr.convertChiper(c));
//        System.out.println();


        DESEn cr = new DESEn();
        /*String text = "ZufarKayZufarKayZufarKayZufarKayZufarKayZufarKay";
        String C0 = "KFU";
        C0 = cr.addNULL(cr.toBit(C0));
        byte[] sB = text.getBytes();
        ArrayList<byte[]> P = Des.Hash.toBlocks(sB);
        ArrayList<byte[]> C = new ArrayList<>();

        String key = "09-622_2", plain, Cip = "";
        key = cr.toBit(key);
        for (int i=0; i<P.size(); i++){
            String stB = new String(P.get(i));
            if (i==P.size()-1){
                plain = cr.addNULL(cr.toBit(stB));
            }{
                plain = cr.toBit(stB);
            }
            Cip += cr.convertChiper(cr.DESen(plain, key));
            byte[] cip = Cip.getBytes();
            //C.add(Arrays.copyOf(bufferArray, 8))
                    C.add(cr.convertChiper(cr.DESen(plain, key)).getBytes());
        }
        ArrayList<byte[]> Copy = C;
        System.out.println(Cip);
        for (int i=0; i<P.size(); i++){
            if (i!=0) {
                String cv = new String(C.get(i-1));
                cv = cr.addNULL(cr.toBit(cv));
                C.set(i, Des.Hash.xor(P.get(i), cr.convertChiper(cr.DESen(cv, key)).getBytes()));
                P.set(i, Des.Hash.xor(C.get(i), cr.convertChiper(cr.DESen(cv, key)).getBytes()));
            }else{
                C.set(i, Des.Hash.xor(P.get(i), cr.convertChiper(cr.DESen(C0, key)).getBytes()));
                P.set(i, Des.Hash.xor(C.get(i), cr.convertChiper(cr.DESen(C0, key)).getBytes()));
            }
        }
        for (int i=0; i<C.size(); i++){
            String r = new String(C.get(i));
            System.out.print(r);
        }
        System.out.println();
        for (int i=0; i<P.size(); i++){
            String r = new String(P.get(i));
            System.out.print(r);
        }
*/




 //Вычисление хэш значения с использованием 64 битовой функции шифрования Des.DESEn
        String text = "ZufarKayumov_09-622";
        byte[] textByteArr = text.getBytes();
        byte[] hashArr  = Hash.HashFunction(textByteArr, cr, "ILoveKazan");

        for (int i = 0; i<hashArr.length; i++){
            System.out.print(hashArr[i] + "  ");
        }
        System.out.println();
        String hash = new String(hashArr);
        System.out.println("Хэш значение 1: " + hash);

        String res = hash+text;
        System.out.println(res);
        byte[] resArr = res.getBytes();
        byte[] reshashArr  = Hash.HashFunction(resArr, cr, "ILoveKazan");

        for (int i = 0; i<hashArr.length; i++){
            System.out.print(reshashArr[i] + "  ");
        }
        System.out.println();
        String hashRes = new String(reshashArr);
        System.out.println("Хэш значение 2: " + hashRes);
        System.out.println();
        System.out.println("Итоговое хэш значение: " + hash+hashRes);


        Signature sign = new Signature();
        sign.Sign(text, (hash+hashRes).getBytes());


//        for (int i = 0; i < 3; i++) {
//            h = cr.addNULL(cr.toBit(h));
//            A[i] = cr.toBit(A[i]);
//            String Cip = cr.convertChiper(cr.DESen(h, A[i]));
////            byte[] C = Cip.getBytes();
////            byte[] H = cr.convertChiper(h).getBytes();
////            int[] R = new int[C.length];
////            for (int j = 0; j<C.length; j++){
////                R[j] = ((int)C[i] ^ (int)H[i]);
////            }
////            for (int k = 0; i < C.length; i++) {
////
////                System.out.print(R[k] + " ");
////            }
//            h = xor(Cip, cr.convertChiper(h));
//            //long v = Long.parseLong(cr.toBit(Cip.getBytes()))^Long.parseLong(h);
//           // h = cr.convertChiper(String.valueOf(v));
//        }
//        System.out.println("Хэш значение: " + h);

    }
    public static String xor(String a, String b){
        StringBuilder sb = new StringBuilder();
        for(int k=0; k < a.length(); k++)
            sb.append((a.charAt(k) ^ b.charAt(k + (Math.abs(a.length() - b.length()))))) ;
        return sb.toString();
    }

    public static String[] CFBEncrypt(String currenttext, String Key, String plaintext){
        DESEn c = new DESEn();
        String plain[]= c.devn(8, plaintext);
        String chiper[]= new String[plain.length];
 
        String init = c.toBit(currenttext);
        if(init.length()<64) init=c.addNULL(init);
        String key = c.toBit(Key);
        if(key.length()<64) key = c.addNULL(key);

        for(int i=0;i<plain.length;i++){
            String p = c.toBit(plain[i]);
            if(p.length()<64) p=c.addNULL(p);
            String desResult = c.DESen(init, key);
            init = c.XOR(desResult, p);
            chiper[i]=init;
        }
        return chiper;
    }

    public static String[] CFBDecrypt(String currenttext, String Key, String chipertext){
        DESEn c = new DESEn();
        String chiper[]= c.devn(64, chipertext);
        String plain[]= new String[chiper.length];

        String init = c.toBit(currenttext);
        if(init.length()<64) init = c.addNULL(init);
        String key = c.toBit(Key);
        if(key.length()<64) key = c.addNULL(key);

        for(int i=0;i<chiper.length;i++){
            String p=chiper[i];
            if(p.length()<64) p = c.addNULL(p);
            String desResult = c.DESen(init, key);
            plain[i]=c.XOR(desResult, p);
            init = p;
        }
        return plain;
    }
}





        /*System.out.println("Crypt");
        String st = "Kayumov";
        String k = "Zufar";
        byte[] is = encode(st, k);
        for (int i = 0; i < is.length; i++) {

            System.out.print(is[i] + " ");
        }
        String pl = decode(is, k);
            System.out.print(pl + " ");
*/




       /* public static byte[] encode(String pText, String pKey) {
            byte[] txt = pText.getBytes();
            byte[] key = pKey.getBytes();
            byte[] res = new byte[pText.length()];

            for (int i = 0; i < txt.length; i++) {
                res[i] = (byte) (txt[i] ^ key[i % key.length]);
            }
            return res;
        }

        public static String decode(byte[] pText, String pKey) {
            byte[] res = new byte[pText.length];
            byte[] key = pKey.getBytes();

            for (int i = 0; i < pText.length; i++) {
                res[i] = (byte) (pText[i] ^ key[i % key.length]);
            }

            return new String(res);
        }*/

        /*String s = "Key";
        byte[] key = s.getBytes(StandardCharsets.US_ASCII);

        Rc4.RC4 encoder = new Rc4.RC4(key);
        String testString = "1Plaintext";
        byte[] testBytes = testString.getBytes(StandardCharsets.US_ASCII);
        byte[] result = encoder.encrypt(testBytes);
        for (int i = 0; i<result.length; i++){

            System.out.println(result[i]);
        }

        Rc4.RC4 decoder = new Rc4.RC4(key);
        byte[] decryptedBytes = decoder.decrypt(result);
        String decryptedString = decryptedBytes.toString();
        System.out.println(decryptedString);*/


