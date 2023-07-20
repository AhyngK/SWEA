import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SW1860_02 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int peopleNum = Integer.parseInt(st.nextToken());
            int makeTime = Integer.parseInt(st.nextToken());
            int oneTimeMake = Integer.parseInt(st.nextToken());

            int[] peopleArrive = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(peopleArrive);

            // 초당 사람 수 배열로 변환
            int[] personTimes = new int[peopleArrive[peopleArrive.length-1]+1];
            for (int i = 0; i < peopleArrive.length; i++) {
                personTimes[peopleArrive[i]]++;
            }

            boolean check = true;

            int bread = 0;
            for (int i = 0; i < personTimes.length; i++) {
                if(i!=0 && i%makeTime==0){
                    bread+=oneTimeMake;
                }

                if(bread>=personTimes[i]){
                    bread-=personTimes[i];
                }
                else {
                    check = false;
                    break;
                }
            }

            if(check){
//                System.out.println("#"+(tc+1)+" Possible");
                sb.append("#")
                        .append(tc+1)
                        .append(" ")
                        .append("Possible\n");
            }
            else {
//                System.out.println("#"+(tc+1)+" Impossible");
                sb.append("#")
                        .append(tc+1)
                        .append(" ")
                        .append("Impossible\n");
            }
        }
        System.out.println(sb);
    }
}
