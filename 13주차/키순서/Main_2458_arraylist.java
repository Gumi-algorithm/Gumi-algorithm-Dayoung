package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list[] = new ArrayList[N+1];
        for(int i=0;i<=N;i++) list[i] = new ArrayList<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            //번호가 a인 학생이 번호가 b인 학생보다 키가 작다.
            int a= Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
        }

        int result = 0;
        for(int i=1;i<=N;i++){
            if(bfs(list,i,N)) result++;
        }

        System.out.println(result);
    }

    private static boolean bfs(ArrayList<Integer>[] list, int i, int N) {
        int res = list[i].size(); //얘는 나보다 큰애들 일단 먼저 넣어줌
        Queue<Integer> q = new LinkedList<>();
        boolean isVisited[] = new boolean[N+1];
        q.offer(i); //나보다 작은 애들 찾을 거
        isVisited[i] = true;

        while (!q.isEmpty()){
            int num = q.poll();

            for(int n=1;n<=N;n++){
                if(list[n].contains(num) && !isVisited[n]){
                    res++;
                    q.offer(n);
                    isVisited[n] = true;
                }
            }
        }

        return  res==N-1?true:false;
    }
}
