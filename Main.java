import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int arr[] = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());

		int start = 0;
		int end = start + 1;
		int sum = Integer.MAX_VALUE;
		int num1=0,num2=0;
		while(start<N) {
			if(end >=N) {
				start++;
				end = start+1;
			}
			else if(end<N) {
				if(sum>Math.abs(arr[start]+arr[end])) {
					sum = Math.abs(arr[start]+arr[end]);
					num1 = arr[start];
					num2 = arr[end];
				}
				end++;
			}
		}
		
		if(num1>num2) System.out.println(num2+" "+num1);
		else System.out.println(num1+" "+num2);
	}
}
