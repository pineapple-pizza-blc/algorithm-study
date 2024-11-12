// 도로 검문 메모리 : 53761kb, 시간 : 548ms

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, minDist, answer;
    static List<int[]>[] adj;
    static int[] dist, prev;
    static List<int[]> routes;
  
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N + 1];
        for (int n = 1; n < N + 1; n++) {
            adj[n] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            adj[from].add(new int[] {to, time});
            adj[to].add(new int[] {from, time});
        }

        // 최단 거리 비용
        prev = new int[N + 1];
        minDist = dijk(1, N);
        if (minDist == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        // 최단 거리 루트
        routes = new ArrayList<>();
        for (int i = N; i > 1; i = prev[i]) {
            routes.add(new int[]{prev[i], i});
        }
        // 최단 거리 루트에서 길을 하나씩 빼보면서 비교
        answer = 0;
        for (int[] route : routes) {
            int from = route[0];
            int to = route[1];
            int dist = dijk(from, to);
            if (dist == Integer.MAX_VALUE) {
                System.out.println(-1);
                return;
            }
            answer = Math.max(answer, dist - minDist);
        }

        System.out.println(answer);
    }

    private static int dijk(int from, int to) {
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] {1, 0});

        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int cn = current[0];
            int ct = current[1];
            if (dist[cn] < ct) continue;
            for (int[] next : adj[cn]) {
                int nn = next[0];
                int time = ct + next[1];
                if (dist[nn] > time && !(cn == from && nn == to)) {
                    dist[nn] = time;
                    prev[nn] = cn;
                    pq.add(new int[] { nn, time});
                }
            }
        }
        return dist[N];
    }
}
