import java.util.*;
import java.io.*;

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
    static Queue<Loc> wookjae = new ArrayDeque<>();
    static int dx[] = {0, -1, 0, 1, 0, 1, -1, 1, -1}, dy[] = {0, 0, 1, 0, -1, 1, 1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 8; i++) {
            arr[i] = bf.readLine().toCharArray();
        }

        wookjae.offer(new Loc(7, 0));

        while (!wookjae.isEmpty()) {
            Set<Loc> newWookjae = new HashSet<>();
            while (!wookjae.isEmpty()) {
                Loc wook = wookjae.poll();

                if (arr[wook.x][wook.y] == '#') continue; // 벽이 이미 이동한 후 해당 위치에 있을 경우

                if (wook.x == 0 && wook.y == 7) { // 목표 위치에 도달했을 때
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

            moveWalls(); // 벽 이동

            for (Loc wook : newWookjae) {
                if (arr[wook.x][wook.y] != '#') { // 이동 후 안전한 위치만 추가
                    wookjae.offer(wook);
                }
            }
        }

        System.out.println("0"); // 모든 경로가 막혔을 경우
    }

    static void moveWalls() {
        char[][] newArr = new char[8][8];
        for (int i = 0; i < 8; i++) {
            Arrays.fill(newArr[i], '.'); // 새로운 맵을 빈 공간으로 초기화
        }

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (arr[i][j] == '#') {
                    if (i < 7) {
                        newArr[i + 1][j] = '#'; // 벽 한 칸 아래로 이동
                    }
                }
            }
        }

        arr = newArr; // 벽 상태 갱신
    }

    static boolean isInRange(int x, int y) {
        return 0 <= x && x < 8 && 0 <= y && y < 8;
    }
}
