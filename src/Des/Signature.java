package Des;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;

public class Signature {
    Random random;
    BigInteger p, q, g, y;
    public void Sign(String input, byte[] hash) {
        random = new SecureRandom();
        p = q = g = y = BigInteger.ZERO;
        newPQ();
        BigInteger x, k, h, j, r, s, e, d;
        x = q.subtract(new BigInteger(String.valueOf(Math.abs(random.nextInt()) + 1)));
        j = p.subtract(new BigInteger(String.valueOf(Math.abs(random.nextInt()) + 1)));
        g = j.modPow((p.subtract(new BigInteger("1"))).divide(q), p);
        y = g.modPow(x, p);
        k = q.subtract(new BigInteger(String.valueOf(Math.abs(random.nextInt()))));
        r = g.modPow(k, p);
        BigInteger po = r.mod(q);
        ByteBuffer w = ByteBuffer.wrap(hash);
        h = BigInteger.valueOf(w.getLong());
        s = ((po.multiply(h).add(x)).multiply(k.modPow(new BigInteger("-1"), q))).mod(q);
        input += " " + r.toString() + " " + s.toString() + " ";
        System.out.println(input);
        System.out.println("r: " + r);
        System.out.println("s: " + s);
        d = r.modPow(s, p);
        e = g.modPow(po.multiply(h), p).multiply(y).mod(p);
        System.out.println("d: " + d);
        System.out.println("e: " + e);
        if (d.equals(e)){
            System.out.println("Подпись верна!");
        }
    }
        public void newPQ()
        {
            if(random!=null) {
                q = new BigInteger(128, random);
                while (q.bitLength() != 128)
                    q = q.multiply(new BigInteger("2"));
                while (!q.isProbablePrime(10))
                    q = q.nextProbablePrime();
                p = (((new BigInteger("2")).pow(128)).multiply(q)).add(new BigInteger("1"));
                while (!p.isProbablePrime(10))
                    p = p.add(q.multiply(new BigInteger("2")));
            }
            System.out.println(p);
            System.out.println(q);
        }
    }


