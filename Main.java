import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int arr[] = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
		
//		System.out.println(Arrays.toString(arr));
		
		int start = 0;
		int end = 0;
		int minLen = Integer.MAX_VALUE;
		int sum = 0;
		
		while(true) {
			if(sum >=S) {
				minLen = Math.min(minLen, end-start);
				sum -= arr[start++];
			}
			else if(end == N) break;
			else if(sum <S) {
				sum +=arr[end++];
			}
		}
		
		System.out.println(minLen<Integer.MAX_VALUE?minLen : "0");
	}

}
