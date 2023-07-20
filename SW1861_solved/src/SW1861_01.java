import java.util.Arrays;
import java.util.Scanner;

public class SW1861_01 {
    static int movementBiggest = -1;
    static int startBiggest = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        sc.nextLine();

        for (int tc = 0; tc < testCase; tc++) {
            int roomNum = sc.nextInt();
            sc.nextLine();

            int[][] rooms = new int[roomNum][roomNum];
            for (int i = 0; i <rooms.length; i++) {
                rooms[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            movementBiggest = -1;
            startBiggest = -1;

            for (int i = 0; i < rooms.length; i++) {
                for (int j = 0; j < rooms[i].length; j++) {
                    recursion(rooms[i][j],i,j,rooms,1);
                }
            }
            System.out.println("#"+(tc+1)+" "+startBiggest+" "+movementBiggest);
        }
    }
    static void recursion(int startNum, int x, int y, int[][] rooms, int movement){
        boolean check = false;

        // upper
        if(x-1>=0 && rooms[x-1][y]-rooms[x][y]==1){
            check = true;
            recursion(startNum,x-1,y,rooms,movement+1);
        }
        // lower
        if(x+1<rooms.length && rooms[x+1][y]-rooms[x][y]==1){
            check = true;
            recursion(startNum,x+1,y,rooms,movement+1);
        }
        // right
        if(y+1<rooms[x].length && rooms[x][y+1]-rooms[x][y]==1){
            check = true;
            recursion(startNum,x,y+1,rooms,movement+1);
        }
        // left
        if(y-1>=0 && rooms[x][y-1]-rooms[x][y]==1){
            check = true;
            recursion(startNum,x,y-1,rooms,movement+1);
        }

        //nowhere to move
        if(!check){
            if(movement>movementBiggest){
                movementBiggest = movement;
                startBiggest = startNum;
            }
            if(movement==movementBiggest && startNum<startBiggest){
                startBiggest = startNum;
            }
            return;
        }
    }
}
