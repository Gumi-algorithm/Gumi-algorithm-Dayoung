import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static final int INF = 999999;
	static int adjMatrix[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //도시의 개수
		M = Integer.parseInt(br.readLine()); //버스의 개수
		
		adjMatrix = new int[N][N];

		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1; //시작도시
			int end = Integer.parseInt(st.nextToken())-1; //도착도시
			int price = Integer.parseInt(st.nextToken()); //한번 타는데 필요한 비용
			
			//시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
			if(adjMatrix[start][end] != 0) {
				adjMatrix[start][end]  = (adjMatrix[start][end] >price)?price : adjMatrix[start][end];
			}
			else
				adjMatrix[start][end] = price;
		}
		
		for(int i=0;i<N;++i) {
			for(int j=0;j<N;++j) {
				if(i != j && adjMatrix[i][j]==0) {//자기자신으로의 인접 정보가 아니고 인접해있지 않다면 INF로 채우기
					adjMatrix[i][j]=INF;
				}
			}
		}

		for(int k=0; k<N; ++k) {
			for(int i=0; i<N; ++i) {
				if(i==k) continue; // 출발지와 경유지가 같다면 다음 출발지
				for(int j=0; j<N; ++j) {
					if(i==j) continue; // 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스
					if(adjMatrix[i][j] > adjMatrix[i][k]+adjMatrix[k][j]) {
						adjMatrix[i][j] = adjMatrix[i][k]+adjMatrix[k][j];
					}
				}
			}
		}
		
		print();
	}
	
	private static void print() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(adjMatrix[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
