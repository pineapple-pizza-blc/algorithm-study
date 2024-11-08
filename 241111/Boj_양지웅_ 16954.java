// 움직이는 미로 탈출 메모리 : 12320kb 시간 : 132ms 
import java.io.*;

public class Main {
    static char[][] maze;
    static final int MAZE_SIZE = 8;
    static int[] dr = {1, 1, 1, -1, -1, -1, 0, 0, 0};
    static int[] dc = {0, 1, -1, 0, 1, -1, 1, -1, 0};

    static int escape;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        maze = new char[MAZE_SIZE][MAZE_SIZE];
        for (int row = 0; row < MAZE_SIZE; row++) {
            char[] line = br.readLine().toCharArray();
            for (int col = 0; col < MAZE_SIZE; col++) {
                maze[row][col] = line[col];
            }
        }

        escape = 0;
        DFS(7, 0, 0);
        System.out.println(escape);
    }

    private static void DFS(int row, int col, int turn) {
        if (turn >= MAZE_SIZE) {
            escape = 1;
            return;
        }

        for (int d = 0; d < dr.length; d++) {
            int nr = row + dr[d];
            int nc = col + dc[d];
            if (!isRange(nr, nc) || !canEscape(nr, nc, turn)) continue;
            DFS(nr, nc, turn + 1);
        }
    }

    private static boolean isRange(int row, int col) {
        return (0 <= row && row < MAZE_SIZE && 0 <= col && col < MAZE_SIZE);
    }

    private static boolean canEscape(int row, int col, int turn) {
        if (0 <= row - turn && maze[row - turn][col] == '#') return false;
        if (0 <= row - turn - 1 && maze[row - turn - 1][col] == '#') return false;
        return true;
    }
}
