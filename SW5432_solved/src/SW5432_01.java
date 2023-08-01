import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SW5432_01 {
    static int[] cutted;
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase ; tc++) {
            sum=0;
            String[] stick = br.readLine().split("");
            splitStick(stick);
//            System.out.println(Arrays.toString(cutted));
            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void splitStick(String[] stick){
        Stack<Integer> leftBrackets = new Stack<>();
        cutted = new int[stick.length];
        int count = 1;

        // Cutting Process
        for (int i = 0; i < stick.length; i++) {
            // left bracket cases
            if(stick[i].equals("(")){
                leftBrackets.add(i);
            }

            // right bracket cases
            else{
                // 1. before was left bracket -> razer
                if(i!=0 && stick[i-1].equals("(")){
                    leftBrackets.pop();
                    cutted[i] = -1;
                    cutted[i-1] = -1;
                }
                // 2. one stick
                else {
                    cutted[leftBrackets.pop()] = count;
                    cutted[i] = count;
                    count++;
                }
            }
        }
        countStick(count);
    }

    static void countStick(int count){
        int[] eachStick = new int[count];
        boolean[] stickCheck = new boolean[count];
        Arrays.fill(stickCheck,false);
        List<Integer> currentStick = new ArrayList<>();

        for (int i = 0; i < cutted.length; i++) {
            // met the stickEnd
            if(cutted[i]!=-1 && stickCheck[cutted[i]]){
                eachStick[cutted[i]] = currentStick.get(currentStick.size()-1)/2+1;
                currentStick.remove(currentStick.size()-1);
            }
            // met the stickFirst
            else if (cutted[i]!=-1 && !stickCheck[cutted[i]]) {
                stickCheck[cutted[i]] = true;
                currentStick.add(0);
            }
            // met razer
            else if (!currentStick.isEmpty()) {
                for (int j = 0; j < currentStick.size() ; j++) {
                    currentStick.set(j,currentStick.get(j)+1);
                }
            }
        }
        sum = Arrays.stream(eachStick).sum();
    }
}
