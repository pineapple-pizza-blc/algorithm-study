package solved.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class P2922 {

	static long result;
	static char[] word;
	static HashMap<Character, Boolean> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		list = new HashMap<>();
		list.put('A', true);
		list.put('E', true);
		list.put('I', true);
		list.put('O', true);
		list.put('U', true);

		word = br.readLine().toCharArray();
		result = 0;
		dfs(false,0,0,0, 1);

		System.out.println(result);
	}

	public static void dfs(boolean flag, int gather, int consonant, int depth, long cnt){
		if (gather == 3 || consonant == 3) return;
		if (depth == word.length){
			if (flag && gather < 3 && consonant < 3) {
				result+= cnt;
			}
			return;
		}

		char now = word[depth];
		// System.out.println(now+ " " + cnt);
		if(now != '_'){
			if (list.containsKey(now)){
				dfs(flag, gather+1, 0, depth+1, cnt);
			}
			else {
				if (now == 'L'){
					dfs(true, 0, consonant+1, depth+1, cnt);
				}
				else{
					dfs(flag, 0, consonant+1, depth+1, cnt);
				}
			}
		}
		else{
			dfs(flag, gather+1, 0, depth+1, cnt * 5);
			dfs(flag, 0, consonant+1, depth+1, cnt * 20);
			dfs(true, 0, consonant+1, depth+1, cnt);
		}

	}
}
