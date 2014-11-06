
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	static void p(String str) {
		System.out.println(str);
	}
	
	static void pr(String str) {
		System.out.print(str);
	}

	static int makeKey(int r, int c){
		return (r << 16) | c;
	}
	
	static int n;
	
	static int map[][];
	static HashSet<Integer> solved;
	static ArrayList<HashSet<Integer>> flow;
	
	
	
	static void fillStart(int r, int c, int targ){
		if(solved.contains(makeKey(r,c)) || r < 0 || r >= n || c <0 || c >=n || map[r][c] != targ){
			return;
		}
//		p("("+r+","+c+")");
		solved.add(makeKey(r,c));
		map[r][c] = 0;
		fillStart(r-1,c,targ);
		fillStart(r+1,c,targ);
		fillStart(r,c-1,targ);
		fillStart(r,c+1,targ);
	}
	
	static void findFlowHelper(int r, int c, int num){
 		if(solved.contains(makeKey(r,c)) ||
				flow.get(num-1).contains(makeKey(r,c)) ||
				r < 0 || r >= n || c <0 || c >=n || map[r][c] != num){
			return;
		}
		flow.get(num-1).add(makeKey(r,c));
		findFlowHelper(r-1,c,num);
		findFlowHelper(r+1,c,num);
		findFlowHelper(r,c-1,num);
		findFlowHelper(r,c+1,num);
	}
	
	static void findFlow(int num){
		flow.add(new HashSet<Integer>());
		for(Integer x : solved){
			int r = x >> 16;
			int c = x & 0x000ffff;
			findFlowHelper(r+1,c,num);
			findFlowHelper(r-1,c,num);
			findFlowHelper(r,c-1,num);
			findFlowHelper(r,c+1,num);
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numcases = in.nextInt();
		while(numcases --> 0){
			n = in.nextInt();
			map = new int[n][n];
			solved = new HashSet<Integer>();
			flow = new ArrayList<HashSet<Integer>>();
//			horizon = new HashSet<Integer>();
			solved.add(0);
			for(int r = 0; r < n; r++){
				String s = in.next();
				for(int i=0; i < s.length(); i++){					
					map[r][i] = s.charAt(i) - '0';
				}
			}
			fillStart(0,1,map[0][0]);
			fillStart(1,0,map[0][0]);
			map[0][0]=0;
			int sol[] = new int[6];
			while(solved.size() < n*n){
//				p(""+solved.size());
				int maxflow = 0;
				int maxi = 0;
				flow = new ArrayList<HashSet<Integer>>();
				for(int i = 1; i <=6 ;i++){
					findFlow(i);
					if(flow.get(i-1).size() > maxflow) {
						maxflow = flow.get(i-1).size();
						maxi = i-1;
					}
				}
				solved.addAll(flow.get(maxi));
				for(Integer x : flow.get(maxi)){
					map[x>>16][x&0x0000ffff] = 0;
				}
				sol[maxi]++;
				
				
			}
			int out = 0;
			for(int x : sol){
				out+=x;
			}
			
			p(""+out);
			String firstPrint = "";
			for(int x : sol){
				pr(firstPrint+x);
				firstPrint = " ";
			}
			p("");
			
		}
	
	}
}
