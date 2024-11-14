package solved.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_이규빈_2307 {
	static int maxTime, minTime, N;
	static ArrayList<int[]> selected;
	static int[] preNode;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());


		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int k = 0; k < N + 1; k++) graph.add(new ArrayList<>());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new int[]{b, c, 0});
			graph.get(b).add(new int[]{a, c, 0});
		}

		maxTime = 0;
		minTime = Integer.MAX_VALUE;

		selected = new ArrayList<>();
		selected.add(new int[] {0,0});
		preNode = new int[N+1];
		dijkstar(graph, 0);

		int now = N;
		while (true){
			if (now == 1) break;
			int pre = preNode[now];
			selected.add(new int[] {now, pre});
			selected.add(new int[] {pre, now});
			now = pre;
		}

		for (int i = 1; i < selected.size(); i++){
			dijkstar(graph, i);
		}

		if (maxTime == Integer.MAX_VALUE) System.out.println(-1);
		else if (maxTime == minTime) System.out.println(0);
		else System.out.println(maxTime - minTime);
	}

	public static void dijkstar(ArrayList<ArrayList<int[]>> graph, int target) {
		PriorityQueue<int[]> heapq = new PriorityQueue<>((int[] o1, int[] o2) -> {
			return o1[1] - o2[1];
		});
		heapq.add(new int[]{1, 0});

		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;

		int[] select = selected.get(target);

		while (!heapq.isEmpty()) {
			int[] now = heapq.poll();

			for (int[] next : graph.get(now[0])) {
				int newCost = next[1] + now[1];
				if (dist[next[0]] > newCost && !(now[0] == select[0] && next[0] == select[1])) {
					dist[next[0]] = newCost;
					preNode[next[0]] = now[0];
					heapq.add(new int[]{next[0], newCost});
				}
			}
		}

		// System.out.println(dist[N]);
		minTime = Math.min(minTime, dist[N]);
		maxTime = Math.max(maxTime, dist[N]);
	}
}
