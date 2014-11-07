
//import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
//	static void recur(int from, int to, int[]){
//		
//	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Scanner in = new Scanner(new FileReader("/nfs/nfs4/home/sjkirby/workspace/Scratch/src/d/test")); 
		Scanner in = new Scanner(System.in);
		String a = in.next();
		String b = in.next();
		int cases = 0;
		while(!a.equals("END")){
			cases++;
			int aa[] = new int[26];
			int ab[] = new int[27];
			
			for(char c : a.toCharArray()){
				aa[c-'a']++;
			}
			for(char c : b.toCharArray()){
				ab[c-'a']++;
			}
			boolean flag = true;
			for(int i = 0; i < 26; i++){
				if(aa[i] != ab[i]){
					flag = false;
				}
			}
			if(flag){
				System.out.println("Case "+cases+": same");
			}
			else{
				System.out.println("Case "+cases+": different");
			}
			
			
			
			a = in.next();
			b = in.next();
		}
		
		
	}

}