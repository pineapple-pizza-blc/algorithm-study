import java.io.*;
import java.util.*;

// 실행 시간 175 ms 메모리 16.93 MB
public class Softeer_이규빈_사물인식최소면적산출프로그램 {

    static int N, K,result;
    static ArrayList<ArrayList<int[]>> coordinates;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coordinates = new ArrayList<>();

        for (int i = 0; i < K + 1; i++) coordinates.add(new ArrayList<>());

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            coordinates.get(k).add(new int[] {x, y});
        }
        //입력 끝
        result = Integer.MAX_VALUE;
        for (int[] start : coordinates.get(1)){
            int startX = start[0];
            int startY = start[1];
            makeQuadRangle(2, startX, startY, startX, startY, 0);
        }

        System.out.println(result);
    }

    public static void makeQuadRangle(int target, int startX, int startY, int endX, int endY, int area){
        if(area > result) return;
        if (target == K+1){
            result = Math.min(result, area);
            return;
        }


        for (int[] next : coordinates.get(target)){
            int newStartX = Math.min(startX, next[0]);
            int newStartY = Math.min(startY, next[1]);
            int newEndX = Math.max(endX, next[0]);
            int newEndY = Math.max(endY, next[1]);
            if (result > (newEndX - newStartX) * (newEndY - newStartY)){
                makeQuadRangle(target+1, newStartX, newStartY, newEndX, newEndY, (newEndX - newStartX) * (newEndY - newStartY));
            }
        }
    }
}