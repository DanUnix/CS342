/**
 * Team: Francisco Gonzalez, Daniel Pulley
 * NET-ID: fgonza21, dpulley
 * UIN: 679481167, 669874288
 * CS 342 HW3 - RSA Encryption
 * An RSA encryption program.  Encrypts and Decrypts.
 *
 */

import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class RSA {

	HugeIntegerClass p;
	HugeIntegerClass q;
	HugeIntegerClass N;
	HugeIntegerClass M;
	HugeIntegerClass e;
	HugeIntegerClass d;
	int eInt = 0,dInt = 0,nInt = 0;
	HugeIntegerClass one = new HugeIntegerClass("1");
	static String fileName;
	// Constructor
	public RSA() {
		
	}

	// Calculate N = (p * q)
	public HugeIntegerClass getN(HugeIntegerClass x, HugeIntegerClass y)
	{
		return N.multiplication(x,y);
	}
	
	// Calculate M	
	public HugeIntegerClass getM(HugeIntegerClass x, HugeIntegerClass y)
	{
		p = x.subtraction(x, one);
		q = y.subtraction(y, one);
		return M.multiplication(p,q);
		
	}
	
	// Calculate the exponent
	public int getE(HugeIntegerClass x, HugeIntegerClass y)
	{
		int xInt = arrToInt(x.bigInt);
		int yInt = arrToInt(y.bigInt);	 
		return GCD(xInt,yInt);
	}
	// Calculate the inverse of E
	public int getD(HugeIntegerClass x, int y)
	{
		int xInt = arrToInt(x.bigInt);
		//int yInt = arrToInt(y.bigInt);
		return (1 + xInt)/y;	
	}
	
	public int arrToInt(int a[])
	{
		int newInt = 0;
		for(int i = 0; i < a.length; i++)
			newInt += a[i]*Math.pow(10,a.length-i-1);
		return newInt;
	}
	// recursive Euclid Implementation to find the greatest common divisior
	public static int GCD(int p, int q)
	{
		// Base Case
		if(q == 0)
			return p;
		else
			return GCD(q, (p % q));
	}
	

	public void getFileName(String nameOfFile)
	{
		fileName = nameOfFile;
	}

	//Get the value of the messages
	public HugeIntegerClass getMessageValue()
	{
		
		String line = null;
		HugeIntegerClass value = new HugeIntegerClass();
		try{
		
		File messageFile = new File("myblockedfile.txt");
		BufferedReader br = new BufferedReader(new FileReader(messageFile));	
		
		while((line = br.readLine()) != null){
			
			value = new HugeIntegerClass(line);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
	
	// Read the XML file
	public void readFromXMLFile(String f)
	{
		try{
		File XML = new File(f);

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder;

        Document document;

        documentBuilder = documentBuilderFactory.newDocumentBuilder();

        document = documentBuilder.parse(XML);
		if(f.equals("publickey.xml")){
			String evalue = document.getElementsByTagName("evalue").item(0).getTextContent();
			HugeIntegerClass hugeE = new HugeIntegerClass(evalue);
			this.eInt = arrToInt(hugeE.bigInt);	
			System.out.println("EVALUE: " + evalue);
		
        }
		if(f.equals("privatekey.xml")){
			String dvalue = document.getElementsByTagName("dvalue").item(0).getTextContent();
			HugeIntegerClass dVal = new HugeIntegerClass(dvalue);
			this.dInt = arrToInt(dVal.bigInt);
			System.out.println("DVALUE: " + dvalue);
		}
		String nvalue = document.getElementsByTagName("nvalue").item(0).getTextContent();
		HugeIntegerClass hugeN = new HugeIntegerClass(nvalue);
		this.nInt = arrToInt(hugeN.bigInt);
		// If dInt has no value
		if(this.dInt == 0)
			System.out.println("Encrypted Value" + encryption(this.eInt,this.nInt));
		if(this.eInt == 0)
			System.out.println("Decrypted Value" + decryption(this.dInt,this.nInt));	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Encode/decode the message
	public int encryption(int e, int n)
	{
		// C = p^e % n
		String encode;	
		try{
		File message = new File("myblockedfile.txt");
		
		PrintWriter writer = new PrintWriter("encrypted.txt", "UTF-8");
		encode = readMessage(message);
		HugeIntegerClass hugeEncode = new HugeIntegerClass(encode);
		int pVal = arrToInt(hugeEncode.bigInt);
		int result;
		for(int i = 0; i < e; i++)
			pVal *= pVal;	
		result = pVal % n;
		writer.println(result);
		return result;
		}catch(Exception exc){
			exc.printStackTrace();
		}
		return 0;
	}
	
	public int decryption(int d, int n)
	{
		// P = C^d % n
		String decode;	
		try{
		File message = new File("encrypted.txt");
		decode = readMessage(message);
		PrintWriter writer = new PrintWriter("decrypted.txt", "UTF-8");

		HugeIntegerClass hugeDecode = new HugeIntegerClass(decode);
		int CVal = arrToInt(hugeDecode.bigInt);
		int result;
		for(int i = 0; i < d; i++)
			CVal *= CVal;	
		result = CVal % n;
		writer.println(result);
		return result;
		}catch(Exception exc){
			exc.printStackTrace();
		}
		return 0;
	}
	
	//Read the message in the file
	public String readMessage(File file) throws IOException
	{
		int length;
		char[] c = new char[4096];
		final StringBuffer buf = new StringBuffer();
		final FileReader r = new FileReader(file);
		try{
			while((length = r.read(c)) > 0){
				buf.append(c,0,length);
			}
		}finally{
			r.close();
		}
		return buf.toString();


	}
		

	
}
