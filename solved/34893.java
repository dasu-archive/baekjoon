// https://www.acmicpc.net/problem/34893

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main_34893{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] uos = br.readLine().split(" ");
         long u = Long.parseLong(uos[0]);
         long o = Long.parseLong(uos[1]);
         long s = Long.parseLong(uos[2]);
         if ( u <= o && u <= s) System.out.println(u);
         else if ( o <= s && o <= u) System.out.println(o);
         else System.out.println(Math.min(o,(u + 2*s) / 3 ));

    }
}