import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_11559 {
	//�ѿ�ѿ�
	static char map[][] = new char[12][6];
	static int visited[][] = new int[12][6];
	static Queue<int[]> q = new LinkedList<>();
	static Queue<int []> q2 = new LinkedList<>();
	static int totalNum=0;
	static int dx[] = {0,0,-1,1}; //����
	static int dy[] = {-1,1,0,0}; //�¿�
	static boolean isTrue = true;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int i=0;i<12;i++) {
			 String str = br.readLine();
			for(int j=0;j<6;j++) 
				map[i][j] = str.charAt(j);
		} //�̷� �Է�
		

		while(isTrue) {
			puyopuyo();
			if(isTrue == true)
				getMap();
		}
		
		System.out.println(totalNum);
	}

	static void puyopuyo() {
		//�̷� �ѹ� �� ���鼭 �ѿ�ѿ� �˻��ϱ�
		isTrue = false;
		//System.out.println("�ѿ�ѿ� �Լ�");

		for(int i=0;i<12;i++) {
			for(int j=0;j<6;j++) {
				if(map[i][j] !='.') {
					//��ĭ �ƴ� ���� ���� ������
					//����� Ž���ϸ鼭 4�� �̻����� Ȯ���ϱ�
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
								//�湮���ϰ� ���Ž���Ѱ� ã�� ���̶� ������
								q.offer(new int[] {nx,ny});
								q2.offer(new int[] {nx,ny});
								visited[nx][ny] = 1; //ť�� �־��ְ� �湮 üũ ���ֱ�
							}
						}
					}
					
					//ť�� �� ���� ���� 
					if(q2.size()>=4) {
						//�ѿ�ѿ�� ���� �� �ִ� �������
						//System.out.println("�ѿ�ѿ� ����");
						isTrue = true;
						while(!q2.isEmpty()) {
							//���� map�� �ѿ�ѿ� �����ֱ�
							int dir[] = q2.poll();
							map[dir[0]][dir[1]] ='.';
						}
					}
					
					q.clear();
					q2.clear();
					//ť �� �� ��� �ְ� �ٽ� ó������ ���鼭 ��� �� �ִ°� ã��
				}
			}
		}
		//��� �ѿ�ѿ䰡 ������ +1���ֱ�
		if(isTrue)//���Ⱑ �Ͼ���� totalNum +1���ֱ�
			totalNum++;
		
		//�ѿ�ѿ� visited����
		for(int i=0;i<12;i++) {
			for(int j=0;j<6;j++)
				visited[i][j] = 0;
		}
	}
	
	static void getMap() {
		//�ѿ�ѿ�� ������ �迭 ����
		for(int i=10;i>=0;i--) {
			for(int j=0;j<6;j++) {
				if(map[i][j] !='.' && map[i+1][j]=='.') {
					int idx = i;
					while((idx+1)<=11 && map[idx+1][j] == '.') {
						//�ؿ��� ��ĭ�϶����� ��� ���鼭
						if((idx+1)>11)
							break;
						idx = idx+1;
					}
					
					map[idx][j] = map[i][j];
					map[i][j] = '.';
					
				}
			}
		}
		
/*		System.out.println("�ѿ�ѿ� ��  �迭");
		for(int i=0;i<12;i++)
			System.out.println(Arrays.toString(map[i]));*/
	}
}
