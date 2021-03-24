import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long dp[][] = new long[3][N + 1];
		long MOD = 1000000007L;

		if (N >= 1) {
			dp[0][1] = 0;
		}
		if (N >= 2) {
			dp[0][2] = 1;
			dp[1][2] = 1;
			dp[2][2] = 0;
		}

		for (int i = 3; i <= N; i++) {
			dp[0][i] = (dp[1][i - 1] + dp[2][i - 1])%MOD;
			dp[1][i] = (dp[0][i - 1] + dp[2][i - 1])%MOD;
			dp[2][i] = (dp[0][i - 1] + dp[1][i - 1])%MOD;
		}

		System.out.println(dp[0][N]%MOD);
	}
}