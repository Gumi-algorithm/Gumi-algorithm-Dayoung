import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_1697 {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Queue<Integer> q = new LinkedList<>();
		
		int N,K; //수빈이의 위치 ,, 동생의 위치
		int time = 0; //걸리는 시간
		int visited[] = new int[100001];
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		q.offer(N); //초기 값 넣어주기
		while(!q.isEmpty()) {
			
			int size = q.size();
			int current=0;
			
			//size만큼 돌면서 -1,1,2 넣어주기
			//현재 dept만큼?
			for(int i=0;i<size;i++) {
				current = q.poll();
				if(current== K)
					break;
				if(current>0 && visited[current-1]==0) {
					q.offer(current-1);
					visited[current-1] = 1;
				}
				if(current<100000 && visited[current+1] == 0) {
					q.offer(current+1);
					visited[current+1] = 1;
				}
				if(current*2<=100000 && visited[current*2] == 0) {
					q.offer(current*2);
					visited[current*2] =1;	
				}
			}
			
			
			if(current == K)
				break;
			
			time++;
		}
		System.out.println(time);
	}
	

}
