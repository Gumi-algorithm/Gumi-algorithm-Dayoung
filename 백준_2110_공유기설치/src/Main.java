import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //집의 개수
		int C = Integer.parseInt(st.nextToken()); //공유기 개수
		
		int arr[] = new int[N]; //집의 좌표 저장할 배열
		
		for(int i=0;i<N;i++) arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		int min = 1; //최소 거리
		int max = arr[N-1] -arr[0]; //가능한 최대거리
		
		int len=0;
		int result = 0;
		
		while(min<=max) {
			int mid = (min + max)/2;
			int start = arr[0];
			int cnt = 1;
			
			for(int i=1;i<N;i++) {
				len = arr[i] - start;
				if(mid<=len) {
					cnt++;
					start = arr[i];
				}
			}
			
			if(cnt>= C) {
				//공유기의 수를 줄이기
				result = mid;
				min = mid+1;
			}
			else {//공유기 수 늘ㄹ리기
				max = mid-1;
			}
		}
		
		System.out.println(result);
	}
}
