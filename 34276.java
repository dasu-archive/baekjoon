import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * T = 트랙 거리
 * T(i) 는 현재 좌표 위치
 * D(i) 는 앞 순서와의 거리 차이

 * 로직
 1. 각 위치를 좌표로 표시
    T(i) = ( T(i-1) + D(i) ) / T
 2. 구한 위치를 크기 순으로 정렬
 3. DRS 가 가능한 드라이버 구하기 ( 맨 첫번째는 제외 ( i > 0 ) )
    T(i) - T(i-1) > 0 && T(i) - T(i-1) <= 1000
 4. 맨 첫번째값이 가능한지 구하기 ( i = 0, n = 전체 수  )
    T - T(n-1) > 0 && T - T(n-1) <= 1000
 5. 사전순으로 정렬
*/
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nt = br.readLine().split(" ");
        int n = Integer.parseInt(nt[0]);
        int t = Integer.parseInt(nt[1]);

        Map<String, Integer> dMap = new HashMap<>();
        List<String> keys = new ArrayList<>();

        int tNow = 0;
        //  1. 각 위치를 좌표로 표시
        for(int i = 0 ; i < n ; i++){
            String[] td = br.readLine().split(" ");
            String dNow = td[0];
            tNow = (tNow + Integer.parseInt(td[1])) % t;
            dMap.put(dNow, tNow);
            keys.add(dNow);
        }

        // 2. 구한 위치를 크기 순으로 정렬
        List<String> rankKeys = new ArrayList<>();
        dMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .thenComparing(Map.Entry.comparingByKey()))
                .forEach(entry -> rankKeys.add(entry.getKey()));

                
        List<String> drsList = new ArrayList<>();
        
        // 3. DRS 가 가능한 드라이버 구하기 ( 맨 첫번째는 제외 ( i > 0 ) ) 
        // T(i) - T(i-1) > 0 && T(i) - T(i-1) <= 1000
        for(int i = 1; i <  n; i++){
            String driverNow = rankKeys.get(i);
            int trackNow = dMap.get(driverNow);
            String driverFront = rankKeys.get(i - 1);
            int trackFront =  dMap.get(driverFront);

            // System.out.println(driverNow + " " + trackNow + " : " + driverNext + " " + trackNext);

            if (trackNow - trackFront > 0 && trackNow - trackFront <= 1000)
                drsList.add(rankKeys.get(i));
        }
        
        //  4. 맨 첫번째값이 가능한지 구하기 ( i = 0, n = 전체 수  )
        // T - T(n-1) > 0 && T - T(n-1) <= 1000
        if(t - dMap.get(rankKeys.get(n-1)) > 0 && t - dMap.get(rankKeys.get(n-1)) <= 1000){
            drsList.add(rankKeys.get(0));
        }

        // 5. 정렬
        if (drsList.size() == 0) {
            System.out.print(-1);
        }

        Collections.sort(drsList);
        
        for (String drs : drsList) {
            System.out.print(drs + " ");
        }
    }
}