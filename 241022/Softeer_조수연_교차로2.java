import java.io.*;
import java.util.*;

public class Softeer_조수연_교차로2 {

    static class Output extends Car {

        int outputIdx;
        int outTime;
    

        Output(int time, char doro) {
            super(time, doro);
            this.outputIdx = idx++;
        }

        Output(Car car) {
            super(car.time, car.doro);
            this.outputIdx = idx++;
        }
    
        @Override
        public String toString() {
            return "OutputIdx: " + outputIdx + ", OutTime: " + outTime +
                   ", CarTime: " + time + ", Doro: " + doro;
        }
    }

    static class Car {
        int time;
        char doro;
        
        Car(int time, char doro) {
            this.time = time;
            this.doro = doro;
        }
    }

    static int idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        Queue<Output> doros[] = new ArrayDeque[4];
        for(int i=0;i<4;i++) {
            doros[i] = new ArrayDeque<>();
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int time = Integer.parseInt(st.nextToken());
        char doro = st.nextToken().charAt(0);

        doros[doro-'A'].offer(new Output(time, doro));

        for(int i=1;i<N;i++) {
            st = new StringTokenizer(bf.readLine());
            Car car = new Car(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));

            doros[car.doro-'A'].offer(new Output(car));

        }

        int cnt = 0;
        while(cnt < idx) {
            List<Output>
        }

    }
}
