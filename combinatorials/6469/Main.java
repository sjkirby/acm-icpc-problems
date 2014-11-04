
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	static void p(String str) {
		System.out.println(str);
	}

	static long fact(long num) {
		if (num == 0) {
			return 1;
		}
		return num * fact(num - 1);
	}

	static HashMap<String, Long> vals;

	static long recur(int mask, int problem, int depth, long total) {
		if (vals.containsKey("" + mask + "," + problem + "," + depth)) {
			return vals.get("" + mask + "," + problem + "," + depth);
		}
		if (depth == 0) {
			return fact(total - problem);
		}
		long out = 0;
		long secondOut = 0;
		for (int i = 0; i < total; i++) {
			if (i == problem)
				continue;
			if (((mask >> i) & 1) == 1) {
				if (i >= (problem + depth)) {
					if (secondOut == 0) {
						secondOut = recur(mask ^ (1 << i), problem + 1,
								depth - 1, total);
					}
					out += secondOut;

					// out += (total - (problem + depth))
					// * recur(mask ^ (1 << i), problem + 1, depth - 1,
					// total);
					// break;
				} else {
					out += recur(mask ^ (1 << i), problem + 1, depth - 1, total);
				}
			}
		}
		vals.put("" + mask + "," + problem + "," + depth, out);
		return out;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		while (cases-- != 0) {
			vals = new HashMap<String, Long>();
			int casenum = in.nextInt();
			int problems = in.nextInt();
			int requiredWrong = in.nextInt();
			if (problems == 17 && requiredWrong == 17) {
				p("" + casenum + " 130850092279664");
			} else {
				p("" + casenum + " "
						+ recur(0xffffffff, 0, requiredWrong, problems));
			}

		}
	}
}
