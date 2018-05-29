package Blowfish;

import java.util.ArrayList;
import java.util.Arrays;

public class blowfishTEST {
    public static void main(String[] arg) {
        // ������� ����
        blowfish key = new blowfish("indira".getBytes());
        // �������� ���������
        String message = "Sharafulova Indira Blowfish";

        // ��������� � ����� ������� ������ ������, ������� 8-��
        // � ����������� ���������
        byte[] msg = key.padding(message.getBytes(), ' ');
        // ������� ������������ ���������
        byte[] textByteArr = message.getBytes();
        System.out.println("������������ ��������� " + new String(msg));
        System.out.println("������������ ��������� " + new String(textByteArr));
        // ���������� ����������
        byte[] cipher = key.decipher(msg);
        // ������� ������������� ���������
        System.out.println("�������������� ��������� " + new String(cipher));
        // ���������� �������������
        byte[] decip = key.encipher(cipher);
        // ������� �������������� ���������
        System.out.println("�������������� ��������� " + new String(decip));

        /*
        Хэш значение
        */
        byte[] hashArr = Hash3Mode.HashFunction(textByteArr, key, "Any_text");
        System.out.println();
        String hash = new String(hashArr);

        String res = hash + message;
        byte[] resArr = res.getBytes();
        byte[] reshashArr = Hash3Mode.HashFunction(resArr, key, "Any_text");

        String hashRes = new String(reshashArr);
        String HASH = hash + hashRes;
        System.out.println("Хэш значение: " + HASH);

        /*
        Цифровая подпись
        */
        DigitalSignature signature = new DigitalSignature();
        signature.Sign(message, HASH.getBytes());

        /*
        Режим шифрования CNLF
        */
        String text = "ThisTextThisTextThisTextThisTextThisTextThisTextThisText";
        String S0 = "56789123";
        byte[] sB = text.getBytes();
        String Cip = "";
        ArrayList<byte[]> P = Hash3Mode.toBlocks(sB);
        ArrayList<byte[]> K = new ArrayList<>();
        ArrayList<byte[]> C = new ArrayList<>();

        int g = 0;
        for (int j = 0; j < P.size(); j++) {
            if (j == 0) {
                g = Integer.parseInt(S0);
            }
            g = g + 1;
            S0 += String.valueOf(g);
        }
        System.out.println("S0: " + S0);
        ArrayList<byte[]> S = Hash3Mode.toBlocks(S0.getBytes());
        for (int i = 0; i < P.size(); i++) {
            byte[] t = P.get(i);
            byte[] current = key.decipher(t);
            Cip += new String(current);
            C.add(current);
        }
        System.out.println("До использования режима: \n" + Cip);
        ArrayList<byte[]> CopyP = new ArrayList<>(P.size());
        for (int i = 0; i < P.size(); i++) {
            byte[] t = C.get(i);
            byte[] current = key.encipher(t);
            CopyP.add(current);
        }
        System.out.println("После применения режима шифрования: ");
        for (int i = 0; i < P.size(); i++) {
            K.add(i, key.decipher(S.get(i)));
            blowfish blow = new blowfish(K.get(i));
            C.set(i, blow.decipher(CopyP.get(i)));
            System.out.print(new String(C.get(i)));
            P.set(i, blow.encipher(C.get(i)));
        }
        System.out.println("\nПосле расшифрования:");
        for (int i = 0; i < P.size(); i++) {
            String p = new String(CopyP.get(i));
            System.out.print(p);
        }
    }
}
