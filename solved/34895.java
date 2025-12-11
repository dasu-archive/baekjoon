// https://www.acmicpc.net/problem/34895

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main_34895 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = parseInt(br.readLine());
        String binary = br.readLine();

        Long i = 1L;
        String next = Long.toBinaryString(i);
        while (true) {

            if (binary.indexOf(next) < 0) {
                break;
            } else {
                next = Long.toBinaryString(++i);
            }
        }
        System.out.println(next);
    }
}