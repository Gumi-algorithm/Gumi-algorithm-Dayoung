import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //보드의 크기
		int K = sc.nextInt(); //사과의 개수
		
		int map[][] = new int[N][N];
		
		for(int i=0;i<K;i++) {
			int row = sc.nextInt();
			int col = sc.nextInt();
			map[row][col] = 1;
		}//사과의 위치 입력
		
		int L = sc.nextInt(); //방향 변환 정보
		//D는 오른쪽으로 90도 회전 L의 왼쪽으로 90도 회전
		
		Queue<Node> q = new LinkedList<>(); //방향정보 저장할 큐
		for(int i=0;i<L;i++) {
			int time = sc.nextInt();
			String dir = sc.next();
			
			q.offer(new Node(time,dir));
		}
		
		Deque<Node> dq = new ArrayDeque<>();  //뱀의 정보 저장할 덱
		dq.offer(new Node(0,0)); //초반 시작값 넣어주기
		
		int result = 0;
		
		while(true) {
			//일단 오른쪽을 향해서 출발
			Node node = q.peek();
			if(node.time == result) {
				//현재 시간이랑 뱀의 뱡향 변환 정보가 같다면 이동시켜주기
			}
			//시간만나면 튀튀
			result++;
		}
	}
	
	static class Node{
		int x;
		int y;
		int time;
		String dir;
		public Node(int time, String dir) {
			super();
			this.time = time;
			this.dir = dir;
		}
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
}
