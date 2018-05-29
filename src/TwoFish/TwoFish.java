package TwoFish;

public class TwoFish {
    static final int BLOCK_SIZE = 16; // bytes in a data-block
    private static int ROUNDS = 16;
    private static int INPUT_WHITEN = 0;
    private static int OUTPUT_WHITEN = 4;
    private static int ROUND_SUBKEYS = 8; // 2*(#
    private static int SK_STEP = 0x02020202;
    private static int SK_BUMP = 0x01010101;
    private static int SK_ROTL = 9;
    byte[] p = new byte[16];
    int P0 = (p[0]+p[1]*2^8+p[2]*2^16+p[3]*2^24);
    int P1 = (p[4]+p[5]*2^8+p[6]*2^16+p[7]*2^24);
    int P2 = (p[8]+p[9]*2^8+p[10]*2^16+p[11]*2^24);
    int P3 = (p[12]+p[13]*2^8+p[14]*2^16+p[15]*2^24);



    public static int[] MDS(int X){
        byte[] x = intToByteArray(X);
        int[][] MDS = new int[][]{{0x01, 0xef, 0x5b, 0x5b},{0x5b, 0xef,0xef,0x01},{0xef,0x5b,0x01,0xef},{0xef,0x01,0xef,0x5b}};

        int[] x1  = new int[]{x[0], x[1], x[2], x[3]};
        int[] y = multiplayMatrix(MDS, x1);
        return y;
    }

    public static int[] multiplayMatrix(int[][] X, int[] Y){
        int[] C = new int[X.length];
        for (int i = 0; i<X.length;i++){
                for (int k = 0; k < Y.length; k++) {
                    C[i] += X[i][k] * Y[k];
                }
            }
        return C;
    }

    public static void main(String[] args) {
        byte[] p = new byte[]{75, 97, 121, 117};
        int P0 = (p[0]+p[1]*2^8+p[2]*2^16+p[3]*2^24);


        byte[] f = intToByteArray(P0);
        for (int i = 0; i < f.length; i++) {

            System.out.print(f[i] + " ");
        }
        System.out.println();


        byte bb = (byte)P0;
        System.out.println(bb);
        int i = ((p[3] & 0xFF) << 24) + ((p[2] & 0xFF) << 16) + ((p[1] & 0xFF) << 8) + (p[0] & 0xFF);
        byte g = (byte) i;
        System.out.println(P0);
        System.out.println(g);
    }
    public static final byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }
}
