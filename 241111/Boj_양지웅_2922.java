// 즐거운 단어, 메모리 : 12748kb, 시간 : 72ms

import java.io.*;

public class Main {
    static long answer;
    static char[] letters;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = 0;
        letters = br.readLine().toCharArray();

        DFS(0, 0,0, false, 1);

        System.out.println(answer);
    }

    private static void DFS(int idx, int cc, int vc, boolean hasL, long sum) {
        if (idx >= letters.length) {
            if (hasL) {
                answer += sum;
            }
            return;
        }

        char letter = letters[idx];
        if (letter == '_') {
            if (vc < 2) {
                DFS(idx + 1, 0, vc + 1, hasL, sum * 5);
            }
            if (cc < 2) {
                DFS(idx + 1, cc + 1, 0, hasL, sum * 20);
                DFS(idx + 1, cc + 1, 0, true, sum);
            }
        } else if (isVowel(letter)) {
            if (vc >= 2) return;
            DFS(idx + 1, 0, vc + 1, hasL, sum);
        } else {
            if (cc >= 2) return;
            if (letter == 'L') DFS(idx + 1, cc + 1, 0,true, sum);
            else DFS(idx + 1, cc + 1, 0, hasL, sum);
        }
    }

    private static boolean isVowel(char letter) {
        return ("AEIOU".indexOf(letter) != -1);
    }
}
