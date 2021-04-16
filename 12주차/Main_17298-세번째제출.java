package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];
        int result[] = new int[N];

        Stack<Integer> notFound = new Stack<>();
        Stack<Integer> idx = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());

//        for(int i=0;i<N-1;i++){
//            int idx = i+1;
//
//            if(arr[i] <arr[idx]){
//                while(!notFound.isEmpty()){
//                    if(arr[notFound.peek()] >= arr[idx]) break;
//                    result[notFound.pop()] = arr[idx];
//
//                }
//                result[i] = arr[idx];
//            }
//            else{
//                notFound.push(i);
//            }
//        }
//
//        while(!notFound.isEmpty()){
//            result[notFound.pop()] = -1;
//        }
//        result[N-1] = -1;

        for(int i=0;i<N;++i){
            int index = i;

            while((notFound.size()>0) && (notFound.peek()<arr[index])){
                result[idx.peek()] = arr[index];
                notFound.pop();
                idx.pop();
            }
            notFound.push(arr[index]);
            idx.push(index);

        }

        while(idx.size()>0){
            result[idx.peek()] = -1;
            idx.pop();
        }

        result[N-1] = -1;

        for(int i=0;i<N;i++) System.out.print(result[i]+" ");
    }
}
