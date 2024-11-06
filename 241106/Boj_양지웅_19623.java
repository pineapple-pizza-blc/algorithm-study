// 회의실 배정 4, 메모리 : 59084kb, 시간 : 716ms

import java.util.*;
import java.io.*;

class Meeting {
    int start;
    int end;
    int count;

    public Meeting(int start, int end, int count) {
        this.start = start;
        this.end = end;
        this.count = count;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Meeting[] meetings = new Meeting[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(start, end, count);
        }

        Arrays.sort(meetings, Comparator.comparingInt(meeting -> meeting.end));

        int[] dp = new int[N];
        dp[0] = meetings[0].count;
        for (int i = 1; i < N; i++) {
            int sum = meetings[i].count;
            int idx = binarySearch(meetings, i);
            if (idx != -1) {
                sum += dp[idx];
            }
            dp[i] = Math.max(sum, dp[i - 1]);
        }

        System.out.println(dp[N - 1]);
    }

    private static int binarySearch(Meeting[] meetings, int index) {
        int left = 0, right = index - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (meetings[mid].end <= meetings[index].start) {
                if (meetings[mid + 1].end > meetings[index].start) return mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
