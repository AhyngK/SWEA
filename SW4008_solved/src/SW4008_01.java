import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW4008_01 {
    static int biggest = -1000000;
    static int smallest = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        // 출력할 StringBuilder
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <=testCase ; tc++) {
            int numSize = Integer.parseInt(br.readLine());
            int[] functionsNum = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            // 연산자를 분리해서 리스트에 저장
            List<Integer> functions = new ArrayList<>();
            for (int i = 0; i < functionsNum.length; i++) {
                for (int j = 0; j < functionsNum[i]; j++) {
                    functions.add(i);
                }
            }

            // 방문 여부를 체크하는 배열
            boolean[] check = new boolean[functions.size()];
            Arrays.fill(check,false);

            // 재귀 호출로 모든 경우의 수 해봄
            biggest =-1000000;
            smallest = 1000000;
            int sum = nums[0];
            recursion(0,nums,functions,check,sum);

            //System.out.println("#"+tc+" "+(biggest-smallest));
            sb.append("#").append(tc).append(" ").append(biggest-smallest).append("\n");
        }
        System.out.println(sb);
    }

    static void recursion (int index, int[] nums, List<Integer> functions, boolean[] check, int sum){
        if(index==functions.size()){
            if(sum>biggest){
                biggest=sum;
            }
            if(sum<smallest){
                smallest = sum;
            }
            return;
        }
        // 반복적으로 같은 자리에 같은 연산부호를 하는 경우를 없애기 위한 List, 사용한 연산자를 저장한다
        List<Integer> done = new ArrayList<>();

        for (int i = 0; i < functions.size(); i++) {
            if(!check[i] && !done.contains(functions.get(i))){
                done.add(functions.get(i));
                check[i] = true;
                if(functions.get(i)==0){
                    recursion(index+1,nums,functions,check, plus(sum,nums[index+1]));
                }
                else if (functions.get(i)==1) {
                    recursion(index+1,nums,functions,check, minus(sum,nums[index+1]));
                }
                else if (functions.get(i)==2) {
                    recursion(index+1,nums,functions,check, multiply(sum,nums[index+1]));
                }
                else if (functions.get(i)==3) {
                    recursion(index+1,nums,functions,check, divide(sum,nums[index+1]));
                }
                check[i] = false;
            }
        }
    }
    static int plus(int a, int b){
        return a+b;
    }
    static int minus(int a, int b){
        return a-b;
    }
    static int multiply(int a, int b){
        return a*b;
    }
    static int divide(int a, int b){
        return a/b;
    }
}
