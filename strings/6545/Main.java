import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static void p(String str) {
		System.out.println(str);
	}

	static void pr(String str) {
		System.out.print(str);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Integer inp = Integer.decode(in.nextLine());
		while(inp != 0){
			ArrayList<String> strs = new ArrayList<String>();
			while(inp-- != 0){
				strs.add(in.nextLine());
			}
			int start = 0;
			for(String str : strs){
				if(start < str.length()){
					for(int i = start; i <= str.length(); i++){
						if(i == str.length()){
							start = i;
							break;
						}
						if(str.charAt(i) == ' '){
							start = i;
							break;
						}
						
					}
				}
			}
			p(""+(start+1));
			inp = Integer.decode(in.nextLine());
		}
		
	
	}
}