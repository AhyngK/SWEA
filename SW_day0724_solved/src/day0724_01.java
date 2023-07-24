import java.util.*;
import java.io.*;

public class day0724_01 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		for(int tc=0; tc<testCase; tc++) {
			int size = Integer.parseInt(br.readLine());
			int[][] farm = new int[size][size];
			for(int i=0; i<farm.length; i++) {
				farm[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			}
			
			int sum =0;
			for(int i=0; i<=size/2; i++) {
				for(int j=size/2-i; j<=size/2+i; j++) {
					sum+=farm[i][j];
					farm[i][j] = -1;
				}
			}
			for(int i=size/2+1; i<size;i++) {
				for(int j= i-size/2; j<size-(i-size/2); j++) {
					sum+=farm[i][j];
					farm[i][j] = -1;
				}
			}
			
			
			System.out.println("#"+(tc+1)+" "+sum);
		}
		
	}
}
