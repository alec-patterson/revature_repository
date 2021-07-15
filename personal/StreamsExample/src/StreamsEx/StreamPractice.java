package StreamsEx;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamPractice {
	
	
	public static void main(String[] args) {
		List<Integer> i = Arrays.asList(1, 2, 3, 4, 5);
		
		List<Integer> t = i.stream().map(x->{return x * 30;}).filter(x->{return x%8 == 0;}).collect(Collectors.toList());
		for(Integer n: t) {
			System.out.println(n);
		}
	}
}
