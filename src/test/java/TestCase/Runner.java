package TestCase;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Runner {

	public static void main(String[] arg) {
		descending();
//		String input = "Rahuaaaaalalddffsdsd";
//		int count = 0;
//		char c;
//		for (int i = 0; i < input.length(); i++) {
//			c = input.charAt(i);
//			if (c == 'a') {
//				count++;
//			}
//		}
//		System.out.print("Total Number is " + count + " of " + "a");

//		VerifyCreatePage cl=new VerifyCreatePage();
//		cl.createLeads();
//		cl.createInvoice();

	}

	public static void descending() {
		Short value[] ={3,4,2,6,8,1,9};
		int temp = 0;
		for (int i = 0; i < value.length; i++) {
			for (int j = i + 1; j < value.length; j++) {
				if (value[i] < value[j]) {
					temp = value[i];
					value[i] = value[j];
					value[j] =(short) temp;
				}
			}
		}
		System.out.println(Arrays.toString(value));
		System.out.println(new Runner());
		for (int i = 0; i < value.length; i++) {
			 System.out.println(value[i]);
		}
//		System.out.println(value[value.length - 2]);
//		Arrays.sort(value , Collections.reverseOrder());
//		System.out.println(Arrays.toString(value));
		
	}

}
