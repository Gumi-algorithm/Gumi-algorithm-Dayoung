import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static int weight[];
	static int robot[];
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		weight = new int[2 * N];
		robot = new int[N];// 1이 존재하는거

		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		while (true) {
			result++;
			
			int tmp = weight[(2 * N) - 1]; //제일 끝에 값
			for (int i = (2 * N) - 1; i >= 1; i--) {
				weight[i] = weight[i - 1]; //벨트 돌리기
			}
			weight[0] = tmp; //첫칸에는 밑에서 올라오는 값

			// 로봇도 벨트에 따라 이동
			for (int i = N - 1; i >= 1; i--) {
				robot[i] = robot[i - 1];
			}
			robot[0] = 0; // 로봇도 이동
			
			for (int i = N - 1; i >= 0; i--) {
				if (i == N - 1) {
					robot[i] = 0;
					continue;
				}
				if (robot[i] == 1 && robot[i + 1] == 0 && weight[i + 1] > 0) {
					robot[i] = 0;
					robot[i + 1] = 1;
					weight[i + 1]--;
				}
			}

			if (weight[0] > 0 && robot[0] == 0) {
				weight[0]--;
				robot[0] = 1;
			}

			int cnt = 0;
			for (int i = 0; i < 2 * N; i++) {
				if (weight[i] == 0) {
					cnt++;
				}
				if (cnt >= K) {
					System.out.println(result);
					return;
				}
			}
		}
	}
}