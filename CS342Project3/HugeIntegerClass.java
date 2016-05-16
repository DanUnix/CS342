/**
 * Team: Francisco Gonzalez, Daniel Pulley 
 * NET-ID: fgonza21, dpulley 
 * UIN:  679481167, 669874288 
 * CS 342 HW3 - RSA Encryption An RSA encryption program.
 * The Huge Integer Class stores and manipulates extremely large integers.
 *
 */


// class name
public class HugeIntegerClass {
    // Create new integer array 
	public int[] bigInt;

    // HugeIntegerClass Method that gets the size of string and create new int array of that string size
	public HugeIntegerClass(String str) {
        // Get length of string
		int len = str.length();
        // Create new array of string size
		bigInt = new int[len];
        // put data into array
		for (int i = len; i > 0; i--) {
			bigInt[i - 1] = Character.getNumericValue(str.charAt(i - 1));
		}
	}

    // Constructor
	public HugeIntegerClass() {
	}

    // isGreater Method
    // Takes two huge integer ints and check if greater than the other
	public static boolean isGreater(HugeIntegerClass x, HugeIntegerClass y) {
        // if x length is greater than y length return true
		if (x.bigInt.length > y.bigInt.length)
			return true;
        // else if x and y lengths are equal return true
		else if (x.bigInt.length == y.bigInt.length) {
			int i = x.bigInt.length;
			if (x.bigInt[i - 1] > y.bigInt[i - 1]) {
				return true;
			}
		}
        // else return false
		return false;
	}

    // isLess Method
    // Takes two huge integers and checks which one is lesser than the other
	public static boolean isLess(HugeIntegerClass x, HugeIntegerClass y) {
        // If x length is lesser than y length return true
		if (x.bigInt.length < y.bigInt.length)
			return true;
        // else if x and y length is equal to each other return ture
		else if (x.bigInt.length == y.bigInt.length) {
			int i = x.bigInt.length;
			if (x.bigInt[i - 1] < y.bigInt[i - 1]) {
				return true;
			}
		}
        // else return false
		return false;
	}
    // isEqual method
    // Takes two huge integers and returns true if they are equal
	public static boolean isEqual(HugeIntegerClass x, HugeIntegerClass y) {
        // if x length is equal to y legnth return true
		if (x.bigInt.length == y.bigInt.length) {
			int n = x.bigInt.length;
			for (int i = 0; i < n; i++) {
				if (x.bigInt[i] != y.bigInt[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
    // addtion method
    // Adds two huge integers
	public static HugeIntegerClass addition(HugeIntegerClass x, HugeIntegerClass y) {
        // get size of x and y
		int xSize = x.bigInt.length;
		int ySize = y.bigInt.length;
        // create result hugeinteger
		HugeIntegerClass result = new HugeIntegerClass();
		int len;
        // if x Size is greater than y size
		if (xSize > ySize) {
			len = xSize + 1;
            // Result of x added to y
			result.bigInt = new int[len];
            // carry operation
			int carry = 0;
			for (int i = 0; i < ySize; i++) {
				int val = x.bigInt[i] + y.bigInt[i] + carry;
				carry = val / 10;
				val = val % 10;
				result.bigInt[i] = val;
			}
			for (int i = ySize; i < xSize; i++) {
				int val = x.bigInt[i] + carry;
				carry = val / 10;
				val = val % 10;
				result.bigInt[i] = val;
				if (i == (len - 1) && carry > 0) {
					result.bigInt[i] = result.bigInt[i] + carry;
				}
			}
		} else {
			len = ySize + 1;
			result.bigInt = new int[len];
			int carry = 0;
			for (int i = 0; i < xSize; i++) {
				int val = x.bigInt[i] + y.bigInt[i] + carry;
				carry = val / 10;
				val = val % 10;
				result.bigInt[i] = val;
			}
			for (int i = xSize; i < ySize; i++) {
				int val = y.bigInt[i] + carry;
				carry = val / 10;
				val = val % 10;
				result.bigInt[i] = val;
				if (i == (len - 1) && carry > 0) {
					result.bigInt[i] = result.bigInt[i] + carry;
				}
			}
		}
        // Return result
		return result;
	}
    
    // subtraction method
	public static HugeIntegerClass subtraction(HugeIntegerClass x, HugeIntegerClass y) {
		if (isLess(x, y)) { // Check to see that x is bigger so that subtraction
							// does not result in a negative number
			System.exit(-1);
		}
        // get size of x and y
		int xSize = x.bigInt.length;
		int ySize = y.bigInt.length;
        // create result hugeinteger that will be returned after the below operations
		HugeIntegerClass result = new HugeIntegerClass();
		result.bigInt = new int[xSize];
		for (int i = 0; i < ySize; i++) {
			int val;
			if (y.bigInt[i] != 0) {
				if (x.bigInt[i] > y.bigInt[i]) {
					val = x.bigInt[i] - y.bigInt[i];
					result.bigInt[i] = val;
				} else if (x.bigInt[i] < y.bigInt[i]) {
					x.bigInt[i + 1] = x.bigInt[i + 1] - 1;
					x.bigInt[i] = x.bigInt[i] + 10;
					val = x.bigInt[i] - y.bigInt[i];
					result.bigInt[i] = val;
				} else {
					result.bigInt[i] = 0;
				}
			}
		}
		for (int i = ySize; i < xSize; i++) {
			result.bigInt[i] = x.bigInt[i];
		}
		return result;
	}

    // Multiplication Method
	public static HugeIntegerClass multiplication(HugeIntegerClass x, HugeIntegerClass y) {
        // get size of x and y
		int xSize = x.bigInt.length;
		int ySize = y.bigInt.length;
        // Create result hugeinteger that will be return after the following operations
		HugeIntegerClass result = new HugeIntegerClass();
		result.bigInt = new int[ySize + xSize];

		for (int i = 0; i < ySize; i++) {
			int carry = 0;
			for (int j = 0; j < xSize; j++) {
				result.bigInt[j + i] = result.bigInt[j + i] + carry + (x.bigInt[j] * y.bigInt[i]);
				carry = result.bigInt[j + i] / 10;
				result.bigInt[j + i] = result.bigInt[j + i] % 10;
			}
			result.bigInt[i + xSize] = result.bigInt[i + xSize] + carry;
		}
        // return result
		return result;
	}

    // Division method
    // Does division of two hugeintegers 
	public HugeIntegerClass division(HugeIntegerClass num, HugeIntegerClass den) {
        // Create hugeinteger of int 0
        HugeIntegerClass zero = new HugeIntegerClass("0");
        zero.bigInt = new int[1];
		zero.bigInt[0] = 0;
        HugeIntegerClass quotient = new HugeIntegerClass("0");
        quotient.bigInt = new int[1];
		quotient.bigInt[0] = 0;
        HugeIntegerClass remainder = num;
        HugeIntegerClass one = new HugeIntegerClass("1");
        one.bigInt = new int[1];
		one.bigInt[0] = 1;
        if(isEqual(den, zero))
            System.exit(-1);

        else if(isEqual(num, den)){
            quotient.bigInt[0] = 1;
        }
        else {
            while (isEqual(remainder, den) || isGreater(remainder, den)){
                HugeIntegerClass tmpQ = new HugeIntegerClass("");
                HugeIntegerClass tmpR = new HugeIntegerClass("");

				tmpQ = addition(quotient, one); 
				quotient = tmpQ;
                tmpR = subtraction(remainder, den); 
                remainder = tmpR;
            }
        }
        this.bigInt = quotient.bigInt;
        /*for(int i = this.bigInt.length - 1; i > 0; i--){
            this.hugeInt.remove(i);
        }*/
        return remainder;
    }
	
	/*public static HugeIntegerClass division(HugeIntegerClass num, HugeIntegerClass den, int flag) {
		HugeIntegerClass quotient = new HugeIntegerClass();
		HugeIntegerClass remainder = new HugeIntegerClass();
		HugeIntegerClass one = new HugeIntegerClass();
		one.bigInt = new int[1];
		one.bigInt[0] = 1;
		if (den.bigInt.length == 1 && den.bigInt[0] == 0) {// cannot divide by 0
			System.exit(-1);
		} else if (isLess(num, den)) {
			return num;
		} else if (isEqual(num, den)) {
			return one;
		} else {
			remainder.bigInt = new int[num.bigInt.length];
			for (int i = 0; i < num.bigInt.length; i++) {
				remainder.bigInt[i] = num.bigInt[i];
			}
			quotient.bigInt = new int[den.bigInt.length];
			// quotient.bigInt[0] = 0;
			System.out.println("\nTrying to Divide\n");
			while (isEqual(remainder, den) || isGreater(remainder, den)) {
				HugeIntegerClass tmpq = new HugeIntegerClass("");
				tmpq = addition(quotient, one);
				quotient.bigInt = tmpq.bigInt;

				HugeIntegerClass tmpr = new HugeIntegerClass("");
				tmpr = subtraction(remainder, den);
				remainder.bigInt = tmpr.bigInt;
			}
		}
		if (flag == 1) {// division
			return quotient;
		} else {// modulus
			return remainder;
		}
	}*/

	/**
	 * Main method to start the game
	 */

    // main method
    // This method was just used for testing. Below is not used in running implementation
	public static void main(String[] args) {
        // Our hugeintegers start off as strings that will be converted to our hugeintegere type
		String str1 = "654654654616516848189798419516516848";
		String str2 = "11111";
		String str3 = "79876154313549";
		String str4 = "87898";
		String str5 = "9999";
        // Convert strings to hugeinteger type
		HugeIntegerClass hic1 = new HugeIntegerClass(str1);
		HugeIntegerClass hic2 = new HugeIntegerClass(str2);
		HugeIntegerClass hic3 = new HugeIntegerClass(str3);
		HugeIntegerClass hic4 = new HugeIntegerClass(str4);
		HugeIntegerClass hic5 = new HugeIntegerClass(str5);
		System.out.println("Printed in Reverse");
        // Print out our huge integers
		System.out.print("\nFirst Big Int: ");
		for (int i = 0; i < hic1.bigInt.length; i++) {
			System.out.print(hic1.bigInt[i]);
		}

		System.out.print("\nSecond Big Int: ");
		for (int i = 0; i < hic2.bigInt.length; i++) {
			System.out.print(hic2.bigInt[i]);
		}

		System.out.print("\nThird Big Int: ");
		for (int i = 0; i < hic3.bigInt.length; i++) {
			System.out.print(hic3.bigInt[i]);
		}

		System.out.print("\nFourth Big Int: ");
		for (int i = 0; i < hic4.bigInt.length; i++) {
			System.out.print(hic4.bigInt[i]);
		}

		System.out.print("\nFifth Big Int: ");
		for (int i = 0; i < hic5.bigInt.length; i++) {
			System.out.print(hic5.bigInt[i]);
		}

		HugeIntegerClass subHics = subtraction(hic2, hic5);

		System.out.print("\nSubtracted: ");
		for (int i = 0; i < subHics.bigInt.length; i++) {
			System.out.print(subHics.bigInt[i]);
		}

		HugeIntegerClass addedHics = addition(hic2, hic5);

		System.out.print("\nAdded: ");
		for (int i = 0; i < addedHics.bigInt.length; i++) {
			System.out.print(addedHics.bigInt[i]);
		}

		HugeIntegerClass multHics = multiplication(hic2, hic5);

		System.out.print("\nMultiplied: ");
		for (int i = 0; i < multHics.bigInt.length; i++) {
			System.out.print(multHics.bigInt[i]);
		}

		HugeIntegerClass divHics = new HugeIntegerClass();
		HugeIntegerClass modHics = new HugeIntegerClass();
		
		modHics = divHics.division(hic2, hic5);

		System.out.print("\nDivided: ");
		for (int i = 0; i < divHics.bigInt.length; i++) {
			System.out.print(divHics.bigInt[i]);
		}

		System.out.print("\nModulus: ");
		for (int i = 0; i < modHics.bigInt.length; i++) {
			System.out.print(modHics.bigInt[i]);
		}

	}// end of main

}
