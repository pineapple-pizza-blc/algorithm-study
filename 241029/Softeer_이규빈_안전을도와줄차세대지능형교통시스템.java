import java.io.*;
import java.util.*;

// 실행 시간 170 ms 메모리 21.04 MB
public class Softeer_이규빈_안전을도와줄차세대지능형교통시스템 {
    static int N, T;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        int[][][] map = new int[N+1][N+1][4];
        boolean[][] check = new boolean[N+1][N+1];
        for (int i = 0; i < N * N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++){
                map[i % N + 1][i / N + 1][j] = Integer.parseInt(st.nextToken());
            }
        }
        HashMap<Integer, int[]> dic = makeDic();
        ArrayDeque<int[]> deque = new ArrayDeque<>();

        int result = 0;
        deque.add(new int[] {1, 1, 0, 0});

        while(!deque.isEmpty()){
            int[] now = deque.pollFirst();
            int x = now[0];
            int y = now[1];
            int time = now[2];
            int dir = now[3];
            int[] signals = dic.get(map[x][y][time % 4]);
            
            if (!check[x][y]) {
                check[x][y] = true;
                result++;
            }

            if (signals[0] == dir){
                
                for (int i = 1; i < signals.length; i++){
                    int nextX = x + dx[signals[i]];
                    int nextY = y + dy[signals[i]];
                    if (1 <= nextX && nextX <= N && 1 <= nextY && nextY <= N && time + 1 <= T){
                        deque.add(new int[] {nextX, nextY, time+1, signals[i]});
                    }
                }
            }
        }
        System.out.println(result);
    }
    public static HashMap<Integer, int[]> makeDic(){
        HashMap<Integer, int[]> dic = new HashMap<>();
        dic.put(1, new int[] { 1, 0, 1, 2 });
        dic.put(5, new int[] { 1 ,0, 1});
        dic.put(9, new int[] { 1, 1, 2 });
        
        dic.put(2, new int[] {0,  3, 0, 1});
        dic.put(6, new int[] {0, 3, 0});
        dic.put(10, new int[] {0, 0, 1 });
        
        dic.put(3, new int[] { 3, 2, 3, 0});
        dic.put(7, new int[] {3, 2, 3});
        dic.put(11, new int[] {3, 3, 0 });
        
        dic.put(4, new int[] {2, 1, 2, 3});
        dic.put(8, new int[] {2, 1, 2});
        dic.put(12, new int[] {2, 2, 3 });
        
        return dic;

    }
}
