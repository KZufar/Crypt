public class Task_2 {
    public static void main(String[] args) {
        System.out.println(AlgoritmEvklida(12, 14));
        System.out.println(QuickExponentiation(2, 5, 100));
        for (int i = 0; i<3; i++){
            System.out.println(AdvancedAlgoritmEuclid(18, 12, 0, 0)[i]);
        }
        for (int i = 0; i<3; i++){
            System.out.println(AdvancedAlgoritmEuclid(91, 17, 0, 1)[i]);
        }
        System.out.println(reverseElement(7, 3, 0, 1));
        System.out.printf(String.valueOf(isPrime(17, 12)));
    }

    public static int mod(int a, int b){
        int m = 0;
        if (a<b){
            m = a-b;
            return m;
        }else {
            m = a%b;
            return m;
        }
    }
    public static int AlgoritmEvklida(int a, int b){
        a = Math.abs(a);
        b = Math.abs(b);
        int NOD;
        while(a>0 & b>0){
            if (a>b){
                a = a%b;
            }else{
                b = b%a;
            }
        }
        NOD=a+b;
        return NOD;
    }
    public static int[] AdvancedAlgoritmEuclid(int a, int b, int x, int y){
        int[] d = new int[3];
        if (b==0){
            d[0] = a;
            d[1]=1;
            d[2]=0;
            return d;
        }
        int x2 = 1, x1 = 0, y2 = 0, y1 = 1, q, r;
        while(b>0){
            q = a/b;
            r = a-q*b;
            x = x2-q*x1;
            y = y2-q*y1;
            a = b;
            b = r;
            x2 = x1;
            x1 = x;
            y2 = y1;
            y1 = y;
        }
        d[0] = a;
        d[1] = x2;
        d[2] = y2;
        return d;
    }
    public static int QuickExponentiation(int a, int x, int n){
        int y = 1;
        /*for (int i = 0; i < x; i++){ %медленный алгоритм
            y = (y*a)%n;
        }
        return y;*/

        while (x>0){
            if (x%2!=0) {
                y = (y * a) % n;
            }
                a = (a*a)%n;
                x = x/2;
        }
        return y;
    }
    public static int reverseElement(int d, int m, int y, int x){
        int[] dxy = new int[3];
        for (int i =0; i<3; i++) {
            dxy[i] = AdvancedAlgoritmEuclid(d, m, y, x)[i];
        }
        int r = 0;
        while(dxy[2] != mod(r, d)){
            r++;
        }
       /* d*dxy[2]=
        int r = m%d;
        int q, z;
        while (r>0){
            q = m/d;
            z = (dxy[2]+(m-((q*dxy[1])%m)))%m;
            m = r%d;
            dxy[2] = z%dxy[1];
            r = m%d;
        }*/
        return r;
    }
    public static boolean isPrime(int n, int k){
        int s=0, t;
        int g = n-1;
        while((g%2==0)){
            g = g/2;
            s++;
        }
        t = g;

        int x = 0, y=0;
        for (int i = 0; i<k; i++) {
            x = QuickExponentiation(i + 2, t, n);
            if (x == 1 || x == n - 1) {
                continue;
            }else {
                return false;
            }
//            for (int j = 0; j < s; j++) {
//                x = QuickExponentiation(x, 2, n);
//                if (x == 1 & ) {
//                    return false;
//                } else if (x != n - 1) {
//                    break;
//                }
//            }
        }
        return true;
    }
}
