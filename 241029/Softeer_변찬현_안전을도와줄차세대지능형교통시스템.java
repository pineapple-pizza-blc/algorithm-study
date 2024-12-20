import java.io.*;
import java.util.*;

/*
실행 시간
255 ms
메모리
28.16 MB
*/

public class Softeer_변찬현_안전을도와줄차세대지능형교통시스템 {
    static int N, T;
    static int[][][] map;
    static boolean[][] visited;
    static int[][] moves = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][N][4];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        int answer = bfs();
        bw.write(answer + "\n");

        br.close();
        bw.close();
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1, 0});
        int count = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int dir = current[2];
            int time = current[3];

            if (!visited[x][y]) {
                visited[x][y] = true;
                count++;
            }

            if (time >= T || map[x][y][time % 4] % 4 != dir) continue;

            if (isRange(x + moves[dir][0], y + moves[dir][1])) {
                queue.add(new int[]{x + moves[dir][0], y + moves[dir][1], dir, time + 1});
            }

            if (map[x][y][time % 4] / 4 != 1 && isRange(x + moves[(dir + 3) % 4][0], y + moves[(dir + 3) % 4][1])) {
                queue.add(new int[]{x + moves[(dir + 3) % 4][0], y + moves[(dir + 3) % 4][1], (dir + 3) % 4, time + 1});
            }

            if (map[x][y][time % 4] / 4 != 2 && isRange(x + moves[(dir + 1) % 4][0], y + moves[(dir + 1) % 4][1])) {
                queue.add(new int[]{x + moves[(dir + 1) % 4][0], y + moves[(dir + 1) % 4][1], (dir + 1) % 4, time + 1});
            }
        }

        return count;
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
