import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Softeer_조수연_안전운전을하지못했어요 {
    static class Loc {
        int x, y;

        Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static HashSet<Integer> ans = new HashSet<>();

    static int dx[] = {1, 0, -1, 0}, dy[] = {0, 1, 0, -1}, N, T, doro[][][];
    static Loc[][] sign = new Loc[12][];
    static int[][] dirIdx = new int[12][];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        //(신호+1)%4 => 0 -> 하, 1 -> 우, 2 -> 상, 3 -> 좌

        for(int i=0;i<4;i++) {
            dirIdx[i] = new int[3];
            for(int j=0;j<3;j++) {
                dirIdx[i][j] = (2+i)-j;
            }
        }

        //2 1 0, 3 2 1, 

        doro = new int[N][N][4];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                st = new StringTokenizer(bf.readLine());
                for(int k=0;k<4;k++) {
                    doro[i][j][k] = Integer.parseInt(st.nextToken())-1;
                }
            }
        }

        bfs();
        System.out.println(ans.size());
    }

    private static void bfs() {
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(0, 0, 0, 2));
        
        while (!queue.isEmpty()) {
            State state = queue.poll();

            int signal = doro[state.x][state.y][state.time];
            // System.out.println(signal);
            if((signal+1)%4 != state.dir) continue;

            ans.add(state.x*100 + state.y);
            
            if(state.time == T) continue;

            

            // if(signal < 4) {
            //     //세 방향
            //     for(int i=0;i<4;i++) {
            //         int nextX = state.x+dx[i], nextY = state.y+dy[i];
            //         if(isInRange(nextX, nextY) && i != (state.dir+2)%4) {
            //             queue.add(new State(nextX, nextY, state.time+1, i));
            //         }
            //     }

            // } else if(signal < 8) {
            //     //dir 기준 dir - 1 빼고
            //     for(int i=0;i<4;i++) {
            //         int nextX = state.x+dx[i], nextY = state.y+dy[i];
            //         if(isInRange(nextX, nextY) && i != (state.dir-1+4)%4 && i != (state.dir+2)%4) {
            //             queue.add(new State(nextX, nextY, state.time+1, i));
            //         }
            //     }
                
            // } else {
            //     for(int i=0;i<4;i++) {
            //         int nextX = state.x+dx[i], nextY = state.y+dy[i];
            //         if(isInRange(nextX, nextY) && i != (state.dir+1+4)%4 && i != (state.dir+2)%4) {
            //             queue.add(new State(nextX, nextY, state.time+1, i));
            //         }
            //     }
            // }

        }
    }

    static class State extends Loc{
        State(int x, int y, int time, int dir) {
            super(x, y);
            this.time = time;
            this.dir = dir;
        }

        int time, dir;

    }

    static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}