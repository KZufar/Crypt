package TwoFish;

import java.util.Scanner;

public class MyTwofish 
{
	public static void main(String[] args) 
	{
		try (Scanner in = new Scanner(System.in))
		{
			System.out.print("Enter the key : ");
			String k = in.nextLine();
			System.out.print("Enter the plain text : ");
			String pt = in.nextLine();
			byte tempKey[] = k.getBytes();
			byte tempPlainText[] = pt.getBytes();
			byte key[] = new byte[16];
			byte plainText[] = new byte[16];
					
			int i;
			for(i=0; i<16;i++)
			{
				if(i<tempKey.length)  key[i] = tempKey[i];
				else key[i] = (byte)0;
			}
			for(i=0; i<16;i++)
			{
				if(i<tempPlainText.length)  plainText[i] = tempPlainText[i];
				else plainText[i] = (byte)0;
			}
						
			Object K = TwoFish.Twofish_Algorithm.makeKey(key);
			byte[] ct = TwoFish.Twofish_Algorithm.blockEncrypt(plainText, 0, K);
			String cipherText = new String(ct);
			System.out.println("Cipher text : "+cipherText);
			
			System.out.print("Enter the key : ");
			String k2 = in.nextLine();
			byte tempKey2[] = k2.getBytes();
			byte key2[] = new byte[16];
			for(i=0; i<16;i++)
			{
				if(i<tempKey2.length)  key2[i] = tempKey2[i];
				else key2[i] = (byte)0;
			}
			
			Object K2 = TwoFish.Twofish_Algorithm.makeKey(key2);
			byte[] cpt = TwoFish.Twofish_Algorithm.blockDecrypt(ct, 0, K2);
			String ot = new String(cpt);
			System.out.println("Decrypted Text : "+ot);
		}
	}
}
