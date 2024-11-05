import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_조수연_12893 {
    static int N, M;
    static boolean visited[], team[];
    static List<Integer> arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N];
        visited = new boolean[N];
        team = new boolean[N];

        for(int i=0;i<N;i++) {
            arr[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken())-1, b = Integer.parseInt(st.nextToken())-1;
            arr[a].add(b);
            arr[b].add(a);
        }

        // 모든 정점에 대해 확인
        for(int i=0;i<N;i++) {
            if(!visited[i]) {
                if(!bfs(i)) {
                    System.out.println(0);
                    return;
                }
            }
        }
        System.out.println(1);
    }

    // BFS로 이분 그래프 판별
    static boolean bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        team[start] = true;  // 시작 정점의 팀을 true로 설정

        while(!queue.isEmpty()) {
            int current = queue.poll();
            
            for(int next : arr[current]) {
                if(!visited[next]) {
                    visited[next] = true;
                    team[next] = !team[current];  // 현재 정점과 반대 팀으로 설정
                    queue.offer(next);
                }
                // 이미 방문한 정점인 경우, 같은 팀에 속해있다면 불가능
                else if(team[next] == team[current]) {
                    return false;
                }
            }
        }
        return true;
    }
}