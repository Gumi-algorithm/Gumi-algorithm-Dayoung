import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
//https://www.acmicpc.net/problem/7576
	static int N,M;
	static int map[][];
	static boolean isVisited[][];
	static int time;
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	static Queue<ArrayList<Node>> q = new LinkedList<>();
	static ArrayList<Node> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		isVisited = new boolean[N][M];
		
		boolean isTrue = false;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) list.add(new Node(i,j)); //토마토 위치 저장할 ArrayList
				if(map[i][j] == 0) isTrue = true;
			}
		} 
		
		//1은 익은 토마토 0은 익지않은 토마도 -1을 토마토가 들어있지않은 칸
		//토마토는 한꺼번에 익는다
		//토마토 칸 하나당 bfs?
		
		//list마다 계속 옆으로 움직이면서 map[1][1]로 채워주고 그 1짜리 또 가고 ,,,이런식으로?
		//queue에 ArrayList 넣어주기?
		if(!isTrue) {
			System.out.println("0");
			return;
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==0 && !isPath(i, j)) {
					System.out.println("-1");
					return;
				}
			}
		}
		q.offer(list);
		bfs();
		System.out.println(time-1);
		
	}
	
	static void bfs() {
		while(!q.isEmpty()) {
			//큐가 빌 때 까지
			ArrayList<Node> qList = q.poll(); //현재 큐에 들어 있는 리스트 빼기
			ArrayList<Node> newList = new ArrayList<>(); //새로 구한 토마토 리스트 넣어주기
			int size = qList.size();
			
			for(int i=0;i<size;i++) {
				//현재 리스트에 들어있는 토마토 위치 하나씩 빼면서 사방위 탐색 해주기
				for(int d=0;d<4;d++) {
					int nx = qList.get(i).row + dx[d];
					int ny = qList.get(i).col + dy[d];
					
					if(nx<0 || nx>=N || ny<0 || ny>=M || isVisited[nx][ny]|| map[nx][ny]==-1 || map[nx][ny]==1) continue;
					
					map[nx][ny] = 1;
					newList.add(new Node(nx,ny));
				}
			}
			time++;
			if(newList.size()!=0) q.offer(newList);;
			bfs();
		}
	}
	
	static boolean isPath(int row,int col) {
		for(int d=0;d<4;d++) {
			int nx = row + dx[d];
			int ny = col + dy[d];
			
			if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
			
			if(map[nx][ny] !=-1) return true; //4방향중에 -1이 아닌게 있으면
		}
		
		return false;
	}
	
	static class Node{
		int row;
		int col;
		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
}
