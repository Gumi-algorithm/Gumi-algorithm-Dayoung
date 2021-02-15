import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_11559 {
	//뿌요뿌요
	static char map[][] = new char[12][6];
	static int visited[][] = new int[12][6];
	static Queue<int[]> q = new LinkedList<>();
	static Queue<int []> q2 = new LinkedList<>();
	static int totalNum=0;
	static int dx[] = {0,0,-1,1}; //상하
	static int dy[] = {-1,1,0,0}; //좌우
	static boolean isTrue = true;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int i=0;i<12;i++) {
			 String str = br.readLine();
			for(int j=0;j<6;j++) 
				map[i][j] = str.charAt(j);
		} //미로 입력
		

		while(isTrue) {
			puyopuyo();
			if(isTrue == true)
				getMap();
		}
		
		System.out.println(totalNum);
	}

	static void puyopuyo() {
		//미로 한번 싹 돌면서 뿌요뿌요 검사하기
		isTrue = false;
		//System.out.println("뿌요뿌요 함수");

		for(int i=0;i<12;i++) {
			for(int j=0;j<6;j++) {
				if(map[i][j] !='.') {
					//빈칸 아닌 무슨 색깔 만나면
					//사방위 탐색하면서 4개 이상인지 확인하기
					//System.out.println(map[i][j]);
					q.offer(new int[] {i,j});
					q2.offer(new int[] {i,j});
					visited[i][j] = 1;
					
					while(!q.isEmpty()) {
						int dir[] = q.poll();
						for(int d=0;d<4;d++) {
							int nx = dir[0]+dx[d];
							int ny = dir[1]+dy[d];
							
							if(nx<0 || nx>=map.length || ny<0 || ny>=map[0].length) continue;
							
							if(visited[nx][ny] ==0 && map[nx][ny] == map[i][j]) {
								//방문안하고 사방탐색한게 찾는 색이랑 같으면
								q.offer(new int[] {nx,ny});
								q2.offer(new int[] {nx,ny});
								visited[nx][ny] = 1; //큐에 넣어주고 방문 체크 해주기
							}
						}
					}
					
					//큐가 다 돌고 나면 
					if(q2.size()>=4) {
						//뿌요뿌요로 없앨 수 있는 사이즈면
						//System.out.println("뿌요뿌요 시작");
						isTrue = true;
						while(!q2.isEmpty()) {
							//현재 map에 뿌요뿌요 없애주기
							int dir[] = q2.poll();
							map[dir[0]][dir[1]] ='.';
						}
					}
					
					q.clear();
					q2.clear();
					//큐 두 개 비워 주고 다시 처음부터 돌면서 비울 수 있는거 찾기
				}
			}
		}
		//모든 뿌요뿌요가 끝나면 +1해주기
		if(isTrue)//연쇄가 일어났으면 totalNum +1해주기
			totalNum++;
		
		//뿌요뿌요 visited정리
		for(int i=0;i<12;i++) {
			for(int j=0;j<6;j++)
				visited[i][j] = 0;
		}
	}
	
	static void getMap() {
		//뿌요뿌요로 지워진 배열 정리
		for(int i=10;i>=0;i--) {
			for(int j=0;j<6;j++) {
				if(map[i][j] !='.' && map[i+1][j]=='.') {
					int idx = i;
					while((idx+1)<=11 && map[idx+1][j] == '.') {
						//밑에가 빈칸일때까지 계속 돌면서
						if((idx+1)>11)
							break;
						idx = idx+1;
					}
					
					map[idx][j] = map[i][j];
					map[i][j] = '.';
					
				}
			}
		}
		
/*		System.out.println("뿌요뿌요 후  배열");
		for(int i=0;i<12;i++)
			System.out.println(Arrays.toString(map[i]));*/
	}
}
