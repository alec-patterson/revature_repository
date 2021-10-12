public class interviewQuestion {



	int getVals(int n, int r) {
		int s = 0;
		int a = 1;
		for(int i = 1; i <= r; i++) {
			a *= (n+i);
			s += a;
		}
		return s;
	}

	public static void main(String[] args) {
		interviewQuestion iq = new interviewQuestion();
		
		System.out.println(iq.getVals(4, 5));
	}

}

/*
	int getVals(int n, int r) {
		int s = 0;
		int a = 1;
		for(int i = 1; i <= r; i++) {
			a++ = a * (n+i);
			s += a;
		}
		return s;
	}
*/