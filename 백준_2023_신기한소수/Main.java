import java.util.Scanner;

public class Main {

	static int N;
	static int startNum;
	static int endNum;
	static int isPrime;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		StringBuilder sb = new StringBuilder();
		sb.append(1);
		for(int i=1;i<N;i++) sb.append(0);
		startNum = Integer.parseInt(sb.toString());
		
		sb = new StringBuilder();
		for(int i=0;i<N;i++) sb.append(9);
		endNum = Integer.parseInt(sb.toString());

		//N자리 숫자/(10*N)
		//N자리 숫자/(10*N-1)
		//N자리 숫자/(10*N-2)
		//N자리 숫자/(10*N-3) ..->N-(N-1)까지 자리수 구하면서 그 자리수가 다 소수인지 확인
		
		//dfs로 하나씩 넣으면서 소수인지 아닌지 구하기?
		
		int divideNum = 1;
		for(int i=N-1;i>=1;i--) divideNum*=10;
		for(int i=startNum;i<=endNum;i++) {
			isPrime = 0;
			dfs(i,N-1);
			if(isPrime == N) System.out.println(i);
		} //시간 초과

	}
	
	static void dfs(int num,int N) {
		int divideNum = 1;
		for(int i=N;i>=1;i--) divideNum*=10;
		
		int findNum = num / divideNum; //이 숫자가 소수인지 아닌지 찾아야함

		boolean isTrue = true;
		if(findNum == 0 || findNum ==1) return;
		
		//만약 끝자리가 2,4,6,8,0으로 끝나도 소수 x
		if(findNum!=2 && (findNum%10) %2 ==0) return;
		for(int i=2;i<=Math.sqrt(findNum);i++) { 
			if(findNum % i ==0) return;
		}
		
		if(isTrue) isPrime++;
		
		if(N>0) dfs(num,N-1); //1일때 까지 돌려주기

	}
	
}
