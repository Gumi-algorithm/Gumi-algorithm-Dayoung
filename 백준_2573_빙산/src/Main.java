import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int map[][];
	static boolean isVisited[][];
	static Queue<Node> q = new LinkedList<>();
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };
	static int result = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		while (true) {
			// 빙산 몇 개인지 탐색
			isVisited = new boolean[N][M];

			iceBerg();

			int cnt = 0;
			// 모두 다 0이면 그냥 탈출하고 0
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {
					if (map[i][j] == 0)
						cnt++;
				}
			}
			if (cnt == N * M) {
				result = 0;
				break;
			}

			cnt = 0;
			for (int i = 1; i < N - 1; i++) {
				if (cnt == 2)
					break;
				for (int j = 1; j < M - 1; j++) {
					if (cnt == 2)
						break;
					if (map[i][j] != 0 && !isVisited[i][j]) {
						if (cnt == 1) {
							++cnt;
							break;
						}
						q.offer(new Node(i, j));
						bfs();
						cnt++;
					}
				}
			}
			if (cnt == 2)
				break;
			result++;
		}

		System.out.println(result);
	}

	static void iceBerg() {
		Stack<Node> stack = new Stack<>();
		Stack<Node> stack2 = new Stack<>();
//		for (int i = 1; i < N - 1; i++) {
//			for (int j = 1; j < M - 1; j++) {
//				if (map[i][j] != 0) {
//					// 빙산이면 사방향 탐색해서 그 수 만큼 줄이기
//					int cnt = 0;
//					for (int d = 0; d < 4; d++) {
//						if (cnt == map[i][j])
//							break; // 현재 빙산의 수와 뺄 숫자가 같으면 포문 벗어나기
//
//						int nx = i + dx[d];
//						int ny = j + dy[d];
//
//						if (nx < 0 || ny < 0 || nx > N || ny > M)
//							continue; // 배열 범위 벗어나면 x
//
//						if (map[nx][ny] == 0)
//							cnt++;
//					}
//					stack.add(new Node(i, j, cnt)); // 현재 빙산의 위치 좌표와 얼마만큼 녹을지 저장
//				}
//			}
//		}

		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++)
				if (map[i][j] != 0)
					stack.push(new Node(i, j));
		}

		while (!stack.isEmpty()) {
			Node node = stack.pop();

			int cnt = 0;
			for (int d = 0; d < 4; d++) {
				if (cnt == map[node.x][node.y])
					break; // 현재 빙산의 수와 뺄 숫자가 같으면 포문 벗어나기
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];

				if (nx < 0 || ny < 0 || nx > N || ny > M)
					continue; // 배열 범위 벗어나면 x

				if (map[nx][ny] == 0)
					cnt++;
			}
			
			stack2.push(new Node(node.x,node.y,cnt));
		}

		while (!stack2.isEmpty()) {
			Node node = stack2.pop();
			map[node.x][node.y] = map[node.x][node.y] - node.weight;
		}
	}

	static void bfs() {

		while (!q.isEmpty()) {
			Node node = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];

				if (nx < 0 || ny < 0 || nx > N || ny > M || isVisited[nx][ny])
					continue;

				if (map[nx][ny] != 0) {
					isVisited[nx][ny] = true;
					q.offer(new Node(nx, ny));
				}
			}
		}
	}

	static class Node {
		int x;
		int y;
		int weight;

		public Node(int x, int y, int weight) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
