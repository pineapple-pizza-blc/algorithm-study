import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_변찬현_19623 {
	
	public static class Time implements Comparable<Time>{
		int st;
		int ed;
		int p;
		
		public Time(int st, int ed, int p) {
			this.st = st;
			this.ed = ed;
			this.p = p;
		}
		
		@Override
		public int compareTo(Time o) {
			if (this.ed == o.ed) {
				return this.st - o.st;
			}
			return this.ed - o.ed;
		}
	}
	
	public static List<Time> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		
		for (int i=0; i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.add(new Time(a,b,c));
		}

		Collections.sort(list);

		int[] dp = new int[N];
		dp[0] = list.get(0).p;
		
		for (int i=1; i<N;i++) {
			Time t = list.get(i);
			
			int curp = t.p;
			
			int prev = binarySearch(i);
			
			if (prev != -1) {
				curp += dp[prev];
			}
			
			dp[i] = Math.max(dp[i-1], curp);
		}
		
		bw.write(dp[N-1] +"");
		
		br.close();
		bw.close();
	}

	private static int binarySearch(int i) {
		int left =0;
		int right = i-1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (list.get(mid).ed < list.get(i).st) {
				if(list.get(mid +1).ed < list.get(i).st) {
					left = mid +1 ;
				} else {
					return mid;
				}
			} else {
				right = mid - 1;
			}
		}
		
		return -1;
	}
}
