import java.util.*;
import java.io.*;

public class Boj_조수연_2307 {

    static class Node implements Comparable<Node> {

        int to, time;

        Node(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    static int n, m, res, ans;
    static int INF = 987654321;
    static int[] time;
    static boolean[] visited;
    static List<List<Node>> graph;
    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        res = Integer.MIN_VALUE;

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, t));
            graph.get(to).add(new Node(from, t));
        }

        for (int i = 2; i < n; i++) {
            visited = new boolean[n + 1];
            visited[i] = true;
            dijkstra(1);

            res = Math.max(res, time[n]);
        }

        visited = new boolean[n + 1];
        dijkstra(1);
        ans = time[n];

        if (res == INF) {
            System.out.println(-1);
        } else {
            System.out.println(res - ans);
        }

    }

    private static void dijkstra(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        visited[start] = true;
        time = new int[n + 1];
        Arrays.fill(time, INF);
        time[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            visited[now.to] = true;

            for (Node next : graph.get(now.to)) {

                if (!visited[next.to] && time[next.to] > time[now.to] + next.time) {
                    time[next.to] = time[now.to] + next.time;
                    pq.add(new Node(next.to, time[next.to]));
                }
            }
        }
    }
}
