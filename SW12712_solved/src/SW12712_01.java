import java.util.Arrays;
import java.util.Scanner;

public class SW12712_01 {
    static int biggest = -1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        sc.nextLine();

        for (int k = 0; k < testCase; k++) {
            int arraySize = sc.nextInt();
            int spraySize = sc.nextInt();
            sc.nextLine();

            // 2차원 배열 입력 받기
            int[][] flies = new int[arraySize][arraySize];
            for (int l = 0; l < arraySize; l++) {
                flies[l] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            biggest = -1;
            for (int i = 0; i <flies.length; i++) {
                for (int j = 0; j < flies[i].length; j++) {
                    crossSpray(i,j,spraySize,flies);
                    xSpray(i,j,spraySize,flies);
                }
            }

            System.out.println("#"+(k+1)+" "+biggest);
        }


        // 각 칸에 대해서 스프레이 분사 호출
    }
    // 십자로 스프레이 분사
    static void crossSpray(int startX, int startY, int spraySize, int[][] flies){
        int sum = flies[startX][startY];

        // 방향마다 현재 위치를 저장하고 바꾸는 방법 -> 시도
        // for문 안에서 for문으로 각 방향에 대한 계산을 수행하는 방법

        int upperX = startX;
        int rightY = startY;
        int leftY = startY;
        int lowerX = startX;

        for (int i = 1; i < spraySize; i++) {
            // Upper
            upperX-=1;
            if(upperX>=0){
                sum+=flies[upperX][startY];
            }
            // right
            rightY+=1;
            if(rightY<flies[startX].length){
                sum+=flies[startX][rightY];
            }
            // left
            leftY-=1;
            if(leftY>=0){
                sum+=flies[startX][leftY];
            }
            // lower
            lowerX+=1;
            if(lowerX<flies.length){
                sum+=flies[lowerX][startY];
            }
        }
        if(sum>biggest){
            biggest = sum;
        }
    }

    // x로 스프레이 분사
    static void xSpray(int startX, int startY, int spraySize, int[][] flies){
        int sum = flies[startX][startY];

        int rightUpperX = startX;
        int rightUpperY = startY;
        int leftUpperX = startX;
        int leftUpperY = startY;
        int downRightX = startX;
        int downRighty = startY;
        int downLeftX = startX;
        int donwLeftY = startY;

        for (int i = 1; i < spraySize; i++) {
            // rightUpper
            rightUpperX-=1;
            rightUpperY+=1;
            if(rightUpperX>=0 && rightUpperY<flies[startX].length){
                sum+=flies[rightUpperX][rightUpperY];
            }

            // leftUpper
            leftUpperX-=1;
            leftUpperY-=1;
            if(leftUpperX>=0 && leftUpperY>=0){
                sum+=flies[leftUpperX][leftUpperY];
            }

            // downRight
            downRightX+=1;
            downRighty+=1;
            if(downRightX<flies.length && downRighty<flies[startX].length){
                sum+=flies[downRightX][downRighty];
            }

            // downLeft
            downLeftX+=1;
            donwLeftY-=1;
            if(downLeftX<flies.length && donwLeftY>=0){
                sum+=flies[downLeftX][donwLeftY];
            }
        }
        if(sum>biggest){
            biggest = sum;
        }
    }
}
