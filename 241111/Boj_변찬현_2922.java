import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static char[] arr;
	static String word;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		word = br.readLine();
		arr = new char[word.length()];

		for (int i = 0; i < word.length(); i++) {
			char a = word.charAt(i);
			if (a == 'A'|| a =='E' || a =='I' || a =='O' || a =='U') {
				arr[i] ='V';
			} else if (a == '_') {
				arr[i] ='_';
			} else if (a=='L') { 
				arr[i] ='L';
			} else {
				arr[i] = 'C';
			}
		}

		long ans = make(0);
		bw.write(ans + "");

		br.close();
		bw.close();
	}

	private static long make(int index) {
		if (index == word.length()) {
			int c = 0;
			int v = 0;
			boolean isL = false;

			for (int i = 0; i < word.length(); i++) {
				if (arr[i] == 'C' || arr[i] == 'L') {
					c++;
					v = 0;
					if (arr[i] == 'L') {
						isL = true;
					}
				} else if (arr[i] == 'V') {
					v++;
					c = 0;
				}
				if (c >= 3 || v >= 3) {
					return 0L;
				}
			}
			return isL ? 1L : 0L;
		}

		if (arr[index] == '_') {
			long ans = 0L;

			arr[index] = 'C';
			ans += 20L * make(index+1);

			arr[index] = 'V';
			ans += 5L * make(index+1);

			arr[index] = 'L';
			ans += make(index+1);

			arr[index] = '_';
			return ans;
		}

		return make(index+1);
	}
}
