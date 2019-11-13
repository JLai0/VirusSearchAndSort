import java.util.Arrays;
import java.util.Scanner;

public class Range {

	public static String min;
	//creates min field
	public static String max;
	//creates max field
	public String value;
	//creates value field

	public Range(String s, String s1) {
		//constructor for range
		min = s;
		max = s1;
	}

	public static Range parse(String s) {
		String[] minmax = s.split("-|\\s+");
		//parses for range and stores input as a string array
		if (minmax.length == 1) {
			//if it is equal to one value then save min and max as equal to one another
			min = minmax[0];
			max = minmax[0];
			Range parsed = new Range(min, max);
			return parsed;
		}
		if (minmax.length == 2) {
			//if it is not equal store min differently as max
			min = minmax[0];
			max = minmax[1];
			Range parsed = new Range(min, max);
			return parsed;
		}
		return null;
	}

	public boolean contains(int n) {
		//checks if it contains a value between min and max
		if ((n <= Integer.parseInt(getMax())) && (n >= Integer.parseInt(getMin()))) {
			return true;
			//if it is return true
		} else {
			return false;
			//if it is not return false
		}

	}

	public String getMin() {
		//returns min
		return min;
	}

	public String getMax() {
		//returns max
		return max;
	}

}
