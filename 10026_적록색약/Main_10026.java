import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_10026 {

	static char grid[][];
	static boolean isVisited[][];
	static int zone,colorZone;
	static int dx[] = {0,0,-1,1}; //����
	static int dy[] = {-1,1,0,0}; //�¿�
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		grid = new char[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for(int j=0;j<N;j++)
				grid[i][j] = str.charAt(j);
		}//grid �Է�

		Queue<int []> q = new LinkedList<>();
		
		isVisited = new boolean[N][N];
		
		//���ϻ����� ���ƴ� ���
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!isVisited[i][j]) {
					q.offer(new int[] {i,j});
					zone++;
				} //�湮���� ���� ���̸� ť�� ����
				while(!q.isEmpty()) {
					//ť ���鼭 �����¿� Ȯ���ϱ�
					int idx[] = q.poll();
					
					for(int d=0;d<4;d++) {
						int nx = idx[0]+dx[d];
						int ny = idx[1]+dy[d];
						
						if(nx<0 || nx>=N || ny<0 || ny>=N) continue; //���� ���̸� �׳� ����ġŰ
						if(isVisited[nx][ny]) continue; //�湮�� ���̸� �׳� ����ġ��
						if((grid[nx][ny] == grid[i][j])) {
							//�湮 ���ϰ� visited�̸�
							q.offer(new int[] {nx,ny});
							isVisited[nx][ny] = true;
						}	
					}
				}
			}
		}
		
		isVisited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(grid[i][j]=='G')
					grid[i][j] = 'R';
			}
		}
		//���ϻ����� ���
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!isVisited[i][j]) {
					q.offer(new int[] {i,j});
					colorZone++;
				} //�湮���� ���� ���̸� ť�� ����
				
				while(!q.isEmpty()) {
					//ť ���鼭 �����¿� Ȯ���ϱ�
					int idx[] = q.poll();
					
					for(int d=0;d<4;d++) {
						int nx = idx[0]+dx[d];
						int ny = idx[1]+dy[d];
						
						if(nx<0 || nx>=N || ny<0 || ny>=N) continue; //���� ���̸� �׳� ����ġŰ
						if(isVisited[nx][ny]) continue; //�湮�� ���̸� �׳� ����ġ��
						if((grid[nx][ny] == grid[i][j])) {
							//�湮 ���ϰ� visited�̸�
							q.offer(new int[] {nx,ny});
							isVisited[nx][ny] = true;
						}	
					}
				}
			}
		}
		
		System.out.println(zone +" "+colorZone);
	}
}
