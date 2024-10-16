import java.io.*;
import java.util.*;

public class softeer_순서대로방문하기 {

    //실행 시간 70 ms 메모리 10.23 MB
    static int dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1}, N, M, ans, arr[][];
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        visited = new boolean[N][N];
        
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0;j<N;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken())-1, end = Integer.parseInt(st.nextToken())-1;
        arr[start][end] = 1;
        
        for(int i=2;i<=M;i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken())-1, y = Integer.parseInt(st.nextToken())-1;
            arr[x][y] = i;
        }

        int cnt = 0;
        ans = 0;

        visited[start][end] = true;
        dfs(start, end, 1);
        
        System.out.print(ans);
    }
    static void dfs(int x, int y, int cnt) {

        if(cnt == M) {
            ans++; return;
        }
        
        for(int i=0;i<4;i++) {
            int X = x+dx[i], Y = y+dy[i];
            if(IsInRange(X, Y) && !visited[X][Y]) {
                visited[X][Y] = true;
                if(arr[X][Y] == cnt+1) {
                    dfs(X, Y, cnt+1);
                } else if(arr[X][Y] == 0) {
                    dfs(X, Y, cnt);
                }
                visited[X][Y] = false;
            }
        }
    }

    static boolean IsInRange(int x, int y) {
        return 0 <= x & x < N && 0 <= y && y < N;
    }
}
