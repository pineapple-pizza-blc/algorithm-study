import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static class Node implements Comparable<Node> {
        int ed;
        int t;

        public Node(int ed, int t) {
            this.ed = ed;
            this.t = t;
        }

        @Override
        public int compareTo(Node o) {
            return this.t - o.t;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Node>[] list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, t));
            list[b].add(new Node(a, t));
        }

        int[] previous = new int[N + 1];
        int minResult = dijkstra(N, list, previous);

        int ans = -1;

        int currentNode = N;
        while (currentNode != 1) {
            int prevNode = previous[currentNode];

            int result = dijkstraWithSkip(N, list, prevNode, currentNode);
            if (result != Integer.MAX_VALUE) {
                int diff = result - minResult;
                ans = Math.max(ans, diff);
            } else {
            	ans = -1;
            	break;
            }
            currentNode = prevNode;
        }

        bw.write(ans + "\n");
        br.close();
        bw.close();
    }
    

    public static int dijkstra(int N, List<Node>[] list, int[] previous) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.ed;

            for (Node next : list[now]) {
                int cost = dist[now] + next.t;
                if (cost < dist[next.ed]) {
                    dist[next.ed] = cost;
                    pq.add(new Node(next.ed, cost));
                    previous[next.ed] = now;
                }
            }
        }
        return dist[N];
    }

    public static int dijkstraWithSkip(int N, List<Node>[] list, int skipSt, int skipEd) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.ed;

            for (Node next : list[now]) {
                if ((now == skipSt && next.ed == skipEd) || (now == skipEd && next.ed == skipSt)) continue;
                
                int cost = dist[now] + next.t;
                if (cost < dist[next.ed]) {
                    dist[next.ed] = cost;
                    pq.add(new Node(next.ed, cost));
                }
            }
        }
        return dist[N];
    }
}
