package solved.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P16954 {

    static final int[] dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static final int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드 정보 저장
        map = new char[8][8];
        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
        }

        ArrayDeque<int[]> walls = new ArrayDeque<>();

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == '#') walls.add(new int[]{i, j});
            }
        }

        ArrayDeque<int[]> moves = new ArrayDeque<>();
        moves.add(new int[]{7, 0});

        int result = 0;

        while (!moves.isEmpty()) {
            int size = moves.size();
            boolean[][] visited = new boolean[8][8];

            for (int s = 0; s < size; s++) {
                int[] now = moves.pollFirst();
                if (now[0] == 0 && now[1] == 7) {
                    result = 1;
                    moves.clear();
                    break;
                }

                for (int i = 0; i < 9; i++) {
                    int nextX = now[0] + dx[i];
                    int nextY = now[1] + dy[i];

                    if (checkRange(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] == '.') {
                        visited[nextX][nextY] = true;
                        moves.add(new int[]{nextX, nextY});
                    }
                }
            }

            if (result == 1) break;

            int wallSize = walls.size();
            for (int i = 0; i < wallSize; i++) {
                int[] nowWall = walls.pollFirst();
                int nextX = nowWall[0] + 1;
                int nextY = nowWall[1];
                map[nowWall[0]][nowWall[1]] = '.';
                if (checkRange(nextX, nextY)) {
                    map[nextX][nextY] = '#';
                    walls.add(new int[]{nextX, nextY});
                }
            }
            
            size = moves.size();
            for (int i = 0; i < size; i++) {
                int[] newMove = moves.pollFirst();
                if (map[newMove[0]][newMove[1]] == '.') {
                    moves.addLast(newMove);
                }
            }
        }
        System.out.println(result);
    }

    public static boolean checkRange(int nextX, int nextY) {
        return 0 <= nextX && nextX < 8 && 0 <= nextY && nextY < 8;
    }
}