package StreamsEx;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class StreamEx {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(2,3,4,5,7); //new ArrayList<Integer>();
		
		// map, filter, sorted
		//collect, forEach, reduce (terminal operations)
		List<Integer> result = numbers.stream().filter((x)->{return (x%2 == 0);}).map((x) -> {return x * x;}).collect(Collectors.toList());
		
		for(Integer i: result) {
			System.out.println(i);
		}
	}
}
