import java.io.*;
import java.util.*;

public class Softeer_로봇이지나간경로 {

    static int H, W;
    static char[][] map;
    static boolean[][] visited;
    static int str, stc;
    static int[] dr = {-1, 0, 1, 0, -1, 0}; // 상 우 하 좌 상 우
    static int[] dc = {0, 1, 0, -1, 0, 1};
    static String ans= "";
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        visited = new boolean[H][W];
        int tempr = 0;
        int tempc = 0;
        
        for (int i=0; i<H; i++) {
            String word = br.readLine();
            for (int j=0; j<W; j++) {
                map[i][j] = word.charAt(j);
                if (map[i][j] == '#') {
                    tempr = i;
                    tempc = j;
                }
            }
        }

        dfs(tempr, tempc); // 시작 지점 or 끝 지점 찾기

        bw.write((str+1) + " " + (stc+1) + "\n");
        char dir ='a';
        for (int i=0; i<4; i++) {
            int nr = str + dr[i];
            int nc = stc + dc[i];
            if (isIn(nr, nc)) {
                if (map[nr][nc] == '#') {
                    if (i == 0 ){
                        dir = '^';
                    } else if (i == 1) {
                        dir = '>';
                    } else if (i == 2) {
                        dir = 'V';
                    } else {
                        dir = '<';
                    }
                }
            }
        }
        bw.write(dir + "\n");
        
        dfsans(str, stc, dir);
        bw.write(ans+"");
        
        br.close();
        bw.close();
    }

    public static void dfs(int r, int c) {
        if(!visited[r][c]) {
            visited[r][c] = true;
            str = r;
            stc = c;

            for (int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (isIn(nr, nc)) {
                    if (map[nr][nc] == '#' && !visited[nr][nc]) {
                        dfs(nr, nc);
                    }
                }
            }
        }
    }

    public static void dfsans(int r, int c, char dir) {
        int curr = r;
        int curc = c;
        char curdir = dir;
        boolean a = true;
        while (a) {
            a = false;
            if (curdir == '^') {
                 for (int i=4; i<7; i++) {
                     int j = i;
                     if (j == 6 ) {
                         j = 3;
                     }
                     int nr = curr + dr[j];
                     int nc = curc + dc[j];
                     if (isIn(nr, nc)) {
                         if (map[nr][nc] =='#') {
                             a = true;
                             if (j==4) {
                                 ans += "A";
                                 curr = nr + dr[j];
                                 curc = nc + dc[j];
                             } else if (j == 3) {
                                 ans+= "L";
                                 curdir = '<';
                             } else {
                                 ans += "R";
                                 curdir = '>';
                             }
                             break;
                         }
                     }
                 }
            } else if (curdir == '>') {
                for (int i=1; i<4; i++) {
                     int j = i;
                     if ( j==3 ) {
                         j = 0;
                     }
                     int nr = curr + dr[j];
                     int nc = curc + dc[j];
                     if (isIn(nr, nc)) {
                         if (map[nr][nc] =='#') {
                             a = true;
                             if (j==1) {
                                 ans += "A";
                                 curr = nr + dr[j];
                                 curc = nc + dc[j];
                             } else if (j == 0) {
                                 ans+= "L";
                                 curdir = '^';
                             } else {
                                 ans += "R";
                                 curdir = 'V';
                             }
                             break;
                         }
                     }
                 }
            } else if (curdir == 'V') {
                for (int i=2; i<5; i++) {
                     int j = i;
                     if (j== 4) {
                        j=1;
                     }
                     int nr = curr + dr[j];
                     int nc = curc + dc[j];
                     if (isIn(nr, nc)) {
                         if (map[nr][nc] =='#') {
                             a = true;
                             if (j==2) {
                                 ans += "A";
                                 curr = nr + dr[j];
                                 curc = nc + dc[j];
                             } else if (j == 1) {
                                 ans+= "L";
                                 curdir = '>';
                             } else {
                                 ans += "R";
                                 curdir = '<';
                             }
                             break;
                         }
                     }
                 }
            } else {
                for (int i=3; i<6; i++) {
                     int j = i;
                     if (j == 5) {
                         j= 2;
                     }
                     int nr = curr + dr[j];
                     int nc = curc + dc[j];
                     if (isIn(nr, nc)) {
                         if (map[nr][nc] =='#') {
                             a = true;
                             if (j==3) {
                                 ans += "A";
                                 curr = nr + dr[j];
                                 curc = nc + dc[j];
                             } else if (j == 2) {
                                 ans+= "L";
                                 curdir = 'V';
                             } else {
                                 ans += "R";
                                 curdir = '^';
                             }
                             break;
                         }
                     }
                 }
            }
        }
    }

    public static boolean isIn(int r, int c) {
        if (r >=0 && r < H && c >=0 && c < W) {
            return true;
        }
        return false;
    }
}