import java.io.*;
import java.util.*;

public class Main {

	static int result, n;

	static int[][] map;
	static boolean[][] visited;

	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException{
		//m개의 지점을 순서대로 방문
		//인접한 칸 상하좌우로 이동 가능
		//지났던 길은 가면 안된다. - 방문 처리

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//m개의 지점중 첫번째가 출발지 마지막이 도착지
		ArrayDeque<int[]> checkPoint = new ArrayDeque<>();

		for (int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			checkPoint.add(new int[] {x-1, y-1});
		}

		int[] now = checkPoint.pollFirst();

		result = 0;
		visited[now[0]][now[1]] = true;

		makeRoute(now[0], now[1], checkPoint);
		System.out.println(result);

	}

	public static void makeRoute(int x, int y, ArrayDeque<int[]> checkPoint){
		if (checkPoint.isEmpty()){
			result ++;
			return;
		}

		for (int i = 0; i < 4; i++){
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (0 <= nextX && nextX < n && 0 <= nextY && nextY < n && map[nextX][nextY] != 1 && !visited[nextX][nextY]) {
				visited[nextX][nextY] = true;
				int[] next = checkPoint.peek();
				if (next[0] == nextX && next[1] == nextY){
					next = checkPoint.pollFirst();
					makeRoute(nextX, nextY, checkPoint);
					checkPoint.addFirst(next);
				}
				else{
					makeRoute(nextX, nextY, checkPoint);
				}
				visited[nextX][nextY] = false;
			}
		}
	}

}
