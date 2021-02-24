import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
/*	ù° �ٿ� 1�� ��Ϲ����� ����, ��° �ٿ� 2�� ��Ϲ����� ����, ��° �ٿ� 3�� ��Ϲ����� ����, ��° �ٿ� 4�� ��Ϲ����� ���°� �־�����.
	���´� 8���� ������ �̷���� �ְ�, 12�ù������ �ð���� ������� �־�����. N���� 0, S���� 1�� ��Ÿ���ִ�.

	�ټ�° �ٿ��� ȸ�� Ƚ�� K(1 �� K �� 100)�� �־�����. ���� K�� �ٿ��� ȸ����Ų ����� ������� �־�����. 
	�� ����� �� ���� ������ �̷���� �ְ�, ù ��° ������ ȸ����Ų ��Ϲ����� ��ȣ, �� ��° ������ �����̴�. ������ 1�� ���� �ð� �����̰�, -1�� ���� �ݽð� �����̴�.
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
		} //��� ���� �Է� �ޱ�
		
		
		int K = Integer.parseInt(br.readLine()); //ȸ�� Ƚ�� �Է�
		
		//1�� �ð� ���� -1�� �ݽð� ����
		//2���̶� 6���� �´�� ��
		for(int k=0;k<K;k++) {			
			st = new StringTokenizer(br.readLine());
			int gear = Integer.parseInt(st.nextToken()); //���° ��Ϲ��� ȸ����ų ��
			int dir = Integer.parseInt(st.nextToken()); //��� �������� ȸ������
			
			rotate(gear, dir);
		}
			
		int result = 0;
		for(int i=1;i<5;i++) {
			result += (list[i].get(0) == 1)?(Math.pow(2, i-1)):0;
		}
		System.out.println(result);
	}
	

	static void rotate(int gear,int dir) {
		//��ġ�� �κ�
		//1�� ��� 2- 2����� 6
		//2����� 2- 3�� ��� 6
		//3����� 2 - 4����� 6
		boolean isTrue[] = new boolean[3];
		isTrue[0] = (list[1].get(2) == list[2].get(6))?true:false;
		isTrue[1] = (list[2].get(2) == list[3].get(6))?true:false;
		isTrue[2] = (list[3].get(2) == list[4].get(6))?true:false;
		
		if(gear == 1) {
			if(!isTrue[0]) {
				//2��° ��� ������
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
			System.out.println("1.���� ���");
			System.out.println(list[gear]);
			int num = list[gear].get(7);
			list[gear].remove(7);
			list[gear].add(0, num);
			System.out.println("�ű�� �� ��");
			System.out.println(list[gear]);
		}
		else {
			System.out.println("-1.���� ���");
			System.out.println(list[gear]);
			int num = list[gear].get(0);
			list[gear].remove(0);
			list[gear].add(num);
			System.out.println("�ű�� �� ��");
			System.out.println(list[gear]);
		}
	}
}
