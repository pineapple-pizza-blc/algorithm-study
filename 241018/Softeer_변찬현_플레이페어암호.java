import java.io.*;
import java.util.*;

public class Softeer_변찬현_플레이페어암호 {

    public static String ans;
    public static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String message = br.readLine();
        String key = br.readLine();
        boolean[] alpha = new boolean[26];
        alpha[9] = true;
        map = new char[5][5];
        int curr = 0;
        int curc = 0;
        for (int i=0; i < key.length(); i++){
            char a = key.charAt(i);
            if (alpha[a-'A']) {
                continue;
            }
            alpha[a-'A'] = true;
            map[curr][curc] = a;
            curc+=1;
            if (curc==5) {
                curc=0;
                curr+=1;
            }
        }

        for(int i=0; i<26; i++){
            if (!alpha[i]) {
                char a = (char) (65 + i);
                map[curr][curc] = a;
                curc+=1;
                if (curc==5) {
                    curc=0;
                    curr+=1;
                }
            }
        }

        ans = "";
        Queue<Character> q = new LinkedList<>();
        for (int i=0; i<message.length(); i++) {
            q.add(message.charAt(i));
        }

        while(!q.isEmpty()) {
            char a = q.poll();
            if (q.isEmpty()){
                char b = 'X';
                convert(a,b);
            } else {
                char b = q.peek();
                if (a == b) {
                    if (a=='X'){
                        b='Q';
                        convert(a, b);
                    } else {
                        b='X';
                        convert(a, b);
                    }
                } else {
                    b = q.poll();
                    convert(a, b);
                }
            }
        }
        bw.write(ans);

        br.close();
        bw.close();
    }

    public static void convert(char a, char b) {
        int ar = 0;
        int ac = 0;
        int br = 0;
        int bc = 0;

        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                if (map[i][j] == a) {
                    ar = i;
                    ac = j;
                }
                if (map[i][j] == b) {
                    br = i;
                    bc = j;
                }
            }
        }

        if (ar == br) {
            ac++;
            if (ac == 5){
                ac =0;
            }
            bc++;
            if (bc == 5){
                bc =0;
            }
        } else if (ac == bc) {
            ar++;
            if (ar == 5) {
                ar =0;
            }
            br++;
            if (br == 5) {
                br = 0;
            }
        } else {
            int temp = ac;
            ac = bc;
            bc = temp;
        }
        ans += map[ar][ac];
        ans += map[br][bc];
    }
}
