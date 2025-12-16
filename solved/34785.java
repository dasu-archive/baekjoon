// https://www.acmicpc.net/problem/34875

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
* deg(u) = u의 이웃중 2개 선택
* 내가 구하려던 것 = C(deg(u)-1, 2) × C(deg(v)-1, 2)
* */
class Main_34785{

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long count = 0L;
        List<Integer>[] graph = new ArrayList[n + 1];
        // 배열 초기화
        for(int i = 1; i <=n; i++){
            graph[i] = new ArrayList<>();
        }
        // 간선 개수는 n-1 개.
        for(int i = 1; i < n; i++){
            String[] uv = br.readLine().split(" ");
            int u = Integer.parseInt(uv[0]);
            int v = Integer.parseInt(uv[1]);

            // 리스트에 더해주기
            graph[u].add(v);
            graph[v].add(u);
        }

        for(int u = 1; u <= n; u++){
            //다음 번호들
            List<Integer> uNext = graph[u];
            if (uNext.size() <= 2) continue;

            for (int v : uNext) {
                if (v <= u) continue;

                List<Integer> vNext = graph[v];
                if (vNext.size() <= 2) continue;
                int uSize = uNext.size() - 1;
                int vSize = vNext.size() - 1;

                count += ( ( long ) uSize * (uSize - 1) / 2)
                        * ( ( long ) vSize * (vSize - 1) / 2);
            }

        }
        System.out.println(count);
    }
//    // 50점짜리 정답. 메모리, 시간이 너무 많이 잡아먹음
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int n = Integer.parseInt(br.readLine());
//        int count = 0;
//        Map<Integer, List<Integer>> nodeList = new HashMap<>();
//        for(int i = 0; i < n-1; i++){
//            String[] uv = br.readLine().split(" ");
//            int u = Integer.parseInt(uv[0]);
//            int v = Integer.parseInt(uv[1]);
//
//            // 리스트에 더해주기
//            nodeList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
//            nodeList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
//        }
//
//        for(Integer i : nodeList.keySet()){
//            //다음 번호들
//            List<Integer> uNext = nodeList.get(i);
//            List<Integer> vList = uNext.stream().filter(x -> x > i).collect(Collectors.toList());
//            // 1. 내가 u임을 를 가정
//            if(uNext.size() > 2 && !vList.isEmpty()){
//                // 2. v 찾기
//                for(Integer v : vList ){
//                    List<Integer> numberList = new ArrayList<>();
//                    List<Integer> vNext = nodeList.get(v);
//
//                    // v 찾음
//                    if(vNext.size() > 2 ){
//                        Set<Integer> vSet = new HashSet<>(vNext);
//
//                        int commonSize = 0;
//
//                        for(int x : uNext){
//                            if(vSet.contains(x)) commonSize++;
//                        }
//
//                        int uNextSize = uNext.size() - 1;
//                        int vNextSize = vNext.size() - 1;
//                        // 식 = uNext의 조합 * vNext 의 조합 - 공통갯수 * ( uNext.size() -1 * vNext.size() -1 )
//                        count += ((( uNextSize * (uNextSize - 1))/2) * ((vNextSize * (vNextSize -1))/2))
//                                - (commonSize * ((uNextSize- 1) * (vNextSize -1)));
//                    }
//                }
//
//            }
//
//        }
//        System.out.println(count);
//    }
}