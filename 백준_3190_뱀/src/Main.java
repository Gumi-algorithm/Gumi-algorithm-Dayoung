import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //보드의 크기
		int K = sc.nextInt(); //사과의 개수
		
		int map[][] = new int[N+1][N+1];
		
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
		dq.offer(new Node(1,1)); //초반 시작값 넣어주기
		
		map[1][1] = 2;
		
		int result = 0;
		int direction = 1;
		while(true) {
			//일단 오른쪽을 향해서 출발
			System.out.println(result);
			for(int i=0;i<N;i++) System.out.println(Arrays.toString(map[i]));
			System.out.println("==================================================");
			Node node = q.peek();
			if(node.time == result) {
				//현재 시간이랑 뱀의 뱡향 변환 정보가 같다면 방향 전환 시켜주기
				if(node.dir.equals("D")) {//오른쪽이면
					if(direction ==0) {
						direction = 2;
					}
					else if(direction == 1) {
						direction = 3;
					}
					else if(direction == 2) {
						direction = 0;
					}
					else if(direction == 3) {
						direction = 1;
					}
				}
				else {//왼쪽이면 
					if(direction == 0) {
						direction = 3;
					}
					else if(direction == 1) {
						direction = 2;
					}
					else if(direction == 2) {
						direction = 1;
					}
					else if(direction == 3) {
						direction = 0;
					}
				}
				q.poll(); //큐에서 하나 빼주기
			}
			
			//한칸씩 옆으로 가기
			int nx = dq.peek().x + dx[direction];
			int ny = dq.peek().y + dy[direction];
			
//			. 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
			if(nx<1 || ny<1 || nx>=N || ny>=N) break; //벽을 만나면 끝내기
			if(map[nx][ny] == 2) break;
			
//			먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
			if(map[nx][ny] == 1) {
				//만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
				map[nx][ny] = 2; 
				dq.offerFirst(new Node(nx,ny));
			}
//			만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
			else {
				map[dq.peek().x][dq.peek().y] = 0; //지나간 자리 비워주고
				//머리에 현재 위치 넣어주고
				map[nx][ny] = 2;
				dq.offerFirst(new Node(nx,ny));
				//뒤에 하나 빼주기
				dq.pollLast();
			}
			result++;
		}
		System.out.println(result);
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
