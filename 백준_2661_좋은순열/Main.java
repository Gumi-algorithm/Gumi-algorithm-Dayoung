import java.util.Scanner;

public class Main {
	static int N;
	static String res="";
	static boolean isTrue;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		dfs("1",1); //1번째 위치에 1 넣기
		
		//System.out.println(result);
	}

	static void dfs(String res,int cnt) {//좋은 순열 인 것들만 넘어옴
		if(isTrue) return;
		if(cnt == N) {
			System.out.println(res);
			isTrue = true;
			return;
		}
		
		for(int i=1;i<=3;i++) {
			if(isPossible(res+i)) dfs(res+i,cnt+1);
		}
	}
	

	static boolean isPossible(String res) {
		//전체 길이의 반틈만 비교
		int div = res.length() /2;
		int beginIndex = res.length() -1; //전체index 바로 앞에꺼
		int endIndex = res.length(); //마지막 index
		for(int i=1;i<=div;i++) { //1부터 div길이까지 비교해주기
			if(res.substring(beginIndex-i, endIndex-i).equals(res.substring(beginIndex, endIndex))) return false;
			beginIndex -=1;
		}
		return true;
	}
}
