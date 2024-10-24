import java.io.*;
import java.util.*;

public class Softeer_조수연_사물인식최소면적산출프로그램 {

    //실행시간 0.159초 메모리 11.31MB

    static class Loc {
        int x;
        int y;

        Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static List<Loc> dots[];
    static int N, k, ans;
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        dots = new ArrayList[k];

        for(int i=0;i<k;i++) {
            dots[i] = new ArrayList<>();
        }
        
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(bf.readLine());
            Loc loc = new Loc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            int idx = Integer.parseInt(st.nextToken())-1;
            dots[idx].add(loc);
        }

        for(int i=0;i<dots[0].size();i++) {
            Loc loc = dots[0].get(i);
            getRec(1, loc.x, loc.x, loc.y, loc.y);
        }

        System.out.print(ans);
        
    }

    static void getRec(int dotsIdx, int minX, int maxX, int minY, int maxY) {
        int size = (maxX - minX) * (maxY - minY);
        if(size >= ans) return;
        
        if(dotsIdx == k) {
            ans = size;
            return;
        }

        for(int i=0;i<dots[dotsIdx].size();i++) {
            Loc loc = dots[dotsIdx].get(i);
            int x = loc.x, y = loc.y;

            int newMinX = Math.min(minX, x);
            int newMaxX = Math.max(maxX, x);
            int newMinY = Math.min(minY, y);
            int newMaxY = Math.max(maxY, y);
            
            getRec(dotsIdx + 1, newMinX, newMaxX, newMinY, newMaxY);
            
        }
        
    }
}