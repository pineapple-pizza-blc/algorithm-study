import java.io.*;
import java.util.*;

//실행 시간 463 ms
//메모리 36.36 MB
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		int[] cars = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++){
			cars[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(cars);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++){
			int target = Integer.parseInt(br.readLine());
			int start = 0;
			int end = n-1;
			int index = -1;
			while( start <= end ){
				int mid = (start + end) / 2;

				if (cars[mid] > target){
					end = mid - 1;
				}
				else if (cars[mid] == target){
					index = mid;
					break;
				}
				else{
					start = mid + 1;
				}
			}
			if (index != -1){
				sb.append(index * (n - index -1)).append("\n");
			}
			else{
				sb.append(0).append("\n");
			}
		}
		System.out.println(sb);
	}
}