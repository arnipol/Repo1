package Training;

public class Test {

	//123456

	public static String SwapString(String str) {
		if (str.isEmpty()) return str;
		return SwapString(str.substring(1) + str.charAt(0));
	}
	

}
