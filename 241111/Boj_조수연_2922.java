import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Boj_조수연_2922 {
    static String word;
    static boolean isL = false;
    static long totalWays = 0;
    static Map<String, Long> memo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        word = bf.readLine();
        
        totalWays = solve(0, 0, 0, false);
        System.out.println(totalWays);
    }

    static long solve(int index, int vowelCount, int consonantCount, boolean hasL) {
        if (vowelCount >= 3 || consonantCount >= 3) {
            return 0;
        }
        if (index == word.length()) {
            return hasL ? 1 : 0;
        }

        String key = index + "," + vowelCount + "," + consonantCount + "," + hasL;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        char current = word.charAt(index);
        long count = 0;

        if (current == '_') {
            
            count += 5 * solve(index + 1, vowelCount + 1, 0, hasL);

            count += 20 * solve(index + 1, 0, consonantCount + 1, hasL);
            count += solve(index + 1, 0, consonantCount + 1, true);

        } else {
            if (isVowel(current)) {
                count = solve(index + 1, vowelCount + 1, 0, hasL);
            } else {
                count = solve(index + 1, 0, consonantCount + 1, hasL || current == 'L');
            }
        }

        memo.put(key, count);
        return count;
    }

    static boolean isVowel(char c) {
        return "AEIOU".indexOf(c) != -1;
    }
}
