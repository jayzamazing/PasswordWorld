/** Program name: Jay's Password World
 * Program Description: This program uses a gui to store and retrieve passwords in an embedded database. 
 * Class description: This class is the executor the the program.
 * @author: Adrian Lopez
 * Date: 8/7/2013
 * Version: 1.0
 */

package com.adrianjaylopez.passwordworld.pl;
//import statements
import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JOptionPane;

import com.adrianjaylopez.passwordworld.bl.BussData;

@SuppressWarnings("serial")
public class PasswordWorld extends JpFrame {
	
	/**
	 * This executes the program. 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//initializes the frame
					frame = new JpFrame();
					//initialize instance of the bussdata class
					buss = new BussData();
					//gets the page that is stored in the config file and if the page is 01,
					//then it changes the card
					if (Integer.parseInt(buss.getPage()) == 01){
						//get the cardlayout and set it to cl
						cl = (CardLayout)(frame.getContentPane().getLayout());
						//get the next card
				        cl.next(frame.getContentPane());  
					}
					//makes the gui visible
					frame.setVisible(true);
					
				} catch (Exception e) {
					//displays an error message if exception is caught
					JOptionPane.showMessageDialog(null, "There was an issue with the application. Error 215");
				}
			}
		});
	}
	
	
	
}
