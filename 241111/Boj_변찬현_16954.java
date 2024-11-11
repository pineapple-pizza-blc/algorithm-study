import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static char[][][] map;
	static boolean[][][] visited;
	static boolean check;
	
	static int[] dr = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		map = new char[8][8][8];
		visited = new boolean[8][8][8];
		check = false;
		
		for (int i=0; i<8; i++) {
			String word = br.readLine();
			for (int j=0; j<8; j++) {
				map[i][j][0] = word.charAt(j);
			}
		}
		
		for (int t=1; t<8; t++) {
			for (int i=7; i>=0; i--) {
				for (int j=0; j<8; j++) {
					if (i != 0) {
						map[i][j][t] = map[i-1][j][t-1];
					} else {
						map[i][j][t] = '.';
					}
				}
			}
		}
		
		start(7, 0, 0);
		
		if(check) {
			bw.write("1");
		} else {
			bw.write("0");
		}

		br.close();
		bw.close();
	}

	private static void start(int i, int j, int t) {
		if (t >=7 || (i==0 && j==7)) {
			check=true;
			return;
		}
		
		for (int d=0; d<9; d++) {
			int ni = i + dr[d];
			int nj = j + dc[d];
			if (ni >=0 && ni < 8 && nj >=0 && nj<8) {
				if (map[ni][nj][t] != '#') {
					if (!visited[ni][nj][t+1]) {
						visited[ni][nj][t+1] = true;
						if (map[ni][nj][t+1] != '#') {
							start(ni, nj, t+1);
						}
					}
				}
			}
		}
	}
}
