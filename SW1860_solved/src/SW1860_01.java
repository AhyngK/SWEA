import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class SW1860_01 {
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

            int bread = 0;
            int time = 0;
            int index = 0;

            boolean check = true;

            while (true){
                if(!check || index==peopleArrive.length){
                    break;
                }

                time+=1;

                if(time%makeTime==0){
                    bread+=oneTimeMake;
                }

                while (true){
                    if(index<peopleArrive.length && peopleArrive[index]==time){
                        if(bread>0){
                            bread-=1;
                            index++;
                        }
                        else {
                            check = false;
                            break;
                        }
                    }
                    else {
                        break;
                    }
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
