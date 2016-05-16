/**
 * Team: Francisco Gonzalez, Daniel Pulley
 * NET-ID: fgonza21, dpulley
 * UIN: 679481167, 669874288
 * CS 342 HW3 - RSA Encryption
 * An RSA encryption program.  The Message Block class takes a string and converts it to blocked numbers or takes blocked numbers and converts them to strings.
 *
 */

import java.io.*;
import java.util.Scanner;

public class MessageBlock {
	public String input;
	public String output;
	// constructor for Message Block class
	public MessageBlock(String fileIn, String fileOut) {
		input = fileIn;
		output = fileOut;
	}
	// Converts character to ascii value
	private String toVal(int n) { //Converts to value
		String ch = "";
		if (n == 0) {
			ch = "00"; // null char
		} else if (n == 11) {
			ch = "01"; // vertical tab
		} else if (n == 9) {
			ch = "02"; // horizontal tab
		} else if (n == 10) {
			ch = "03"; // new line
		} else if (n == 13) {
			ch = "04"; // carriage return
		} else if (n == 32) {
			ch = "05"; // carriage return
		}else {
			int temp = n - 27;
			ch = temp + ch;
		}
		return ch;
	}
	// Converts ascii value to character
	private String toChar(int n) { //converts to character
		String ch = "";
		if (n == 0) {
			ch = "\0";
		} else if (n == 1) {
			ch = "\\v";
		} else if (n == 2) {
			ch = "\t";
		} else if (n == 3) {
			ch = "\n";
		} else if (n == 4) {
			ch = "\r";
		} else if (n == 5) {
			ch = " ";
		} else {
			n = n + 27;
			ch = Character.toString((char) n);
		}
		return ch;
	}
	
	// Reads through the message to block it into blocks of ascii values
	public void block(int blocks) {
		FileReader inStream = null;
		int val = -1;
		int count = 0;
		String str = "";
		try {
			inStream = new FileReader(input);
			File f = new File(output);
			PrintWriter p = null;
			try {
				p = new PrintWriter(f);
				while ((val = inStream.read()) > -1) {
					count++;
					String ch = toVal(val);
					str = ch + str;
					if (count == blocks) {
						count = 0;
						p.printf("%s", str);
						p.printf("\n");
						p.flush();
						str = "";
					}
				}
				if (count > 0) {
					int nullChars = blocks - count;
					while (nullChars != 0) {
						str = "00" + str;
						nullChars--;
					}
					p.printf("%s\n", str);
					p.flush();
				}
			} catch (FileNotFoundException e) {
				System.out.println("Error writing to file");
			} finally {
				if (p != null) {
					p.close();
				}
			}
		} catch (IOException e) {
			System.out.println("Error opening file");
		}
	}
	// Reads through the blocks of ascii values adn converts to characters 
	public void unBlock() {
		File file = null;
		try { 
			file = new File(input);
			Scanner scanner = new Scanner(file);
			File f = new File(output);
			PrintWriter p = null;
			try {
				p = new PrintWriter(f);
				while (scanner.hasNext()) {
					String line = scanner.nextLine();
					String[] res = line.split("");
					int i = res.length - 1;
					while (i > 0) {
						String num = res[i - 1] + res[i];
						int temp = Integer.parseInt(num);
						String ch = toChar(temp);
						p.printf("%s", ch);
						p.flush();
						i = i - 2; //two elements
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Error writing to file");
			} finally {
				if (p != null) {
					p.close();
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		}
	}
}
