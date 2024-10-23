import java.io.*;
import java.util.*;

public class Softeer_조수연_교차로 {
    static class Car {
        int time;
        char doro;

        Car(int time, char doro) {
            this.time = time;
            this.doro = doro;
        }
    }
    static PriorityQueue<Car> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>(new Comparator<Car>() {

            @Override
            public int compare(Car o1, Car o2) {
                if(o1.time == o2.time) {
                    return -1;
                }

                return o1.time - o2.time;
            }
            
        });

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(bf.readLine());
            int time = Integer.parseInt(st.nextToken());
            char doro = st.nextToken().charAt(0);
            pq.offer(new Car(time, doro));
        }

        int time = 0;

        while(!pq.isEmpty()) {
            Car[] carlist = new Car[4];
            carlist[0] = pq.poll();
            int i = 1;

            while(!pq.isEmpty() && pq.peek().time == carlist[0].time) {
                carlist[i++] = pq.poll();
            }

            if(i==4) {
                //남은거 다 -1출력하고 return
            }

            if(i==3) {
                //차 3대
            } else if(i==2) {
                //차 2대
            } else {
                //차 1대
                sb.append(carlist[0].time);
            }

        }

        
    }
}
