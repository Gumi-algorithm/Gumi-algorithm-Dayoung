import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Main {

	static int N;
	static int result = 0;
	static int sum = 0;
	static int time[],price[],date[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		time = new int[N+1];
		price = new int[N+1];
		date = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
			
			date[i] = price[i];
		}
		
		//date[i] 에는 현재 날짜까지의 최대 금액? 저장하기
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=i;j++) {
				if(i-j >=time[j])
					date[i] = Math.max(date[i], price[i]+date[j]);
			}
		}
		
		for(int i=1;i<=N;i++) {
			if(i+time[i]<=N+1) {
				result = (result<date[i])?date[i]:result;
			}
		}
		
		System.out.println(result);
	}
	

}
