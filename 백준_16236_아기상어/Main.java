import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N; // 공간의 크기
	static int time = 0;
	static int sharkSize = 2; // 현재 아기 상어의 크기
	static int sharkUp = 0; // 상어 크기 만큼 먹었을 때 크기 UP 해주기
	static int map[][];
	static boolean isVisited[][];
	static boolean isTrue = false;
	static int shark[]; // 아기 상어 위치 저장할 배열
	static ArrayList<Node> list = new ArrayList<>();
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		map = new int[N][N];
		isVisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9)
					shark = new int[] { i, j }; // 아기상어 위치 입력
				if (map[i][j] >= 1 && map[i][j] <= 6)
					list.add(new Node(i, j, map[i][j])); // 물고기들 위치와 size setting

			}
		} // 배열 입력

		// 이거를 반복하기
		// 더이상 움직일 수 없을 때 까지
		while (true) {
//			System.out.println("현재 list");
//			for(int i=0;i<list.size();i++) {
//				System.out.println(list.get(i).size);
//			}
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (sharkSize > list.get(i).size)
					num++;
			}

			if (num == 0 || list.size() ==0) {
				System.out.println(time);
				break;
			}

			Node getMin = new Node(0, 0, Integer.MAX_VALUE);
			int idx = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).size >= sharkSize) continue;

//				System.out.println("ㅈㅣ금 찾을 사이즈 "+list.get(i).size);
//				System.out.println("지금 물고기 위치 :"+list.get(i).x+" "+list.get(i).y);
				isVisited = new boolean[N][N];
				q.offer(new Node(list.get(i).x, list.get(i).y, 0));
				isTrue = false;
				int size = Integer.MAX_VALUE;
				//System.out.println("현재 물고기 :" + list.get(i).x + " " + list.get(i).y);
				while (!q.isEmpty()) {
					//if (isTrue)
					//	break;
					Node node = q.poll();
//					System.out.println(node.x + " " + node.y);

					for (int d = 0; d < 4; d++) {
						int nx = node.x + dx[d];
						int ny = node.y + dy[d];

						if (nx < 0 || nx >= N || ny < 0 || ny >= N || isVisited[nx][ny])
							continue;
						if (map[nx][ny] == 9) {
//							System.out.println("size :" + (node.size + 1));
							size = (size>node.size+1)?node.size+1:size;
							//isTrue = true;
							break;
						}
						if (map[nx][ny] > sharkSize)
							continue;

						isVisited[nx][ny] = true;
						q.offer(new Node(nx, ny, node.size + 1));
					}

				}
				q.clear();
				// 최단 거리 구했음
				if (getMin.size > size) { //내가 현재 구한 거리가 최소값보다 작으면 갱신
					getMin = new Node(list.get(i).x, list.get(i).y, size);
//					System.out.println("최종최단 거리 :"+getMin.size);
					idx = i;
				} else if (getMin.size == size) { //최소값이 같으면
					if (getMin.x > list.get(i).x) {//더 위에 있는거
						getMin = new Node(list.get(i).x, list.get(i).y, size);
						idx = i;
					} else if (getMin.x == list.get(i).x && getMin.y > list.get(i).y) { //더 왼쪽에 있는거
						getMin = new Node(list.get(i).x, list.get(i).y, size);
						idx = i;
					}
				}
			}
			//System.out.println("현재 지울 물고기 위치:" + getMin.x + " " + getMin.y);
			//System.out.println("idx 값 :"+idx);
			time += getMin.size;
			map[getMin.x][getMin.y] = 0;
			map[shark[0]][shark[1]] =0;
			map[getMin.x][getMin.y] = 9;
			shark[0] = getMin.x;
			shark[1] = getMin.y;
			list.remove(idx); // 리스트에 있는 물고기 하나 지워주고
			sharkUp++; // 먹은 만큼 ++
//			System.out.println("몇개 먹은지: "+sharkUp);
			if (sharkSize == sharkUp) { // 먹은 개수랑 사이즈랑 똑같아 지면 크기 키워주기
				sharkSize++;
				sharkUp = 0;
//				System.out.println("상어 크기 키워줌 :"+sharkSize);
			}
			
//			System.out.println("지운 후 배열");
//			for(int i=0;i<N;i++)
//				System.out.println(Arrays.toString(map[i]));
			//System.out.println(time);
		}
		// 현재 리스트 중에서 제일 가꺼운거 자리 구하기
	}


	static class Node {
		int x;
		int y;
		int size;

		public Node() {
		};

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}
}