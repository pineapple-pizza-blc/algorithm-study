import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static int n, m, str, stc, edr, edc;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		int[][] visited = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			visited[row][col] = i;
			if (i == 0) {
				str = row;
				stc = col;
				visited[str][stc] = -1;
			}
			if (i == m - 1) {
				edr = row;
				edc = col;
			}
		}

		dfs(str, stc, 1, visited);
		bw.write(ans + "");

		br.close();
		bw.close();
	}

	public static void dfs(int r, int c, int count, int[][] visited) {
		if (r == edr && c == edc && count == m) {
			ans++;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < n && nc >= 0 && nc < n && map[nr][nc] == 0) {
				if (visited[nr][nc] != -1) {
					if (visited[nr][nc] != 0) {
						if (count != visited[nr][nc]) {
							continue;
						}
						int temp = visited[nr][nc];
						visited[nr][nc] = -1;
						dfs(nr, nc, count + 1, visited);
						visited[nr][nc] = temp;
					} else {
						visited[nr][nc] = -1;
						dfs(nr, nc, count, visited);
						visited[nr][nc] = 0;
					}
				}
			}
		}
	}
}
