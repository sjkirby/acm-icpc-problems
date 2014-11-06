import java.util.ArrayList;
import java.util.Scanner;

class Prize{
	ArrayList<Integer> reqs = new ArrayList<Integer>();
	int value;
	
	Prize(Scanner in){
		int numreqs = in.nextInt();
		while(numreqs-- != 0){
			reqs.add(in.nextInt());
		}
		value = in.nextInt();
	}
	
	int value(ArrayList<Integer> tickets){
		int min = 100000;
		for(Integer x : reqs){
			if(tickets.get(x-1) < min){
				min= tickets.get(x-1);
			}
		}
		return value*min;
	}
	
}

public class Main {
	static void p(String s){
		System.out.println(s);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		while(cases-- != 0){
			int numPrizes = in.nextInt();
			int numTickets = in.nextInt();
			ArrayList<Prize> prizes = new ArrayList<Prize>(); 
			while(numPrizes-- != 0){
				prizes.add(new Prize(in));
			}
			ArrayList<Integer> tickets = new ArrayList<Integer>();
			while(numTickets-- != 0){
				tickets.add(in.nextInt());
			}
			int out = 0;
			for(Prize p : prizes){
				out += p.value(tickets);
			}
			p(""+out);
		}
	}
}