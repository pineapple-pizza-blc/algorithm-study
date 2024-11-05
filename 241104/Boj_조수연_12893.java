import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_조수연_12893 {
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

        for(int i=0;i<N;i++) {
            if(visited[i]) continue;

            Queue<Integer> queue = new ArrayDeque<>();
            visited[i] = true;

            for(int j=0;j<arr[i].size();j++) {
                queue.offer(arr[i].get(j));
            }

            while(!queue.isEmpty()) {
                
            }
        }

    }

    static void dfs(int num) {
        boolean team = true;
        for(int tar : arr[num]) {
            if(!visited[tar]) {
                visited[tar] = true;
                
            }
        }
    }
}
