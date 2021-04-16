import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];
        int result[] = new int[N];

        Stack<Integer> notFound = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());

        for(int i=0;i<N-1;i++){
            int idx = i+1;

            if(arr[i] <arr[idx]){
                while(!notFound.isEmpty()){
                    if(arr[notFound.peek()] >= arr[idx]) break;
                    result[notFound.pop()] = arr[idx];

                }
                result[i] = arr[idx];
            }
            else{
                notFound.push(i);
            }
        }

        while(!notFound.isEmpty()){
            result[notFound.pop()] = -1;
        }
        result[N-1] = -1;

        for(int i=0;i<N;i++) System.out.print(result[i]+" ");
    }
}
