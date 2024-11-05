// 적의적

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static List<List<Integer>> adj;
    static int[] colors;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adj.get(A).add(B);
            adj.get(B).add(A);
        }

        colors = new int[N + 1]; // 0 : 비할당, -1 : GroupA, 1 : GroupB
        for (int i = 0; i < N + 1; i++) {
            if (colors[i] == 0) {
                if (!isBipartite(i)) {
                    System.out.println(0);
                    return;
                }
            }
        }

        System.out.println(1);
    }

    private static boolean isBipartite(int i) {
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        colors[i] = 1;
        while (!q.isEmpty()) {
            int current = q.poll();
            for (int neighbor : adj.get(current)) {
                if (colors[neighbor] == 0) {
                    colors[neighbor] = colors[current] * -1;
                    q.add(neighbor);
                } else if (colors[neighbor] == colors[current]) {
                    return false;
                }
            }
        }
        return true;
    }
}
