import java.util.LinkedList;
import java.util.Scanner;

public class LongestSubString {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a string: ");
		String str = input.nextLine();

		long start = System.currentTimeMillis();
		String longestString = findSubString(str);
		long end = System.currentTimeMillis();
		System.out.println(longestString);
		System.out.println("Finding time: " + (end - start));
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

/**     Tính toán độ phức tạp thời gian của phương thức findSubString
 *      - Các lệnh {1, 2, 3, 4, 5, 6, 16, 19} đều có độ phức tạp O(1)
 *      - Lệnh 17 thực hiện lặp lệnh 18 x lần, x <= n, lệnh 18 độ phức tạp O(1)
 *          ==> Độ phức tạp vòng lặp dòng {17, 18}: O(n) (case xấu nhất)
 *      - Vòng lặp dòng {7, 8, 9, 10, 11, 12, 13, 14, 15}:
 *          + Số lần lặp: n
 *          + Tổng phép tính các biểu thức điều kiện và lệnh ở {8, 9, 10, 11, 15} có độ phức tạp O(1)
 *          + lệnh 13 và 14 giống nhau, gồm một số x bước O(1), x là tổng số lần lệnh addLast() đã thực hiện trước
 *          đó kể từ lần list rỗng gần nhất.
 *          Vì addLast() luôn thực hiện 1 lần trong 1 vòng lặp (không hơn không kém), tổng x trong cả chương trình
 *          không vượt quá n --> tổng số phép tính của {13, 14} là O(n).
 *          + lệnh 12 gồm một số y bước O(1), y bằng với số bước của lệnh 13 ở một thời điểm trước đó. Vì vậy tổng y
 *          trong cả chương trình không vượt quá tổng x trong cả chương trình --> số phép tính của lệnh 12 nhỏ hơn O(n).
 *          --> Độ phức tạp của {12, 13, 14} trong cả chương trình là O(n).
 *          ==> Độ phức tạp của vòng lặp dòng {7, 8, 9, 10, 11, 12, 13, 14, 15}: O(n * c) + O(n) = O(n)
 *
 *       ==> Độ phức tạp của chương trình: T(n) = O(1) + O(n) + O(n) = O(n)
 *
 */