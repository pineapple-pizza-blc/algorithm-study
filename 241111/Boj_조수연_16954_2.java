import java.io.*;
import java.util.*;

public class Boj_조수연_16954 {
    static class Loc {
        int x, y;

        Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Loc loc = (Loc) obj;
            return x == loc.x && y == loc.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static char arr[][] = new char[8][8];
    static Queue<Loc> walls = new ArrayDeque<>(), wookjae = new ArrayDeque<>();
    static int dx[] = {0, -1, 0, 1, 0, 1, -1, 1, -1}, dy[] = {0, 0, 1, 0, -1, 1, 1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 8; i++) {
            arr[i] = bf.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                if (arr[i][j] == '#') {
                    walls.offer(new Loc(i, j));
                }
            }
        }
    
        wookjae.offer(new Loc(7, 0));
    
        while (!wookjae.isEmpty()) {
            Set<Loc> newWookjae = new HashSet<>();
            while (!wookjae.isEmpty()) {
                Loc wook = wookjae.poll();
    
                if (arr[wook.x][wook.y] == '#') continue; // 벽이 이동한 후 체크
    
                if (wook.x == 0 && wook.y == 7) {
                    System.out.println("1");
                    return;
                }
    
                for (int i = 0; i < 9; i++) {
                    int x = wook.x + dx[i], y = wook.y + dy[i];
                    if (isInRange(x, y) && arr[x][y] != '#') {
                        newWookjae.add(new Loc(x, y));
                    }
                }
            }
    
            moveWalls();
    
            for (Loc wook : newWookjae) {
                if (arr[wook.x][wook.y] != '#') { // 벽이 내려온 후에도 안전한 위치만 추가
                    wookjae.offer(wook);
                }
            }
        }
    
        System.out.println("0");
    }

    static void moveWalls() {
        Queue<Loc> newWalls = new ArrayDeque<>();
        while(walls.isEmpty()) {
            Loc wall = walls.poll();
            arr[wall.x][wall.y] = '.'; // 이전 위치는 빈 칸으로

            if (isInRange(wall.x + 1, wall.y)) {
                newWalls.add(new Loc(wall.x + 1, wall.y));
                arr[wall.x + 1][wall.y] = '#'; // 새로운 위치를 벽으로 갱신
            }
        }
        walls = newWalls;
    }

    static boolean isInRange(int x, int y) {
        return 0 <= x && x < 8 && 0 <= y && y < 8;
    }
}
