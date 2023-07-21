import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SW1953_01 {
    static List<List<Integer>> tunnelType = new ArrayList<>();
    static int sum = 0;
    static int timeLimit;
    public static void main(String[] args) throws IOException {
        for (int i = 0; i <= 7 ; i++) {
            tunnelType.add(new ArrayList<>());
        }
        tunnelType.get(1).add(1);
        tunnelType.get(1).add(2);
        tunnelType.get(1).add(3);
        tunnelType.get(1).add(4);
        tunnelType.get(2).add(1);
        tunnelType.get(2).add(4);
        tunnelType.get(3).add(2);
        tunnelType.get(3).add(3);
        tunnelType.get(4).add(1);
        tunnelType.get(4).add(3);
        tunnelType.get(5).add(3);
        tunnelType.get(5).add(4);
        tunnelType.get(6).add(2);
        tunnelType.get(6).add(4);
        tunnelType.get(7).add(1);
        tunnelType.get(7).add(2);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int verticalSize = Integer.parseInt(st.nextToken());
            int horizontalSize = Integer.parseInt(st.nextToken());
            int holeVertical = Integer.parseInt(st.nextToken());
            int holeHorizontal = Integer.parseInt(st.nextToken());
            timeLimit = Integer.parseInt(st.nextToken());

            int[][] tunnel = new int[verticalSize][horizontalSize];
            int[][] tunnelCheck = tunnel.clone();
            for (int i = 0; i < tunnel.length; i++) {
                tunnel[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                Arrays.fill(tunnelCheck[i],0);
            }

            sum = 0;
            recursion(1, holeVertical,holeHorizontal,0,tunnel, tunnelCheck);
            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }
        System.out.println(sb);
    }

    static void recursion(int currentTime, int x, int y, int beDirection, int[][] tunnel, int[][] tunnelCheck){
        if(tunnelCheck[x][y]!=1){
            sum += 1;
            tunnelCheck[x][y]=1;
        }

        if(currentTime==timeLimit){
//            System.out.println("one end"+ sum);
            return;
        }

        int currentTunnelType = tunnel[x][y];
        for (int i = 0; i < tunnelType.get(currentTunnelType).size(); i++) {
            if(tunnelType.get(currentTunnelType).get(i)==beDirection){
                continue;
            }
            int toDirection = tunnelType.get(currentTunnelType).get(i);
            int otherDirection = 5-toDirection;
//            System.out.println(toDirection);
//            System.out.println(otherDirection);
            switch (toDirection){
                case 1 :
                    if(x-1>=0){
                        int nextTunnel = tunnel[x-1][y];
                        if(tunnelType.get(nextTunnel).contains(otherDirection)){
//                            System.out.println("going up");
                            recursion(currentTime+1,x-1,y,otherDirection,tunnel,tunnelCheck);
                        }
                    }
                    break;
                case 2 :
                    if(y-1>=0){
                        int nextTunnel = tunnel[x][y-1];
                        if(tunnelType.get(nextTunnel).contains(otherDirection)){
//                            System.out.println("going left");
                            recursion(currentTime+1,x,y-1,otherDirection,tunnel,tunnelCheck);
                        }
                    }
                    break;
                case 3 :
                    if(y+1<tunnel[0].length){
                        int nextTunnel = tunnel[x][y+1];
                        if(tunnelType.get(nextTunnel).contains(otherDirection)){
//                            System.out.println("going right");
                            recursion(currentTime+1,x,y+1,otherDirection,tunnel,tunnelCheck);
                        }
                    }
                    break;
                case 4 :
                    if(x+1<tunnel.length){
                        int nextTunnel = tunnel[x+1][y];
                        if(tunnelType.get(nextTunnel).contains(otherDirection)){
//                            System.out.println("going down");
                            recursion(currentTime+1,x+1,y,otherDirection,tunnel,tunnelCheck);
                        }
                    }
                    break;
            }
        }
//        System.out.println("nowhere to go " +sum);
    }
}
