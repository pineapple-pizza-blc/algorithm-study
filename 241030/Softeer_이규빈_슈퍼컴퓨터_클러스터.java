import java.io.*;
import java.util.*;

// 실행 시간 294 ms 메모리 22.78 MB
public class Softeer_이규빈_슈퍼컴퓨터_클러스터 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long[] computers = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            computers[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(computers);

        long start = 1;
        long end = 2000000000;
        long result = 0;

        while(start <= end){
            long mid = (start + end) / 2;

            long cost = 0;
            for (int i = 0; i < N; i++){
                if (mid > computers[i]) cost += (mid - computers[i]) * (mid - computers[i]);
                if (cost > B) break;
            }

            if (cost > B){
                end = mid - 1;
            }
            else{
                result = Math.max(result, mid);
                start = mid + 1;
            }
        }
        System.out.println(result);
    }
}
