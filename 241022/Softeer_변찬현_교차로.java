import java.io.*;
import java.util.*;

public class Softeer_변찬현_교차로 {

    public static class Car {
        int index;
        int time;

        public Car(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Queue<Car> a = new LinkedList<>();
        Queue<Car> b = new LinkedList<>();
        Queue<Car> c = new LinkedList<>();
        Queue<Car> d = new LinkedList<>();

        int[] ans = new int[N];
        Arrays.fill(ans, -1);

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char car = st.nextToken().charAt(0);
            if (car == 'A') {
                a.offer(new Car(i, time));
            } else if (car == 'B') {
                b.offer(new Car(i, time));
            } else if (car == 'C') {
                c.offer(new Car(i, time));
            } else if (car == 'D') {
                d.offer(new Car(i, time));
            }
        }

        int currentTime = 0;

        while (!a.isEmpty() || !b.isEmpty() || !c.isEmpty() || !d.isEmpty()) {
            boolean hasPassed = false;
            boolean ahasPassed = false;
            boolean bhasPassed = false;
            boolean chasPassed = false;
            boolean dhasPassed = false;

            if (!a.isEmpty() && !b.isEmpty() && !c.isEmpty() && !d.isEmpty()) {
                if (a.peek().time <= currentTime && b.peek().time <= currentTime &&
                        c.peek().time <= currentTime && d.peek().time <= currentTime) {
                    break;
                }
            }

            if (!a.isEmpty() && a.peek().time <= currentTime) {
                if (d.isEmpty() || d.peek().time > currentTime) {
                    ans[a.poll().index] = currentTime;
                    hasPassed = true;
                    ahasPassed = true;
                }
            }

            if (!b.isEmpty() && b.peek().time <= currentTime && !ahasPassed) {
                if (a.isEmpty() || a.peek().time > currentTime) {
                    ans[b.poll().index] = currentTime;
                    hasPassed = true;
                    bhasPassed = true;
                }
            }

            if (!c.isEmpty() && c.peek().time <= currentTime && !bhasPassed) {
                if (b.isEmpty() || b.peek().time > currentTime) {
                    ans[c.poll().index] = currentTime;
                    hasPassed = true;
                    chasPassed = true;
                }
            }

            if (!d.isEmpty() && d.peek().time <= currentTime && !chasPassed) {
                if (c.isEmpty() || c.peek().time > currentTime) {
                    ans[d.poll().index] = currentTime;
                    hasPassed = true;
                    dhasPassed = true;
                }
            }

            if(!hasPassed) {
                int nextTime = Integer.MAX_VALUE;
                if (!a.isEmpty()) nextTime = Math.min(nextTime, a.peek().time);
                if (!b.isEmpty()) nextTime = Math.min(nextTime, b.peek().time);
                if (!c.isEmpty()) nextTime = Math.min(nextTime, c.peek().time);
                if (!d.isEmpty()) nextTime = Math.min(nextTime, d.peek().time);

                currentTime = nextTime;
            } else {
                currentTime++;
            }
        }

        for (int i = 0; i < N; i++) {
            bw.write(ans[i] + "\n");
        }

        br.close();
        bw.close();
    }
}