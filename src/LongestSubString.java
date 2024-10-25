import java.util.LinkedList;
import java.util.Scanner;

public class LongestSubString {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a string: ");
		String str = input.nextLine();

		String longestString = findSubString(str);
		System.out.println(longestString);
	}

	private static String findSubString(String str) {
		LinkedList<Character> maxList = new LinkedList<>();     // 1
		LinkedList<Character> list = new LinkedList<>();        // 2
		int length = str.length();                              // 3
		if (length <= 1) {                                      // 4
			return str;                                         // 5
		}
		// Keypoint: Ascending substrings DON'T OVERLAP --> Don't have to create nested loop at each character
		list.addLast(str.charAt(0));                            // 6
		for (int i = 1; i < length; i++) {                      // 7
			if (str.charAt(i) >= list.getLast()) {              // 8
				list.addLast(str.charAt(i));                    // 9
				if (i < length - 1) continue;                   // 10
			}
			if (list.size() > maxList.size()) {                 // 11
				maxList.clear();                                // 12
				maxList.addAll(list);                           // 13
			}
			list.clear();                                       // 14
			list.addLast(str.charAt(i));                        // 15
		}
//			***** Time-consuming algorithm *****
//			int longestLength = length - i;
//			if (longestLength <= maxList.size()) break;
//
//			LinkedList<Character> list = new LinkedList<>();
//			list.add(str.charAt(i));
//			int index = i + 1;
//			while (index < length) {
//				if (str.charAt(index) >= list.getLast()) {
//					list.add(str.charAt(index));
//					index++;
//				} else {
//					break;
//				}
//			}
//
//			if (list.size() > maxList.size()) {
//				maxList = list;
//			}

		String outputString = "";                               // 16
		for (Character character : maxList) {                   // 17
			outputString += character;                          // 18
		}
		return outputString;                                    // 19
	}
}
