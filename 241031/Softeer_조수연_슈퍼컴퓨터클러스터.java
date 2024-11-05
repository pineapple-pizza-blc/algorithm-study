import java.util.*;
import java.io.*;

/**
 * Softeer_조수연_슈퍼컴퓨터클러스터
 * 실행 시간 481 ms
 * 메모리 51.55 MB
 */
public class Softeer_조수연_슈퍼컴퓨터클러스터 {
    static Long b, arr[];

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());
        arr = new Long[N];
        
        st = new StringTokenizer(bf.readLine());

        for(int i=0;i<N;i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        Long left = arr[0], right = arr[N-1] + (long) Math.sqrt(b);
        Long answer = 0L;
        while(left <= right) {

            Long mid = (left + right) / 2;

            if(calculate(mid)) {
                left = mid+1;
                answer = mid;
            } else {
                right = mid-1;
            }

        }

        System.out.println(answer);

    }

    public static boolean calculate(Long min) {
        Long cost = 0L;
        for(Long num : arr) {
            if(num < min) {
                cost += (min - num) * (min - num);
                if(cost > b) return false;
            }
        }
        return true;
    }
}