import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
/*	첫째 줄에 1번 톱니바퀴의 상태, 둘째 줄에 2번 톱니바퀴의 상태, 셋째 줄에 3번 톱니바퀴의 상태, 넷째 줄에 4번 톱니바퀴의 상태가 주어진다.
	상태는 8개의 정수로 이루어져 있고, 12시방향부터 시계방향 순서대로 주어진다. N극은 0, S극은 1로 나타나있다.

	다섯째 줄에는 회전 횟수 K(1 ≤ K ≤ 100)가 주어진다. 다음 K개 줄에는 회전시킨 방법이 순서대로 주어진다. 
	각 방법은 두 개의 정수로 이루어져 있고, 첫 번째 정수는 회전시킨 톱니바퀴의 번호, 두 번째 정수는 방향이다. 방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향이다.
*/

	static ArrayList<Integer> list[] = new ArrayList[5];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=1;i<5;i++) {
			st = new StringTokenizer(br.readLine());
			char arr[] = st.nextToken().toCharArray();
			list[i] = new ArrayList<>();

			for(int j=0;j<8;j++)
				list[i].add(arr[j]-'0');
		} //톱니 바퀴 입력 받기
		
		
		int K = Integer.parseInt(br.readLine()); //회전 횟수 입력
		
		//1은 시계 방향 -1을 반시계 방향
		//2번이랑 6번이 맞닿는 곳
		for(int k=0;k<K;k++) {			
			st = new StringTokenizer(br.readLine());
			int gear = Integer.parseInt(st.nextToken()); //몇번째 톱니바퀴 회전시킬 지
			int dir = Integer.parseInt(st.nextToken()); //어느 방향으로 회전할지
			
			rotate(gear, dir);
		}
			
		int result = 0;
		for(int i=1;i<5;i++) {
			result += (list[i].get(0) == 1)?(Math.pow(2, i-1)):0;
		}
		System.out.println(result);
	}
	

	static void rotate(int gear,int dir) {
		//겹치는 부분
		//1번 톱니 2- 2번톱니 6
		//2번톱니 2- 3번 톱니 6
		//3번톱니 2 - 4번톱니 6
		boolean isTrue[] = new boolean[3];
		isTrue[0] = (list[1].get(2) == list[2].get(6))?true:false;
		isTrue[1] = (list[2].get(2) == list[3].get(6))?true:false;
		isTrue[2] = (list[3].get(2) == list[4].get(6))?true:false;
		
		if(gear == 1) {
			if(!isTrue[0]) {
				//2번째 톱니 돌리기
				rotate2(2, dir*(-1));
				int temp = dir*(-1);
				if(!isTrue[1]) {
					rotate2(3, temp*(-1));
					temp = temp * (-1);
					if(!isTrue[2])
						rotate2(4, temp*(-1));
				}
				rotate2(gear,dir);
			}
		}
		
		else if(gear == 2) {
			if(!isTrue[0])
				rotate2(1, dir*(-1));
			if(!isTrue[1]){
				rotate2(3,dir*(-1));
				int temp = dir * (-1);
				if(!isTrue[2])
					rotate2(4, temp*(-1));
			}
			rotate2(gear, dir);
		}
		
		
		else if(gear == 3) {
			if(!isTrue[1]) {
				rotate2(2, dir*(-1));
				int temp = dir*(-1);
				if(!isTrue[0])
					rotate2(1, temp*(-1));
			}
			if(!isTrue[2])
				rotate2(4,dir*(-1));
			
			rotate2(gear, dir);
		}
		else {
			if(!isTrue[2]) {
				rotate2(3, dir*(-1));
				int temp = dir*(-1);
				if(!isTrue[1]) {
					rotate2(2, temp*(-1));
					temp = temp * (-1);
					if(!isTrue[0])
						rotate2(1, temp*(-1));
				}
			}
			rotate2(gear, dir);
		}
	}
	
	static void rotate2(int gear,int dir) {
		if(dir == 1) {
			System.out.println("1.현재 기어");
			System.out.println(list[gear]);
			int num = list[gear].get(7);
			list[gear].remove(7);
			list[gear].add(0, num);
			System.out.println("옮기고 난 후");
			System.out.println(list[gear]);
		}
		else {
			System.out.println("-1.현재 기어");
			System.out.println(list[gear]);
			int num = list[gear].get(0);
			list[gear].remove(0);
			list[gear].add(num);
			System.out.println("옮기고 난 후");
			System.out.println(list[gear]);
		}
	}
}
