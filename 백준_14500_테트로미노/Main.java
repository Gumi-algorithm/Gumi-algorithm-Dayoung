import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int map[][];
	static int res;

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
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				tetromino1(i, j);
				tetromino2(i, j);
				tetromino3(i, j);
				tetromino4(i, j);
				tetromino5(i, j);
			}
		}

		System.out.println(res);
	}

	static void tetromino1(int row, int col) {
		//0 0 0 0
		//2가지 경우
		int sum = 0;
		if((col+3)<M) {
			sum  = map[row][col]+map[row][col+1]+map[row][col+2]+map[row][col+3];
			res = Math.max(res, sum);
		}
		
		if((row+3)<N) {
			sum = map[row][col]+map[row+1][col]+map[row+2][col]+map[row+3][col];
			res = Math.max(res, sum);
		}
	}

	static void tetromino2(int row, int col) {
		int sum =0;
		if(row+1<N && col+1<M) {
			sum += map[row][col];
			sum +=map[row+1][col];
			sum +=map[row][col+1];
			sum +=map[row+1][col+1];
			res = Math.max(res, sum);
		}
		
		
	}

	static void tetromino3(int row, int col) {
		int sum = 0;
		if((row+2)<N && (col+1)<M) {
			sum = map[row][col] + map[row+1][col] + map[row+2][col] + map[row+2][col+1];
			res = Math.max(res, sum);
		}
		
		if((row+1)<N && (col+2)<M) {
			sum = map[row][col]+map[row][col+1]+map[row][col+2]+map[row+1][col];
			res = Math.max(res, sum);
		}
		
		if((row+2)<N && (col+1)<M) {
			sum = map[row][col]+map[row][col+1]+map[row+1][col+1]+map[row+2][col+1];
			res = Math.max(res, sum);
		}
		
		if((row+1)<N && (col-2)>=0) {
			sum = map[row][col]+map[row+1][col]+map[row+1][col-1]+map[row+1][col-2];
			res = Math.max(res, sum);
		}
		
		if((row+2)<N && (col-1)>=0) {
			sum =map[row][col]+map[row+1][col]+map[row+2][col]+map[row+2][col-1];
			res = Math.max(res, sum);
		}
		
		if((row-1)>=0 && (col+2)<M) {
			sum = map[row][col]+map[row][col+1]+map[row][col+2]+map[row-1][col];
			res = Math.max(res, sum);
		}
		
		if((row+2)<N && (col+1)<M) {
			sum = map[row][col]+map[row+1][col]+map[row+2][col]+map[row][col+1];
			res = Math.max(res, sum);
		}
		if((row+1)<N && (col+2)<M) {
			sum = map[row][col]+map[row][col+1]+map[row][col+2]+map[row+1][col+2];
			res = Math.max(res, sum);
		}
	}

	static void tetromino4(int row, int col) {

		int sum = 0;
		if((row+2)<N && (col+1)<M) {
			sum = map[row][col]+map[row+1][col]+map[row+1][col+1]+map[row+2][col+1];
			res = Math.max(res, sum);
		}
		
		if((row-1)>=0 && (col+2)<M) {
			sum = map[row][col]+map[row][col+1]+map[row-1][col+1]+map[row-1][col+2];
			res = Math.max(res, sum);
		}
		
		if((row+2)<N && (col-1)>=0) {
			sum = map[row][col]+map[row+1][col]+map[row+1][col-1]+map[row+2][col-1];
			res = Math.max(res, sum);
		}
		
		if((row+1)<N && (col+2)<M) {
			sum = map[row][col]+map[row][col+1]+map[row+1][col+1]+map[row+1][col+2];
			res = Math.max(res, sum);
		}
	}

	static void tetromino5(int row, int col) {
		int sum = 0;
		if((row+1)<N && (col+2)<M) {
			sum = map[row][col]+map[row][col+1]+map[row][col+2]+map[row+1][col+1];
			res = Math.max(res, sum);
		}
		
		if((row+2)<N && (col-1)>=0) {
			sum = map[row][col]+map[row+1][col]+map[row+2][col]+map[row+1][col-1];
			res = Math.max(res, sum);
		}
		
		if((row-1)>=0 && (col+2)<M) {
			sum = map[row][col]+map[row][col+1]+map[row][col+2]+map[row-1][col+1];
			res = Math.max(res, sum);
		}
		
		if((row+2)<N && (col+1)<M) {
			sum = map[row][col]+map[row+1][col]+map[row+2][col]+map[row+1][col+1];
			res = Math.max(res, sum);
		}
	}
}
