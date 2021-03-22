import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int map[][];
	static boolean isVisited[][];
	static boolean isFind;
	static int result = Integer.MAX_VALUE;
	static Queue<Node> q = new LinkedList<>();
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		isVisited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
				if (map[i][j] == 2)
					q.offer(new Node(i,j,0));
			}
		}

		bfs();
		if(isFind) {
			System.out.println("TAK");
			System.out.println(result);
		}
		else
			System.out.println("NIE");
	}

	static void bfs() {
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				
				if(nx<0 || nx>=n || ny<0 || ny>=m || isVisited[nx][ny] || map[nx][ny]==1) continue;
				
				if(map[nx][ny] ==3 || map[nx][ny] ==4 || map[nx][ny] ==5) {
					//음식 찾음
					isFind = true;
					result = node.weight+1;
					return;
					
				}
				
				isVisited[nx][ny] = true;
				q.offer(new Node(nx,ny,node.weight+1));
			}
		}
	}
	
	static class Node{
		int x;
		int y;
		int weight;
		public Node(int x, int y, int weight) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
	}
}
