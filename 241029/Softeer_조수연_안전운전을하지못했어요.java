import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Softeer_조수연_안전운전을하지못했어요 {
    static int n, t;
    static boolean[][] check;
    static int[][][] signals;
    static Set<String> answer = new HashSet<>();
    
    static int[][] directions = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1} // up, down, left, right
    };
    
    static Map<Integer, int[][]> traffic = new HashMap<>() {{
        put(1, new int[][] {{0, 1}, {-1, 0}, {0, 1}, {1, 0}});
        put(2, new int[][] {{-1, 0}, {0, -1}, {-1, 0}, {0, 1}});
        put(3, new int[][] {{0, -1}, {-1, 0}, {0, -1}, {1, 0}});
        put(4, new int[][] {{1, 0}, {0, 1}, {1, 0}, {0, -1}});
        put(5, new int[][] {{0, 1}, {-1, 0}, {0, 1}});
        put(6, new int[][] {{-1, 0}, {0, -1}, {-1, 0}});
        put(7, new int[][] {{0, -1}, {0, -1}, {1, 0}});
        put(8, new int[][] {{1, 0}, {1, 0}, {0, 1}});
        put(9, new int[][] {{0, 1}, {0, 1}, {1, 0}});
        put(10, new int[][] {{-1, 0}, {-1, 0}, {0, 1}});
        put(11, new int[][] {{0, -1}, {-1, 0}, {0, -1}});
        put(12, new int[][] {{1, 0}, {0, -1}, {1, 0}});
    }};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        t = Integer.parseInt(input[1]);

        check = new boolean[n + 1][n + 1];
        signals = new int[n + 1][n + 1][4];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                String[] line = br.readLine().split(" ");
                for (int k = 0; k < 4; k++) {
                    signals[i][j][k] = Integer.parseInt(line[k]);
                }
            }
        }

        bfs();
        System.out.println(answer.size());
    }

    public static void bfs() {
        Queue<State> q = new LinkedList<>();
        q.offer(new State(1, 1, -1, 0, 0));
        
        while (!q.isEmpty()) {
            State state = q.poll();
            int x = state.x;
            int y = state.y;
            int px = state.px;
            int py = state.py;
            int time = state.time;
            answer.add(x + "," + y);
            int nowSignal = signals[x][y][time % 4];
            int[][] moves = traffic.get(nowSignal);

            if (px != moves[0][0] || py != moves[0][1]) continue;

            for (int i = 1; i < moves.length; i++) {
                int nx = x + moves[i][0];
                int ny = y + moves[i][1];

                if (1 <= nx && nx <= n && 1 <= ny && ny <= n) {
                    if (!check[nx][ny] && time < t) {
                        check[nx][ny] = true;
                        q.offer(new State(nx, ny, moves[i][0], moves[i][1], time + 1));
                    }
                }
            }
        }
    }

    static class State {
        int x, y, px, py, time;
        
        State(int x, int y, int px, int py, int time) {
            this.x = x;
            this.y = y;
            this.px = px;
            this.py = py;
            this.time = time;
        }
    }
}