import java.io.*;
import java.util.*;

//실행 시간 458 ms
//메모리 44.66 MB
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

		HashMap<Integer, Integer> indexes = new HashMap<>();
		for (int i = 0; i < n; i++){
			indexes.put(cars[i], i);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++){
			int target = Integer.parseInt(br.readLine());

			if (indexes.containsKey(target)){
				int index = indexes.get(target);
				sb.append(index * (n - index -1)).append("\n");
			}

			else{
				sb.append(0).append("\n");
			}
		}
		System.out.println(sb);
	}
}
