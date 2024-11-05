import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_변찬현_적의적 {
	
	static int[] p;
	static int[] e;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		p = new int[N+1];
		e = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			p[i] = i;
			e[i] = 0;
		}
		
		boolean suc = true;
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (findset(a) == findset(b)) {
				suc = false;
				break;
			}
			
			if (e[a] == 0) {
				e[a] = b;
			} else {
				union(e[a], b);
			}
			
			if (e[b] ==0) {
				e[b] = a;
			} else {
				union(e[b], a);
			}
		}
		
		if(suc) {
			bw.write("1");
		} else {
			bw.write("0");
		}
		

		br.close();
		bw.close();
	}
	
	public static void union(int a, int b) {
		p[findset(a)] = findset(b);
	}
	
	public static int findset(int x) {
		if(x != p[x]) {
			p[x] = findset(p[x]);
		}
		return p[x];
	}
	
}