// 움직이는 미로 탈출 메모리 : 11500kb 시간 : 68ms 

// DFS
import java.io.*;

public class Main {
    static int[][] maze;
    static boolean[][][] visited;
    static final int MAZE_SIZE = 8;
    static int[] dr = {1, 1, 1, -1, -1, -1, 0, 0, 0};
    static int[] dc = {0, 1, -1, 0, 1, -1, 1, -1, 0};
    static int escape;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        maze = new int[MAZE_SIZE][MAZE_SIZE];
        for (int row = 0; row < MAZE_SIZE; row++) {
            String rows = br.readLine();
            for (int col = 0; col < MAZE_SIZE; col++) {
                if (rows.charAt(col) == '#') {
                    maze[row][col] = 1;
                }
            }
        }

        escape = 0;
        visited = new boolean [MAZE_SIZE][MAZE_SIZE][MAZE_SIZE];
        DFS(MAZE_SIZE - 1, 0, 0);
        System.out.println(escape);
    }

    private static void DFS(int row, int col, int turn) {
        if (escape == 1) return;
        if (turn >= MAZE_SIZE) {
            escape = 1;
            return;
        }
        if (visited[row][col][turn]) return;
        visited[row][col][turn] = true;

    private static boolean isRange(int row, int col) {
        return (0 <= row && row < MAZE_SIZE && 0 <= col && col < MAZE_SIZE);
    }

    private static boolean canEscape(int row, int col, int turn) {
        if (0 <= row - turn && maze[row - turn][col] == 1) return false;
        if (0 <= row - turn - 1 && maze[row - turn - 1][col] == 1) return false;
        return true;
    }
}

// BFS
import java.io.*;
import java.util.*;

public class Main {
    static final int MAZE_SIZE = 8;
    static final int START_ROW = MAZE_SIZE - 1;
    static final int START_COL = 0;
    static int[][] maze;
    static boolean[][] visited;
    static int[] dr = new int[] {1, 1, 1, -1, -1, -1, 0, 0, 0};
    static int[] dc = new int[] {1, 0, -1, 1, 0, -1, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        maze = new int[MAZE_SIZE][MAZE_SIZE];
        for (int row = 0; row < MAZE_SIZE; row++) {
            String rows = br.readLine();
            for (int col = 0; col < MAZE_SIZE; col++) {
                if (rows.charAt(col) == '#') {
                    maze[row][col] = 1;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {START_ROW, START_COL});
        int turn = 0;
        int answer = 1;
        while (!q.isEmpty()) {
            if (turn >= MAZE_SIZE) break;
            int size = q.size();
            visited = new boolean[MAZE_SIZE][MAZE_SIZE];
            for (int i = 0; i < size; i++) {
                int[] current = q.poll();
                int row = current[0];
                int col = current[1];
                for (int d = 0; d < 9; d++) {
                    int nr = row + dr[d];
                    int nc = col + dc[d];
                    if (isRange(nr, nc) && canMove(nr, nc, turn) && !visited[nr][nc]) {
                        q.add(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            turn++;
        }
        if (turn < MAZE_SIZE) answer = 0;
        System.out.println(answer);
    }

    private static boolean isRange(int row, int col) {
        return (0 <= row && row < MAZE_SIZE && 0 <= col && col < MAZE_SIZE);
    }

    private static boolean canMove(int row, int col, int turn) {
        return ((0 <= row - turn && maze[row - turn][col] == 0)
        && (0 <= row - 1 - turn && maze[row - 1 - turn][col] == 0));
    }
}
  