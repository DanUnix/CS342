
/**
 * Team: Francisco Gonzalez, Daniel Pulley
 * NET-ID: fgonza21, dpulley
 * UIN: 679481167, 669874288
 * CS 342 HW3 - RSA Encryption
 * An RSA encryption program.  Creates Keys.
 *
 */

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Keys extends JFrame {
	private JButton generateKey;
	private JButton input;
	public RSA r;
	public HugeIntegerClass N, M;
	int e, d;
	// The mininum value of key
	public static int min = 3;

	// Constructor
	public void crtKeys() {
		setLayout(new GridLayout(10, 10, 10, 10));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 320);
		setVisible(true);

		generateKey = new JButton("Generate Key");
		generateKey.setPreferredSize(new Dimension(100, 50));
		input = new JButton("User Key");
		input.setPreferredSize(new Dimension(100, 50));

		add(generateKey);
		add(input);

		buttonActions b = new buttonActions();
		generateKey.addActionListener(b);
		input.addActionListener(b);
	}
	//Parses the array of the big integer
	public int arrToInt(int a[]) {
		int newInt = 0;
		for (int i = 0; i < a.length; i++)
			newInt += a[i] * Math.pow(10, a.length - i - 1);
		return newInt;
	}
	
	//Creates keys based on values p and q values
	public void generateKeys(HugeIntegerClass p, HugeIntegerClass q) {
		r = new RSA();
		N = r.getN(p, q);
		M = r.getM(p, q);
		e = r.getE(N, M);
		d = r.getD(M, e);

		// Sanity Check by printing out Values
		System.out.println("p value = " + arrToInt(p.bigInt));
		System.out.println("q value = " + arrToInt(q.bigInt));
		System.out.println("N value = " + arrToInt(N.bigInt));
		System.out.println("M(phi) value = " + arrToInt(M.bigInt));
		System.out.println("e value = " + e);
		System.out.println("d value = " + d);
	
		publickey(e,arrToInt(N.bigInt));
		privatekey(d,arrToInt(N.bigInt));
	}
	
	
	// Checks to see if the numbers used to seed the keys are prime
	public static int isPrime(int n) {
		int i;
		if (n == 2)
			return 0;
		if (n < 2)
			return 0;
		if (0 == n % 2)
			return 0;
		for (i = min; (i * i) <= n; i++)
			if ((n % i) == 0)
				return 0;
		return 1;
	}

	// Program defined p and q from file
	public void pDefined() {
		String[] empty = null;
		ArrayList<String> primes = new ArrayList<String>();
		Random rand = new Random();
		RSA r2 = new RSA();
		// Access the file and store values into the array
		try {
			BufferedReader myFileReader = new BufferedReader(new FileReader("numbers.txt"));
			String myString = null;
			while ((myString = myFileReader.readLine()) != null) {
				primes.add(myString);
			}
			empty = primes.toArray(new String[19]);
			myFileReader.close();
		} catch (FileNotFoundException exception) {
			System.out.println(exception);
		} catch (IOException exception) {
			System.out.println(exception);
		}

		HugeIntegerClass hugeP = new HugeIntegerClass(empty[rand.nextInt(19)]);
		HugeIntegerClass hugeQ = new HugeIntegerClass(empty[rand.nextInt(19)]);
		generateKeys(hugeP, hugeQ);
	}
	
	// Generates the public key used by others to encrypt/decrypt messages
	public void publickey(int x, int y) {

		try {
			String key = "<rsakey>\n" + "\t<evalue>" + x + " </evalue>\n" + "\t<nvalue>" + y + " </nvalue>\n"
					+ "</rsakey>";
			File myFile = new File("publickey");

			if (!myFile.exists())
				myFile.createNewFile();
			FileWriter myFileWriter = new FileWriter(myFile.getAbsoluteFile());
			BufferedWriter myBfWriter = new BufferedWriter(myFileWriter);
			myBfWriter.write(key);
			myBfWriter.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	// Generates the private key for user to encrypt/decrypt messages
	public void privatekey(int x, int y) {

		try {
			String key = "<rsakey>\n" + "\t<dvalue>" + x + " </dvalue>\n" + "\t<nvalue>" + y + " </nvalue>\n"
					+ "</rsakey>";
			File myFile = new File("privatekey");

			if (!myFile.exists())
				myFile.createNewFile();
			FileWriter myFileWriter = new FileWriter(myFile.getAbsoluteFile());
			BufferedWriter myBfWriter = new BufferedWriter(myFileWriter);
			myBfWriter.write(key);
			myBfWriter.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	// Initiates actions based on button clicks
	private class buttonActions implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// Generate key from program
			if (e.getSource() == generateKey)
				pDefined();
			// Get values from the user
			if (e.getSource() == input) {
				// get p value
				String p = JOptionPane.showInputDialog(null, "Enter Prime Number: ", "p",
						JOptionPane.INFORMATION_MESSAGE);
				// Convert string into integer
				int pInt = Integer.parseInt(p);
				HugeIntegerClass hugeP = new HugeIntegerClass(p);

				// Check and see if entered value is prime number
				while (pInt < 127 || isPrime(pInt) == 0) {
					pInt = Integer.parseInt(JOptionPane.showInputDialog(null, "Input has to be greater than 127!"));
					System.exit(0);
				}

				// get q value
				String q = JOptionPane.showInputDialog(null, "Enter Prime Number: ", "q",
						JOptionPane.INFORMATION_MESSAGE);
				// Convert string into integer
				int qInt = Integer.parseInt(q);
				HugeIntegerClass hugeQ = new HugeIntegerClass(q);

				while (qInt < 127 || isPrime(qInt) == 0) {
					qInt = Integer.parseInt(JOptionPane.showInputDialog(null, "Input has to be greater than 127!"));
					System.exit(0);
				}
				// Generate the keys
				generateKeys(hugeP, hugeQ);
			}
		}
	}
}
