import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_1697 {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Queue<Integer> q = new LinkedList<>();
		
		int N,K; //�������� ��ġ ,, ������ ��ġ
		int time = 0; //�ɸ��� �ð�
		int visited[] = new int[100001];
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		q.offer(N); //�ʱ� �� �־��ֱ�
		while(!q.isEmpty()) {
			
			int size = q.size();
			int current=0;
			
			//size��ŭ ���鼭 -1,1,2 �־��ֱ�
			//���� dept��ŭ?
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
