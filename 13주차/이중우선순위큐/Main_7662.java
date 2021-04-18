package BOJ;

import java.io.*;
import java.util.*;

//7662 이중 우선순위 큐
public class Main_7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++) {
            TreeMap<Integer,Integer> treeMap = new TreeMap<>();
            int N = Integer.parseInt(br.readLine());

            for(int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String str = st.nextToken();
                int number = Integer.parseInt(st.nextToken());

//                지정된 키의 값을 반환하며 찾지 못하면 기본값(defaultValue)로 지정된 객체를 반환한다
                if(str.equals("I")) treeMap.put(number,treeMap.getOrDefault(number,0)+1);
                else{
                    if(!treeMap.isEmpty()){
                        int num = (number == 1) ? treeMap.lastKey():treeMap.firstKey();
                        if(treeMap.put(num,treeMap.get(num)-1) ==1) treeMap.remove(num);
                    }
                }
            }
            System.out.println(treeMap.size() == 0 ?"EMPTY" : treeMap.lastKey()+" "+treeMap.firstKey());
        }

        }


}
