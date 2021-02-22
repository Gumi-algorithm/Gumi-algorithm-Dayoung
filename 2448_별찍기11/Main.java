import java.util.Scanner;
//별찍기 11

// i%3 ==0 별 하나 
// i%3 ==1 별 두개
// i%3 ==2 별 세개


//https://ansohxxn.github.io/boj/2448/ -> 참고한 사이트
public class Main {
	static int N;
//	static char map[][];
	static boolean star[][];
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		N = sc.nextInt(); //전체 N*N 배열
		//map = new char[N][2*N-1];
		star = new boolean[N][2*N-1];
		tri(0,N-1,N);
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N*2-1;j++)
				sb.append(star[i][j]?"*":" ");
			sb.append("\n");
		}
		
		System.out.println(sb);
		/*for(int i=0;i<N;i++) {
			for(int j=0;j<2*N-1;j++)
				System.out.print(map[i][j]);
			System.out.println();
		}*/
//		for(int i=0;i<N;i++)
//			System.out.println(Arrays.toString(map[i]));
//		getStar(0,0,N/3);
	}
/*	static void draw(int row,int col) {
		map[row][col]='*';
		
		map[row+1][col+1] = '*';
		map[row+1][col-1] = '*';
		
		for(int i=0;i<5;i++)
			map[row+2][col-2+i] = '*';
	}*/
	
	static void tri(int row,int col,int len) {
		if(len == 3) {
//			map[row][col]='*';
			star[row][col] = true;
			
//			map[row+1][col+1] = '*';
//			map[row+1][col-1] = '*';
			
			star[row+1][col+1] = true;
			star[row+1][col-1] = true;
			
//			for(int i=0;i<5;i++)
//				map[row+2][col-2+i] = '*';
			
			for(int i=0;i<5;i++)
				star[row+2][col-2+i] = true;
			return;
		}
		
		tri(row,col,len/2);
		tri(row+(len/2),col-(len/2),len/2);
		tri(row+(len/2),col+(len/2),len/2);
	}
/*	static void getStar(int row,int col,int N) {
		if(N == 3) {
			for(int i=row;i<row+3;i++) {
				for(int j=col;j<col+5;j++) {
					if((i%3 ==0)) {
						if(j == (col+5)/2) System.out.print("*");
						else System.out.print(" ");
					}
					else if((i%3)==1) {
						if(j%2 ==0) System.out.print(" ");
						else System.out.print("*");
					}
					else  System.out.print("*");
				}
				System.out.println();
			}
			
			return;
		}
		N--;
		System.out.println(N);
		getStar(row+3,col,N);
	}*/
}
