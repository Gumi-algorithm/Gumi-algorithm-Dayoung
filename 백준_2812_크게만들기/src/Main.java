import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N =Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Deque<Character> dq = new ArrayDeque<>();
		char c[] = new char[N];
		
		c  = br.readLine().toCharArray();
		
		for(int i=0;i<c.length;i++) {
			while(K>0 && !dq.isEmpty() && dq.getLast()<c[i]) {
				dq.removeLast();
				K--;
			}
			dq.addLast(c[i]);
		}
		
		while(dq.size()>K)
			System.out.print(dq.poll());
	}

}
