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
		String inp = in.next();
		while (inp.length() > 1) {
			char[] str = inp.toCharArray();
			String beforespace = "";
			for(int i = 1; i < str.length; i++){
				if (str[i] == '1') {
					for(int j = i;j<str.length;j+=i){
						if(str[j] == '1') str[j] = '0';
						else str[j] = '1';
					}
					pr(beforespace+i);
					beforespace = " ";
					
				}
				
			}
			p("");
			inp = in.next();
		}
		in.close();
	}
}