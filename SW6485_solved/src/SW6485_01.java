import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW6485_01 {
    public static void main(String[] args) throws IOException {
        int[] busStops = new int[5001];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <=testCase ; tc++) {
            Arrays.fill(busStops,0);

            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i <N ; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int firstStop = Integer.parseInt(st.nextToken());
                int lastStop = Integer.parseInt(st.nextToken());
                for (int j = firstStop; j <=lastStop ; j++) {
                    busStops[j]+=1;
                }
            }
            sb.append("#").append(tc).append(" ");

            int P = Integer.parseInt(br.readLine());
            for (int i = 0; i < P; i++) {
                int index = Integer.parseInt(br.readLine());
                sb.append(busStops[index]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
