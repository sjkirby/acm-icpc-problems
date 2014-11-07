//import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	static void p(String str){
		System.out.println(str);
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(System.in);
//		Scanner in = new Scanner(new FileReader("/nfs/nfs4/home/sjkirby/workspace/Scratch/src/p6548/test"));
		int numin = in.nextInt();
		int numtar = in.nextInt();
		while(numin + numtar != 0){
			ArrayList<Integer> xs = new ArrayList<Integer>();
			while(numin-- != 0){
				xs.add(in.nextInt());
			}
			xs.add(2000000);
			int lastlevel = 1;
			int thislevel = 1;
			int thisbatch = 0;
			int last = xs.get(0) - 1;
			boolean flag = true;
			boolean doneflag = false;
			for(Integer x : xs.subList(1,xs.size())){
				if(x - last != 1){
					if(flag){
						thislevel += thisbatch;
						thisbatch = 0;
					}
					else{
						flag = true;
						thisbatch = 0;
					}
					if(--lastlevel == 0){
						if(doneflag){
							p(""+thislevel);
							break;
						}
						else{
							lastlevel = thislevel;
							thislevel =0;
						}
					}
					
					
				}
				last = x;
				thisbatch++;
				if(x == numtar){
					flag = false;
					doneflag = true;
				}
								
			}
			
			numin =in.nextInt();
			numtar =in.nextInt();
		}
		
		
	}

}
