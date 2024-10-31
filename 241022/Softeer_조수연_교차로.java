import java.io.*;
import java.util.*;

public class Softeer_조수연_교차로 {

    // 풀이중

    static class Car {
        int time;
        int doroIdx;

        Car(int time, char doro) {
            this.time = time;
            this.doroIdx = doro - 'A';
        }
        Car(int time, int doroIdx) {
            this.time = time;
            this.doroIdx = doroIdx;
        }
    }
    static PriorityQueue<Car> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(bf.readLine());
        pq = new PriorityQueue<>(new Comparator<Car>() {

            @Override
            public int compare(Car o1, Car o2) {
                if(o1.time == o2.time) {
                    return -1;
                }

                return o1.time - o2.time;
            }
            
        });

        
        Queue<Integer> doros[] = new ArrayDeque[4];
        for(int i=0;i<4;i++) {
            doros[i] = new ArrayDeque<>();
        }

        // StringTokenizer st = null;
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int time = Integer.parseInt(st.nextToken());
        int doroIdx = st.nextToken().charAt(0) - 'A';
        doros[doroIdx].offer(time);

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(bf.readLine());
            int t = Integer.parseInt(st.nextToken());
            char index = st.nextToken().charAt(0);
            pq.offer(new Car(t, index));
        }

        while(true) {
            //time과 같은 차들 도로 위에 세우기
            while(!pq.isEmpty() && pq.peek().time == time) {
                Car car = pq.poll();
                doros[car.doroIdx].offer(car.time);
            }

            //만약 모든 도로가 차있다면 RETURN -1
            if(doros[0].size()!=0 && doros[1].size()!=0 && doros[2].size()!=0 && doros[3].size()!=0) {
                for(int cnt = 0; cnt < pq.size(); cnt++) {
                    sb.append("-1\n");
                }
                System.out.println(sb);
                return;
            }

            //ac, bd일때 둘다 보내는 코드
            if(doros[0].size() == 0 && doros[2].size() == 0) {
                int carTime = Math.max(doros[1].poll(), doros[])
            }

            //차 하나 도로에서 출발시키기.
            

        }

        // while(!pq.isEmpty()) {
        //     Car[] carlist = new Car[4];
        //     carlist[0] = pq.poll();
        //     int i = 1;

        //     while(!pq.isEmpty() && pq.peek().time == carlist[0].time) {
        //         carlist[i++] = pq.poll();
        //     }

        //     if(i==4) {
        //         //남은거 다 -1출력하고 return
        //     }

        //     if(i==3) {
        //         //차 3대
        //     } else if(i==2) {
        //         //차 2대
        //     } else {
        //         //차 1대
        //         sb.append(carlist[0].time);
        //     }

        // }

        
    }
}
