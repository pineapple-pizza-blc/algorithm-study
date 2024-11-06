import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_조수연_19623 {

    static class Meeting implements Comparable<Meeting> {
        int start, end, people;

        Meeting(int start, int end, int people) {
            this.start = start;
            this.end = end;
            this.people = people;
        }

        @Override
        public int compareTo(Meeting o) {
            return this.end - o.end; // 종료 시간 기준 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        Meeting[] meetings = new Meeting[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(start, end, people);
        }

        // 종료 시간 기준 정렬
        Arrays.sort(meetings);

        // DP 배열 및 종료 시간 배열
        int[] dp = new int[n];
        int[] endTimes = new int[n];

        for (int i = 0; i < n; i++) {
            endTimes[i] = meetings[i].end; // 종료 시간만 저장
        }

        dp[0] = meetings[0].people; // 첫 회의 인원 초기화

        for (int i = 1; i < n; i++) {
            // 현재 회의 인원
            dp[i] = meetings[i].people;

            // 이전 회의 중 겹치지 않는 가장 마지막 회의 찾기 (이분 탐색)
            int idx = binarySearch(endTimes, meetings[i].start);
            if (idx != -1) {
                dp[i] += dp[idx];
            }

            // 이전 최대값과 비교
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }

        System.out.println(dp[n - 1]);
    }

    // 이분 탐색: 종료 시간이 target 이하인 가장 마지막 인덱스를 찾음
    private static int binarySearch(int[] endTimes, int target) {
        int left = 0, right = endTimes.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (endTimes[mid] <= target) {
                result = mid; // 가능한 인덱스 갱신
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}
