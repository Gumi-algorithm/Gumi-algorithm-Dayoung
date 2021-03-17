import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,R;
	static int map[][];
	static int dir;
	static int resX,resY;
	static int dx[]= {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		 int T = Integer.parseInt(br.readLine()); //테스트 케이스 개수
		 for(int t=0;t<T;t++) {
			 st = new StringTokenizer(br.readLine());
			 N = Integer.parseInt(st.nextToken()); // 보드의 크기
			 R = Integer.parseInt(st.nextToken()); //우향우 거울의 개수
			 map = new int[N+2][N+2]; //레이저 좌표까지 더하기 위해 //좌표는 1부터 시작
			 
			 resX = 0;
			 resY = 0;
			 
			 for(int r=0;r<R;r++) {
				 st = new StringTokenizer(br.readLine());
				 //우향우 거울 좌표 입력
				 int row = Integer.parseInt(st.nextToken());
				 int col = Integer.parseInt(st.nextToken());
				 map[row][col] = 1;
			 }
			 //레이저 위치 입력
			 st = new StringTokenizer(br.readLine());
			 int row = Integer.parseInt(st.nextToken());
			 int col = Integer.parseInt(st.nextToken());
			 map[row][col] = 2; //레이저는 2로 표시
			 
			 int nx=0,ny=0;
			 if(col == 0) dir = 1; //위로
			 else if(col == (N+2)-1)  dir = 3; //왼쪽으로
			 else if(row == 0) dir = 2; //아래로
			 else if(row == (N+2)-1) dir = 0;
			 dfs(row,col,dir,0);
			 System.out.println(resX+" "+resY);
			
			 
		 }
		 
		 //col == 0 ->오른쪽
		 //col == (N+2)-1 ->왼쪽
		 //row == 0->아래로
		 //row == (N+2)-1 ->위로
	}
	
	static boolean dfs(int row,int col,int dir,int cnt) {
		
		if(cnt >N*N) return true;
		int nx = row + dx[dir];
		int ny = col + dy[dir];
		
		if(nx<1 || nx>N || ny<1 || ny>N) {
			resX = nx;
			resY = ny;
			return true;
		}
		
		if(map[nx][ny] !=1) dfs(nx,ny,dir,cnt);
		else dfs(nx,ny,(dir+1)%4,cnt+1);
		
		return false;
	}
}
