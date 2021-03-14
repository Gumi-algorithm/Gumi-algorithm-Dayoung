import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static int fuel;
	static int shortestPath = Integer.MAX_VALUE;
	static int passenger[] = new int[2]; 
	static int map[][];
	static boolean isVisited[][];
	static Passenger shortest;
	static int car[] = new int[2];
	static ArrayList<Passenger> list = new ArrayList<>();
	static Queue<Passenger> q = new LinkedList<>();
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //지도 크기
		M = Integer.parseInt(st.nextToken()); //각 승객의 출발지의 행,열,목적지의 행,열
		fuel = Integer.parseInt(st.nextToken()); //연료
		
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//운전을 시작하는 번호
		st = new StringTokenizer(br.readLine());
		car[0] = Integer.parseInt(st.nextToken()) -1; //열이 1번 부터 시작하니까 1 빼서 줄여주기
		car[1] = Integer.parseInt(st.nextToken())-1; //행이 1번 부터 시작하니까 1빼서 줄여주기
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start_x = Integer.parseInt(st.nextToken())-1;
			int start_y = Integer.parseInt(st.nextToken())-1;
			int end_x = Integer.parseInt(st.nextToken())-1;
			int end_y = Integer.parseInt(st.nextToken())-1;
			
			list.add(new Passenger(start_x,start_y,end_x,end_y)); //손님 좌표
			//현재 택시의 위치와 가장 짧은 거리에 있는 손님의 위치 구하기
		}
		System.out.println(list.size());
		find();
	}
	
	static void find() {
		int size = list.size();
		for(int i=0;i<size;i++) {
			isVisited = new boolean[N][N];
			q.offer(new Passenger(list.get(i).start_x, list.get(i).start_y)); //큐에 삽입
			bfs(list.get(i).start_x,list.get(i).start_y);
			System.out.println("현재 가장 짧은 거리 :"+shortestPath);
			q.clear();
		}
	}
	
	static void bfs(int row,int col) {
		int path = 0;
		while(!q.isEmpty()) {
			Passenger p = q.poll();
			for(int d=0;d<4;d++) {
				int nx = p.start_x + dx[d];
				int ny = p.start_y + dy[d];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N || isVisited[nx][ny] || map[nx][ny]==1) continue;
				
				if(nx == car[0] && ny == car[1]) {//차에 있는 좌표에 도착했으면
					if(shortestPath == (path+1)) {//현재 길이랑 가장 짧은 길이가 같다면
						//열이 더 작은 쪽꺼 넣어주기
						if(nx<p.start_x) p = new Passenger(nx, ny);
						else p = new Passenger(p.start_x, ny);
						break;
					}
					
					shortestPath = Math.min(shortestPath, path+1);
					p = new Passenger(nx, ny); //현재 좌표 값 넣어주기
				}
				
				isVisited[nx][ny] = true;
				q.offer(new Passenger(nx, ny));
			}
			path++;
		}
	}
	
	static class Passenger{
		int start_x;
		int start_y;
		int end_x;
		int end_y;
		
		public Passenger(int start_x,int start_y) {
			super();
			this.start_x = start_x;
			this.start_y = start_y;
		}
		public Passenger(int start_x, int start_y, int end_x, int end_y) {
			super();
			this.start_x = start_x;
			this.start_y = start_y;
			this.end_x = end_x;
			this.end_y = end_y;
		}	
	}
}
