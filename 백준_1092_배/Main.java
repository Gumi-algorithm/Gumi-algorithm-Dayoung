import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

//	지민이는 항구에서 일한다. 그리고 화물을 배에 실어야 한다. 
//	모든 화물은 박스에 안에 넣어져 있다. 항구에는 크레인이 N대 있고, 1분에 박스를 하나씩 배에 실을 수 있다. 
//	모든 크레인은 동시에 움직인다.
//
//	각 크레인은 무게 제한이 있다. 
//	이 무게 제한보다 무거운 박스는 크레인으로 움직일 수 없다. 
//	모든 박스를 배로 옮기는데 드는 시간의 최솟값을 구하는 프로그램을 작성하시오.
	
	static int N; //크레인 개수
	static int M; //박스의 수
	static ArrayList<Integer> crane = new ArrayList<>();
	static ArrayList<Integer> box = new ArrayList<>();
	static int count =0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for(int i=0;i<N;i++) crane.add(Integer.parseInt(st.nextToken()));
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<M;i++) box.add(Integer.parseInt(st.nextToken()));
		

		Collections.sort(crane,Collections.reverseOrder());
		Collections.sort(box,Collections.reverseOrder());
		
		if(crane.get(0) <box.get(0) ) { //현재 크레인의 최대 무게보다 박스의 최대무게가 크면
			System.out.println("-1");
		}
		else {
			while(box.size()>0) {
				int boxSize =0;
				for(int i=0;i<box.size();i++) {
					if(boxSize == N) break; //크레인 다 쓰면 튀튀
					
					if(crane.get(boxSize) >= box.get(i)) {//현재 박스에 담을 수 있으면
						boxSize++; //크레인 하나 썼다구 해주고
						box.remove(i);
						if(box.size() == 0) break; //박스 다씀
						i = i-1; //하나 지워줬으니까 앞으로 땡겨줘야함
					}
				}
				count++;
			}
			
			System.out.println(count);
		}
	}

}
