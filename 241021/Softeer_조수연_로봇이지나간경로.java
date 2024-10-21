import java.io.*;

/**
 * 실행 시간 130 ms 메모리 13.97 MB
 */
public class Softeer_조수연_로봇이지나간경로 {
    static int dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1}, N, M, startX, startY, startDir;
    static char arr[][];
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String[] input = bf.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        arr = new char[N][M];

        for(int i=0;i<N;i++) {
            arr[i] = bf.readLine().toCharArray();
        }

        getStartPoint: for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(arr[i][j] == '#' && isStartPoint(i, j)) {
                    startX = i;
                    startY = j;
                    break getStartPoint;
                }
            }
        }

        sb.append(Integer.toString(startX+1)+" "+Integer.toString(startY+1)+"\n");
        switch (startDir) {
            case 0:
                sb.append("^\n");
                break;
            case 1:
                sb.append(">\n");
                break;
            case 2:
                sb.append("v\n");
                break;
            case 3:
                sb.append("<\n");
            default:
                break;
        }

        arr[startX][startY] = '.';
        dfs();
        System.out.println(sb);
    }

    private static void dfs() {
        for(int i=0;i<4;i++) {
            int x1 = startX + dx[i], y1 = startY + dy[i];
            int x2 = startX + dx[i]*2, y2 = startY + dy[i]*2;
            if(isInRange(x2, y2) && arr[x1][y1] == '#' && arr[x2][y2] == '#') {
                if (startDir == i) {
                    sb.append("A");
                    startX = x2;
                    startY = y2;
                    arr[x1][y1] = '.';
                    arr[x2][y2] = '.';
                    dfs();
                    return;
                } else if((i-startDir)%4 == 3 || (i-startDir) == -1) {
                    //좌회전
                    sb.append("L");
                    startDir = i;
                    dfs();
                    return;
                } else if((i-startDir)%4 == 1 || (i-startDir) == -3) {
                    sb.append("R");
                    startDir = i;
                    dfs();
                    return;
                }
            }
        }
    }

    private static boolean isStartPoint(int i, int j) {
        boolean check = false;
        for(int idx= 0;idx<4;idx++) {
            int x = i+dx[idx], y = j+dy[idx];
            if(isInRange(x, y) && arr[x][y] == '#') {
                if(check) return false;
                else {
                    check = true;
                    startDir = idx;
                }
            }
        }
        return true;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}