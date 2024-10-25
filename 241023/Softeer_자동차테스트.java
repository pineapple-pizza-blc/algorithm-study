import java.io.*;
import java.util.*;

public class Softeer_자동차테스트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];

        HashMap<Integer, Integer> map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i =1; i<=n;i++){
            arr[i] = Integer.parseInt(st.nextToken()); // 배열에 값 입력
        }

        Arrays.sort(arr);
        for (int i=2; i<=n-1; i++) {
            map.put(arr[i], (i-1) * (n-i));
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<q; i++) {
            int ans = Integer.parseInt(br.readLine());
            if (map.containsKey(ans)) {
                sb.append(map.get(ans) + "\n");
            } else {
                sb.append("0\n");
            }
        }

        bw.write(sb+"");



        br.close();
        bw.close();
    }
}