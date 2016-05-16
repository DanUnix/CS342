/**
 * Team: Francisco Gonzalez, Daniel Pulley
 * NET-ID: fgonza21, dpulley
 * UIN: 679481167, 669874288
 * CS 342 HW3 - RSA Encryption
 * An RSA encryption program.  The Message Block class takes a string and converts it to blocked numbers or takes blocked numbers and converts them to strings.
 *
 */
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;

// Public class
public class RSAGUI extends JFrame {
    // Create new JButtons for JFrame
	private javax.swing.JButton crtKeys;
	private javax.swing.JButton msgBlock;
	private javax.swing.JButton msgUnblock;
	private javax.swing.JButton encrypt;

    // Constructor
	public RSAGUI() {
        // Name the Window
		super("RSA Encryption");
        // Add buttons on program start
	    addButtons();
	  }
    
    // addButtons()
    // This function adds the necessary buttons to our GUI
	private void addButtons() {
        // Create keys button
		crtKeys = new JButton();
        // Block message button
		msgBlock = new JButton();
        // unblock message button
		msgUnblock = new JButton();
        // Encrypt/decrypt button
		encrypt = new JButton();
        
        // Close window operation
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Set Text to create keys button
		crtKeys.setText("Create Keys");
        // Add actionlistener to create keys button
		crtKeys.addActionListener(new ActionListener() {
            // have create keys perform create keys action when pressed
			public void actionPerformed(ActionEvent e) {
				crtKeysActionPerformed(e);
			}
		});

        // Set Text to the block message button
		msgBlock.setText("Block Message");
        // Add Actionlistener to the message block button 
		msgBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                // Perform the message Block action when pressed
				msgBlockActionPerformed(e);
			}
		});

        // Set text for unblock message button
		msgUnblock.setText("Unblock Message");
        // Add ActionListner to the message unblocking button
		msgUnblock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                // Perform the message unblock action when button is pressed
				msgUnblockActionPerformed(e);
			}
		});
    
        // Set text for encrypt and decrypt button
		encrypt.setText("Encrypt/Decrypt");
        // Add Action listner to encrpyt/decrypt button
		encrypt.addActionListener(new ActionListener() {
            // Perform action when encrpyt/decrypt button is pressed
			public void actionPerformed(ActionEvent e) {
				encryptActionPerformed(e);
			}
		});
        // Create JFrame container
		Container cont = getContentPane();
        // Use gridlayout 
		cont.setLayout(new GridLayout(10, 10, 10, 10));
        // Set the preferred button size of create keys in the container
		crtKeys.setPreferredSize(new Dimension(100, 50));
        // Add create keys button to container
		cont.add(crtKeys);
        // Set the preferred button size to message block button   
		msgBlock.setPreferredSize(new Dimension(100, 50));
        // Add message block button to the container
		cont.add(msgBlock);
        // Set preferred button size to message unblock button
		msgUnblock.setPreferredSize(new Dimension(100, 50));
        // Add message unblock button to the container
		cont.add(msgUnblock);
        // Set preferred button size to encrypt and decrypt button
		encrypt.setPreferredSize(new Dimension(100, 50));
        // Add encrypt and decrypt button to container
		cont.add(encrypt);

        // Set Window size
		setSize(200,320);
        // Set the Windows visible so users can see it
		setVisible(true);
	}
    
    // msgBlockActionPerformed
    // Function for when the message block button is pressed
	private void msgBlockActionPerformed(ActionEvent evt) {
        // Initial block size
		int blockSize = 0;
        // Get the input from JOptionPane
		String inputName = JOptionPane.showInputDialog("Please enter name of file to be blocked: ");
        // If input is null then prompt user with error message
		if (inputName == null) {
			System.out.println("No File entered.");
			return;
		}
        // Prompt user for block size
		String block = JOptionPane.showInputDialog("Please enter block size: ");
        // If block size integered is correct set blockSize
		try {
			blockSize = Integer.parseInt(block);
		} catch (NumberFormatException e) {     // If nothing entered, tell the uesr
			System.out.println("Nothing entered");
			return;
		}
        // Create new file name for output of the blocked message
		String outputName = JOptionPane.showInputDialog("Please enter name of blocked file: ");
        // if output name is null then tell user they did not enter anything
		if (outputName == null) {
			System.out.println("Nothing entered");
			return;
		}
        // Create new blocked file
		MessageBlock blockFile = new MessageBlock(inputName, outputName);
		blockFile.block(blockSize);
	}
    
    // Create keys action
	private void crtKeysActionPerformed(ActionEvent e) {
        // Create a new Key object
		Keys keyGen = new Keys();
        // Run the createKeys method from the Key object to create new keys
		keyGen.crtKeys();
	}
        
    // Message unblock action
	private void msgUnblockActionPerformed(ActionEvent e) {
        // Create String that gets the input name of the blocked file
		String inputName = JOptionPane.showInputDialog("Please enter name of blocked file: ");
        // If input name is null then tell user that they did not enter anything
		if (inputName == null) {
			System.out.println("Nothing entered");
			return;
		}
        // Prompt the user for a name of the output file for the unblocked message
		String outputName = JOptionPane.showInputDialog("Please enter name of unblocked file: ");
        // If Entered name is null then tell user they did not enter anything
		if (outputName == null) {
			System.out.println("Nothing entered");
			return;
		}
		// Create new unblocked file using the given input and output name 
		MessageBlock unBlockFile = new MessageBlock(inputName, outputName);
        // Unblock the file
		unBlockFile.unBlock();
	}
    // encrypt Action Performed
	private void encryptActionPerformed(ActionEvent evt) {
        // Get inputFile name from the user 
		String inputFile = JOptionPane.showInputDialog("Please enter name of file to encrypt/decrypt: ");
        // Create new Object of RSA class type
		RSA object = new RSA();
        // Read from input XML file
		object.readFromXMLFile(inputFile);
        // Get message value
		object.getMessageValue();
		
		
	}
    
    // main method	
	public static void main(String args[]) {
        // Create new object of the class type to run program
		RSAGUI app = new RSAGUI();
	}

}
