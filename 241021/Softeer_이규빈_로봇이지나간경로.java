import java.io.*;
import java.util.*;

//실행 시간: 117 ms 
//메모리: 14.89 MB
public class Main {
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};

	static int a, b, total;
	static char[][] map;
	static boolean[][] visited;
	static int maxLength;
	static String result;

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		map = new char[a][b];
		visited = new boolean[a][b];

		for (int i = 0; i < a; i++){
			String route = br.readLine();
			for (int j = 0; j < b; j++){
				map[i][j] = route.charAt(j);
				if(map[i][j] == '#') total++;
			}
		}

		maxLength = Integer.MAX_VALUE;
		for (int i = 0; i < a; i++){
			for (int j = 0; j < b; j++){
				if (map[i][j] == '#'){
					visited[i][j] = true;
					for (int k = 0; k < 4; k++){
						StringBuilder sb = new StringBuilder();
						sb.append(i+1).append(" ").append(j+1).append("\n");
						if(k == 0) sb.append("^");
						else if (k == 1) sb.append(">");
						else if (k == 2) sb.append("v");
						else if (k == 3) sb.append("<");
						sb.append("\n");
						makeRoute(i, j, k, 1, sb);
					}
					visited[i][j] = false;
				}
			}
		}

		//4방향 중에 고르기
		//
		System.out.println(result);
	}

	public static void makeRoute(int x, int y, int dir, int depth, StringBuilder sb){
		if(depth == total && maxLength > sb.toString().length()){
			result = sb.toString();
			maxLength = sb.toString().length();
			return;
		}

		for (int i = -1; i < 2; i++){
			//전진
			if (i == 0){
				int nextX = x + dx[dir];
				int nextY = y + dy[dir];
				if (checkRange(nextX, nextY)){
					nextX += dx[dir];
					nextY += dy[dir];
					if (checkRange(nextX, nextY)){
						visited[nextX][nextY] = true;
						visited[nextX-dx[dir]][nextY-dy[dir]] = true;
						sb.append("A");
						makeRoute(nextX, nextY, dir, depth+2, sb);
						sb.deleteCharAt(sb.length()-1);
						visited[nextX][nextY] = false;
						visited[nextX-dx[dir]][nextY-dy[dir]] = false;
					}
				}

			}
			//회전
			else{
				int nextX = x + dx[ (dir+ i + 4) % 4 ];
				int nextY = y + dy[ (dir+ i + 4) % 4 ];
				if(checkRange(nextX, nextY)){
					nextX += dx[ (dir+ i + 4) % 4 ];
					nextY += dy[ (dir+ i + 4) % 4 ];
					if (checkRange(nextX, nextY)){
						visited[nextX][nextY] = true;
						visited[nextX-dx[ (dir+ i + 4) % 4 ]][nextY - dy[ (dir+ i + 4) % 4 ]] = true;
						if (i == -1){
							sb.append("L").append("A");
						}
						else {
							sb.append("R").append("A");
						}
						makeRoute(nextX, nextY, (dir+ i + 4) % 4, depth+2, sb);
						sb.deleteCharAt(sb.length()-1);
						sb.deleteCharAt(sb.length()-1);
						visited[nextX][nextY] = false;
						visited[nextX-dx[ (dir+ i + 4) % 4 ]][nextY - dy[ (dir+ i + 4) % 4 ]] = false;
					}
				}
			}
		}
	}

	public static boolean checkRange(int nextX, int nextY){
		return 0 <= nextX && nextX < a && 0 <= nextY && nextY < b && map[nextX][nextY] == '#';
	}
}
