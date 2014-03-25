import java.math.BigDecimal;

public class Encrypt {

	public static void main(String[] args) {
		String input;
		String answer;
		int num; // Private key of the user
		int lenInput;
		String pad; // Store the one-time pad

		System.out.println("Encrypt or Decrypt?");
		answer = TextIO.getln();

		if (answer.equals("Encrypt") || answer.equals("encrypt")) {
			System.out.println("Enter any message to encrypt");
			input = TextIO.getln();
			System.out.println("Enter a number");
			num = TextIO.getlnInt();
			lenInput = input.length();
			pad = createPad(lenInput, num);
			System.out.println("Your key -> " + num);
			System.out.println("Encryption -> " + encrypt(input, pad));
		}

		else if (answer.equals("Decrypt") || answer.equals("decrypt")) {
			System.out.println("Enter any message to decrypt");
			input = TextIO.getln();
			System.out.println("Enter a number");
			num = TextIO.getlnInt();
			lenInput = input.length();
			pad = createPad(lenInput, num);
			System.out.println("Decryption -> " + decrypt(input, pad));
		}

		else
			System.exit(1);

	}

	public static String encrypt(String toConvert, String lookUpPad) {

		String message = "";
		int shift; // Amount of shift of the character
		char letter; // Letter at the particular position
		int ascLetter; // ASCII code for the particular character
		char encLetter; // Encrypted Letter

		for (int i = 0; i < toConvert.length(); i++) {
			String temp = "";
			char c = lookUpPad.charAt(i);
			temp += c;
			letter = toConvert.charAt(i);
			ascLetter = (int) letter;
			shift = Integer.parseInt(temp);
			if (ascLetter + shift > 127)
				message += "" + ((ascLetter + shift) - 130);
			else {
				encLetter = (char) (shift + ascLetter);
				message += encLetter;
			}
		}

		return message;

	}

	public static String decrypt(String toConvert, String lookUpPad) {

		String message = "";
		char encLetter;
		int shift;

		for (int i = 0; i < toConvert.length(); i++) {
			String temp = "";
			char c = lookUpPad.charAt(i);
			temp += c;
			char letter = toConvert.charAt(i);
			int ascLetter = (int) letter;
			shift = Integer.parseInt(temp);
			if (letter >= 0 && letter <= 9)
				message += "" + (130 - shift);
			else {
				encLetter = (char) (ascLetter - shift);
				message += encLetter;
			}

		}

		return message;
	}

	public static String createPad(int len, int n) { // 'n is the private key'

		BigDecimal num1 = new BigDecimal(1 / (Math.PI + n)); // num1
		String b1 = num1 + "";
		int i1;
		String a1 = "";

		for (i1 = 0; i1 <= b1.length(); i1++) {
			if (b1.charAt(i1) == '.') {
				break;
			}
		}

		for (int j = i1 + 1; j <= b1.length() - 1; j++) {
			a1 += b1.charAt(j);
		}

		BigDecimal num2 = new BigDecimal(Math.pow(Math.E * n, 0.76 / n)); // num2
		String b2 = num2 + "";
		int i2;
		String a2 = "";

		for (i2 = 0; i2 <= b2.length(); i2++) {
			if (b2.charAt(i2) == '.') {
				break;
			}
		}

		for (int j = i2 + 1; j <= b2.length() - 1; j++) {
			a2 += b2.charAt(j);
		}

		BigDecimal num3 = new BigDecimal(1 / (Math.PI * n)); // num3
		String b3 = num3 + "";
		int i3;
		String a3 = "";

		for (i3 = 0; i3 <= b3.length(); i3++) {
			if (b3.charAt(i3) == '.') {
				break;
			}
		}

		for (int j = i3 + 1; j <= b3.length() - 1; j++) {
			a3 += b3.charAt(j);
		}

		BigDecimal num4 = new BigDecimal(2 * Math.pow(12.4 / (n + 4565),
				0.5 * n)); // num4
		String b4 = num4 + "";
		int i4;
		String a4 = "";

		for (i4 = 0; i4 <= b4.length(); i4++) {
			if (b4.charAt(i4) == '.') {
				break;
			}
		}

		for (int j = i4 + 1; j <= b4.length() - 1; j++) {
			a4 += b4.charAt(j);
		}

		String pad = "" + a1 + a2 + a3 + a4;

		return pad.substring(0, len); // return the one-time pad based on the
										// private key 'n'
	}

}
