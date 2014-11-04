
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

	static void bounce(){
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Integer a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
		int casenum = 1;
		while(a + b + c != 0){
			TreeMap<Integer,Integer> table1 = new TreeMap<Integer,Integer>();
			TreeMap<Integer,Integer> table2 = new TreeMap<Integer,Integer>();
			
			for(int i = 0; i < c; i++){
				boolean target = true;
				Integer d = in.nextInt();
				while(true){
					Integer e;
					if(target){
						if(table1.containsKey(d%a)){
							e = table1.get(d%a);
							table1.put(d%a, d);
							d = e;
							target = !target;
						}
						else{
							table1.put(d%a, d);
							break;
						}
					}
					else{
						if(table2.containsKey(d%b)){
							e = table2.get(d%b);
							table2.put(d%b, d);
							d = e;
							target = !target;
						}
						else{
							table2.put(d%b, d);
							break;
						}
						
					}
				}
			}
			
			System.out.println("Case "+ casenum++ +":");
			if(table1.size()!=0){
				System.out.println("Table 1");
				for(Integer x:table1.keySet()){
					System.out.println(""+x+":"+table1.get(x));				
				}
			}
			if(table2.size()!=0){
				System.out.println("Table 2");
				for(Integer x:table2.keySet()){
					System.out.println(""+x+":"+table2.get(x));				
				}
			}
			
			
			a = in.nextInt();
			b = in.nextInt();
			c = in.nextInt();
		}
		in.close();
	}

}
