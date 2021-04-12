import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static int T;
	static int map[][],sum[][];
	static boolean isVisited[][];
	static boolean isGram = false;
	static boolean isFind = false;
	static Queue<Node> q;
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		sum = new int[N][M];
		isVisited = new boolean[N][M];
		q = new LinkedList<>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++)
				map[i][j]  = Integer.parseInt(st.nextToken());
		}
		
		q.offer(new Node(0,0));
		isVisited[0][0] = true;
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(map[node.x][node.y] == 2) isGram = true;
			if(node.x == N-1 && node.y == M-1) isFind = true;
			for(int d=0;d<4;d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				
				if(nx<0 || ny<0 || nx>=N || ny>=M || isVisited[nx][ny]) continue;
				//if(map[nx][ny] == 1) continue; //지나갈 수 없는 길 
				//오른쪽이나 밑으로 갈 때만 그램 쓰기?
				if(map[nx][ny] == 1 && isGram && (node.x<nx ||node.y<ny)) {
					isVisited[nx][ny] = true;
					q.offer(new Node(nx,ny));
					isGram = false;
				}
				else if(map[nx][ny] !=1){
					isVisited[nx][ny] = true;
					q.offer(new Node(nx,ny));
				}
				
				if(sum[nx][ny] == 0) sum[nx][ny] =sum[node.x][node.y]+1; //거리 구할려고 sum배열에 저장함
				else sum[nx][ny] = Math.min(sum[nx][ny], sum[node.x][node.y]+1); //경로 여러개? 니까 그때 작은 root로 더해주기
			}
		}
		
		//for(int i=0;i<N;i++) System.out.println(Arrays.toString(sum[i]));
		
		if(isFind && sum[N-1][M-1]<T) System.out.println(sum[N-1][M-1]); //도착했고 거리 안이면
		else System.out.println("Fail"); 
		
	}
	
	static class Node{
		int x,y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
