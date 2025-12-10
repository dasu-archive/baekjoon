// https://www.acmicpc.net/problem/34894


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

class Main_34894{
    static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 0. 문자 리스트 준비
        String[] uospc = {"U","O","S","P","C"};

        // 1. 개수 읽기
        int n = Integer.parseInt(br.readLine());
        // 2. 문자열 읽기
        String[] s = br.readLine().split("");
        // 3. 비용 읽기
        String[] value = br.readLine().split(" ");
        int[] v = new int[value.length];
        for (int i = 0; i < value.length; i++) {
            v[i] = Integer.parseInt(value[i]);
        }
        // 4. dp 준비
        long[][] dp = new long[uospc.length][n + 1];
        for ( int j = 0; j < 5; j++){
            dp[j][0] = Long.MAX_VALUE;
        }


        for(int j = 0 ; j < 5; j++){
            for(int i = 1; i <= n ; i++){
                // 지금 값이 찾는 문자와 같지 않으면 이전 값 복사
                if(! Objects.equals(uospc[j], s[i-1])) dp[j][i] = dp[j][i-1];
                // 찾는 값이면 로직 실행
                else {
                    // 첫번째 줄 U 초기화
                    if ( j == 0 )
                        dp[j][i] = Math.min(dp[j][i-1], v[i-1]);
                    // 두번째 이상 UO, UOS, UOSP, UOSPC 초기화
                    else {
                        dp[j][i] = Math.min( ( dp[j-1][i-1] + v[i-1] > 0 ?  dp[j-1][i-1] + v[i-1] : Long.MAX_VALUE)  , dp[j][i-1] );
                    }
                }

            }
        }
        if(dp[uospc.length-1][n] == Long.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(dp[uospc.length-1][n]);
        }
    }

}




