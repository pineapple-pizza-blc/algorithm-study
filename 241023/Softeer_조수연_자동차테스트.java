import java.io.*;  
import java.util.*;  
  
public class Softeer_조수연_자동차테스트 {  
  
    static int[] arr;  
  
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()), q = Integer.parseInt(st.nextToken());  
        arr = new int[n];
        
        st = new StringTokenizer(bf.readLine());
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);  
  
        for (int i = 0; i < q; i++) {
            int idx = binarySearch(Integer.parseInt(bf.readLine()));  

            if (idx > 0) {
                sb.append((idx*(n-idx-1))).append("\n");
            } else {
                sb.append("0\n");
            }
        }
        System.out.print(sb);
    }  
  
    private static int binarySearch(int value) {  
        int start = 0;  
        int end = arr.length - 1;  
  
        while (start <= end) {
            int mid = start + (end - start) / 2;  
            
            if (arr[mid] == value) {  
                return mid;
            } else if (arr[mid] < value) {  
                start = mid + 1;  
            } else {  
                end = mid - 1;  
            }  
        }
        return -1;  
    }  
}