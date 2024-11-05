import java.io.*;
import java.util.*;

//실행 시간 403 ms 메모리 47.86 MB
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		ArrayList<ArrayDeque<int[]>> loads = new ArrayList<>();

		for (int i = 0; i < 4; i++){
			loads.add(new ArrayDeque<>());
		}

		int preTime = 0;
		for (int i = 0; i < n; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			String w = st.nextToken();

			if (i == 0) preTime = t;
			if (w.equals("A")) loads.get(3).add(new int[] {t, i});
			if (w.equals("B")) loads.get(2).add(new int[] {t, i});
			if (w.equals("C")) loads.get(1).add(new int[] {t, i});
			if (w.equals("D")) loads.get(0).add(new int[] {t, i});
		}

		int[] result = new int[n];
		int checkCount = 0;
		int time = 0;
		Arrays.fill(result, -1);

		while(true){

			int[] possible = new int[4];
			Arrays.fill(possible,Integer.MAX_VALUE);
			int minTime = Integer.MAX_VALUE;
			//나올 가능성이 있는 후보 찾기
			for (int i = 0; i < 4; i++){
				if (!loads.get(i).isEmpty()){
					int[] car = loads.get(i).peekFirst();
					possible[i] = car[0];
					minTime = Math.min(minTime, car[0]);
				}
			}

			int impossibleCount = 0;

			for (int i = 0; i < 4; i++){
				if (possible[i] == minTime && possible[(i+1) % 4] != minTime){
					int[] car = loads.get(i).pollFirst();
					result[car[1]] = car[0];
					if (!loads.get(i).isEmpty() && loads.get(i).peekFirst()[0] <= car[0]) loads.get(i).peekFirst()[0]+= car[0] - loads.get(i).peekFirst()[0]+1;
					checkCount ++;
				}
				if (possible[i] == minTime && possible[(i+1) % 4] == minTime){
					int[] car = loads.get(i).peekFirst();
					car[0]++;
					impossibleCount += 1;
				}
			}

			if (impossibleCount == 4 || checkCount == n){
				break;
			}

		}

		StringBuilder sb = new StringBuilder();
		for (int car : result){
			sb.append(car).append("\n");
		}
		System.out.println(sb);

	}
}