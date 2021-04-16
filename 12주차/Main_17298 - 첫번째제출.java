import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> result = new Stack<>();
        Deque<Integer> dq = new ArrayDeque<>();
        int max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) stack.push(Integer.parseInt(st.nextToken()));

        //스택에서 하나씩 꺼내면서 result에 저장하기
        result.push(-1);
        dq.offerFirst(stack.pop());
        max = Math.max(max,dq.peek());

        for(int i=1;i<N;i++){
            int num = stack.pop();
            //System.out.println("현재 pop:"+num);
            //이 수가 현재 max값 보다 크다면 -1넣어주고 max값 갱신해주기
            if(num >=max){
                result.push(-1);
                dq.offerFirst(num);
                max = num;
            }
            else{
                //이 수가 max값 보다는 작으니까 result 앞에서부터 itertor통해서 큰 값 넣어주기
                Iterator<Integer> it = dq.iterator();
                while(it.hasNext()){
                    int number = it.next();
                    //System.out.println("number :"+number);
                    if(num < number){
                        //내 뒤에 더 큰 사람이 있는거니까
                        result.add(number);
                        dq.offerFirst(num);
                        break;
                    }
                }
            }
        }

       for(int i=0;i<N;i++) System.out.print(result.pop()+" ");
    }
}
