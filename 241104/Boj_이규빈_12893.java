package solved.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_이규빈_12893 {

    static int N, M;
    static int[] visited;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        visited = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            if (visited[i] == 0) {
                if (!bfs(i)) {
                    System.out.println(0);
                    return;
                }
            }
        }
        System.out.println(1);
    }

    public static boolean bfs(int num) {
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{num, 1});

        boolean flag = true;
        while (!deque.isEmpty()) {
            int[] now = deque.pollFirst();

            for (int next : graph.get(now[0])) {
                // 방문 하지 않았다면 현재의 값을 반전해서 대입
                if (visited[next] == 0) {
                    visited[next] = now[1] * -1;
                    deque.add(new int[]{next, now[1] * -1});
                }
                // 방문 했던 곳이라면 현재의 값가 달라야 한다.
                else if (visited[next] != 0 && visited[next] == now[1]) flag = false;
            }
        }

        // System.out.println(flag);
        return flag;
    }
}
