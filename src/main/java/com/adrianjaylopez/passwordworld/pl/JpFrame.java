/** Program name: Jay's Password World
 * Program Description: This program uses a gui to store and retrieve passwords in an embedded database. 
 * Class description:  
 * @author: Adrian Lopez
 * Date: 8/7/2013
 * Version: 1.0
 */

package com.adrianjaylopez.passwordworld.pl;
//import statements
import com.adrianjaylopez.passwordworld.bl.BussData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Stack;

@SuppressWarnings("serial")
//class that deals with the gui
public class JpFrame extends JFrame {
	//declare variables
	private JPanel contentPane;
	private JTextField userNametextField1;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JTextField userNametextField2;
	private JPasswordField passwordField3;
	private JTextField titleTextField;
	private JTextField userNameTextField;
	private JTextField passwordTextField;
	private JTextField urlTextField;
	private JTextArea notesTextArea;
	private static JTree tree;
	private String[] searchResults;
	protected static JpFrame frame;
	protected static CardLayout cl;
	protected static BussData buss;
	private String[] oldInfo;
	private Stack<String> folderStack;
	@SuppressWarnings("rawtypes")
	final JComboBox folderComboBox;
	final JButton btnSaveButton;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public JpFrame() {
		setTitle("Jays Password World");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel loginScreen = new JPanel();
		contentPane.add(loginScreen, "name_165534806352493");
		loginScreen.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setBounds(194, 97, 115, 14);
		loginScreen.add(lblNewLabel);
		
		userNametextField1 = new JTextField();
		userNametextField1.setBounds(194, 122, 115, 20);
		loginScreen.add(userNametextField1);
		userNametextField1.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(194, 153, 115, 14);
		loginScreen.add(lblPassword);
		
		JLabel lblReenterPassword = new JLabel("Re-enter Password");
		lblReenterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblReenterPassword.setBounds(194, 209, 115, 14);
		loginScreen.add(lblReenterPassword);
		
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(194, 178, 115, 20);
		loginScreen.add(passwordField1);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(194, 236, 115, 20);
		loginScreen.add(passwordField2);
		
		JButton btnLogin = new JButton("Login");
		//action listener for the login button
		btnLogin.addActionListener(new ActionListener() {
                    @Override
			public void actionPerformed(ActionEvent e) {
				try {
					//checks to ensure the username, and password fields have been filled in
					if (userNametextField1.getText().length() > 0 && passwordField1.getPassword().length > 0 && passwordField2.getPassword().length > 0 ){
						//checks to ensure passwordfield1 and passwordfield2 are equal
						if (Arrays.equals(passwordField1.getPassword(), passwordField2.getPassword())){
							//initialize bussdata instance and pass it the username, and password
							buss = new BussData(userNametextField1.getText(), passwordField1.getPassword());
							//initializes oldinfo to an empty array with a length of 2
							oldInfo = new String[]{"", ""};
							//get the cardlayout and set it to cl
							cl = (CardLayout)(frame.getContentPane().getLayout());
							//go to the next two cards
							cl.next(frame.getContentPane());
							cl.next(frame.getContentPane());
							//disable the save button
							btnSaveButton.setEnabled(false);
					} else {
						//shows a message that the two password fields are not equal
						JOptionPane.showMessageDialog(getParent(),"The passwords in both fields must match!","Password Mismatch!",JOptionPane.WARNING_MESSAGE, null);
					}
				} else {
					//shows a message that all the fields must be filled out
					JOptionPane.showMessageDialog(getParent(),"All fields must be filled in.","Empty Fields!",JOptionPane.WARNING_MESSAGE, null);
				
				}
				} catch (Exception e1) {
					//show an error message to the user
					JOptionPane.showMessageDialog(null, "There was an issue with the application. Error 1001!");
				}
			}
				
		});
		btnLogin.setBounds(204, 343, 89, 23);
		loginScreen.add(btnLogin);
		
		JPanel loginScreen2 = new JPanel();
		contentPane.add(loginScreen2, "name_165558853314734");
		loginScreen2.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(195, 108, 115, 14);
		loginScreen2.add(lblUserName);
		
		userNametextField2 = new JTextField();
		userNametextField2.setBounds(195, 133, 115, 20);
		loginScreen2.add(userNametextField2);
		userNametextField2.setColumns(10);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword_1.setBounds(195, 164, 115, 14);
		loginScreen2.add(lblPassword_1);
		
		passwordField3 = new JPasswordField();
		passwordField3.setBounds(195, 189, 115, 20);
		loginScreen2.add(passwordField3);
		
		JButton btnLogin_1 = new JButton("Login");
		btnLogin_1.addActionListener(new ActionListener() {
			//action listener for login button on the second page, used when a database is already created
			@SuppressWarnings("unchecked")
                    @Override
			public void actionPerformed(ActionEvent e) {
				try {
					//checks to ensure the username, and password fields have been filled in
				if (userNametextField2.getText().length() > 0 && passwordField3.getPassword().length > 0){
					//initialize bussdata instance and pass it the username, and password
					buss = new BussData(userNametextField2.getText(), passwordField3.getPassword());
					//get the cardlayout and set it to cl
					cl = (CardLayout)(frame.getContentPane().getLayout());
					//sets the items in the jtree		
					buss.tree();
					//sets the treenode object to the tree from the business layer
					DefaultMutableTreeNode root = buss.getTree();
					//sets the model for the jtree
					tree.setModel(new DefaultTreeModel(root));
					//go to the next card
					cl.next(frame.getContentPane());
					//initialize variables
					folderStack = new Stack<>();
					//get the folders for the combobox and set them to the folderstack
					folderStack = buss.getFolders();
					//set save button to disabled
					btnSaveButton.setEnabled(false);
					//iterate through the stack and add the items to the combo box
					for (String i : folderStack){
						folderComboBox.addItem(i);
					}
					//set the starting index to nothing
					folderComboBox.setSelectedIndex(-1);        
				} else {
					//shows a message that all the fields must be filled out
					JOptionPane.showMessageDialog(getParent(),"All fields must be filled in.","Empty Fields!",JOptionPane.WARNING_MESSAGE, null);
				}
				//initializes oldinfo to an empty array with a length of 2
				oldInfo = new String[]{"", ""};
				} catch (Exception e1) {
					//if an exception occurs then display error message
					JOptionPane.showMessageDialog(null, "There was an issue with the application. Error 1002!");
				}
			
			}
		});
		btnLogin_1.setBounds(209, 297, 89, 23);
		loginScreen2.add(btnLogin_1);
		
		JPanel passwordScreen = new JPanel();
		contentPane.add(passwordScreen, "name_165562306153187");
		passwordScreen.setLayout(null);
		
		JLabel lblFolders = new JLabel("Folders");
		lblFolders.setBounds(10, 11, 46, 14);
		passwordScreen.add(lblFolders);
	
		folderComboBox = new JComboBox();
		folderComboBox.setBounds(10, 36, 229, 20);
		passwordScreen.add(folderComboBox);
		
		JLabel lblName = new JLabel("Title");
		lblName.setBounds(10, 67, 46, 14);
		passwordScreen.add(lblName);
		
		titleTextField = new JTextField();
		titleTextField.setEditable(false);
		titleTextField.setBounds(10, 89, 229, 20);
		passwordScreen.add(titleTextField);
		titleTextField.setColumns(10);
		
		JLabel lblUserName_1 = new JLabel("User Name");
		lblUserName_1.setBounds(10, 120, 98, 14);
		passwordScreen.add(lblUserName_1);
		
		userNameTextField = new JTextField();
		userNameTextField.setEditable(false);
		userNameTextField.setBounds(10, 145, 229, 20);
		passwordScreen.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JLabel lblPassword_2 = new JLabel("Password");
		lblPassword_2.setBounds(10, 176, 98, 14);
		passwordScreen.add(lblPassword_2);
		
		passwordTextField = new JTextField();
		passwordTextField.setEditable(false);
		passwordTextField.setBounds(10, 201, 229, 20);
		passwordScreen.add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(10, 232, 46, 14);
		passwordScreen.add(lblUrl);
		
		urlTextField = new JTextField();
		urlTextField.setEditable(false);
		urlTextField.setBounds(10, 257, 229, 20);
		passwordScreen.add(urlTextField);
		urlTextField.setColumns(10);
		
		JLabel lblNotes = new JLabel("Notes");
		lblNotes.setBounds(10, 288, 46, 14);
		passwordScreen.add(lblNotes);
		
		tree = new JTree();
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			//action listener for the jtree on page 3
                    @Override
			public void valueChanged(TreeSelectionEvent arg0) {
				//if the selection is one of the sub items and not one of the main folders then do the following
				if (2 < arg0.getPath().getPathCount()){
					//if the arraysize of oldinfo is greater than 2 then perform this block
					if (oldInfo.length > 2){
						//calls the search method and sets the results equal to searchresults
						searchResults = buss.search(oldInfo[0], oldInfo[1]);
						//goes through each property and if the field is null, then it returns an empty string
						//folder and title are not optional fields so search result 0 and 1 will never be empty
						if ("".equals(searchResults[0])){
							folderComboBox.setSelectedItem("");
						} else {
							folderComboBox.setSelectedItem(searchResults[0]);
						}
						if ("".equals(searchResults[1])){
							titleTextField.setText("");
						} else {
							titleTextField.setText(searchResults[1]);
						}
						if ("".equals(searchResults[2])){
							userNameTextField.setText("");
						} else {
							userNameTextField.setText(searchResults[2]);
						}
						if ("".equals(searchResults[3])){
							passwordTextField.setText("");
						} else {
							passwordTextField.setText(searchResults[3]);
						}
						if ("".equals(searchResults[4])){
							urlTextField.setText("");
						} else {
							urlTextField.setText(searchResults[4]);
						}
						if("".equals(searchResults[5])){
							notesTextArea.setText("");
						} else{
							notesTextArea.setText(searchResults[5]);
						}
						//initializes array to hold the foldername and title
						oldInfo = new String[]{oldInfo[0], oldInfo[1]};
					} else {
					//call the search method and pass it the folder and the title and get the search results
					searchResults = buss.search(arg0.getPath().getPathComponent(1).toString(), arg0.getPath().getPathComponent(2).toString());
					/**
					 * searchresults[0] = foldername
					 * searchresults[1] = title
					 * searchresults[2] = username
					 * searchresults[3] = password
					 * searchresults[4] = url
					 * searchresults[5] = notestext
					 *
					 */
					//goes through each property and if the field is null, then it returns an empty string
					//folder and title are not optional fields so search result 0 and 1 will never be empty
					if ("".equals(searchResults[0])){
						folderComboBox.setSelectedItem("");
					} else {
						folderComboBox.setSelectedItem(searchResults[0]);
					}
					if ("".equals(searchResults[1])){
						titleTextField.setText("");
					} else {
						titleTextField.setText(searchResults[1]);
					}
					if ("".equals(searchResults[2])){
						userNameTextField.setText("");
					} else {
						userNameTextField.setText(searchResults[2]);
					}
					if ("".equals(searchResults[3])){
						passwordTextField.setText("");
					} else {
						passwordTextField.setText(searchResults[3]);
					}
					if ("".equals(searchResults[4])){
						urlTextField.setText("");
					} else {
						urlTextField.setText(searchResults[4]);
					}
					if("".equals(searchResults[5])){
						notesTextArea.setText("");
					} else{
						notesTextArea.setText(searchResults[5]);
					}
					oldInfo = new String[]{searchResults[0], searchResults[1]};
				}
				}
			}
		});
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Passwords") {
				{
				}
			}
		));
		tree.setBounds(258, 10, 230, 360);
		passwordScreen.add(tree);
		
		JButton btnNewButton = new JButton("New");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		//action listener for the new button
		btnNewButton.addActionListener(new ActionListener() {
                    @Override
			public void actionPerformed(ActionEvent e) {
				//set the save button to enabled
				btnSaveButton.setEnabled(true);
				//goes through each box and clears out any text
				folderComboBox.setSelectedItem(null);
		        titleTextField.setText("");
		        userNameTextField.setText("");
		        passwordTextField.setText("");
		        urlTextField.setText("");
		        notesTextArea.setText("");
		        //sets each box as editable
		        folderComboBox.setEditable(true);
		        titleTextField.setEditable(true);
		        userNameTextField.setEditable(true);
		        passwordTextField.setEditable(true);
		        urlTextField.setEditable(true);
		        notesTextArea.setEditable(true);
		        //initializes array to empty with a length of 2
		        oldInfo = new String[]{"", ""};
			}
		});
		btnNewButton.setBounds(258, 381, 70, 23);
		passwordScreen.add(btnNewButton);
		
		JButton btnEditButton = new JButton("Edit");
		btnEditButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		//action listener for the edit button
		btnEditButton.addActionListener(new ActionListener() {
                    @Override
			public void actionPerformed(ActionEvent e) {
				//set the save button to enabled
				btnSaveButton.setEnabled(true);
				//if the title field is not null
				if (null != titleTextField.getText()) {
					//set all fields to editable
		            folderComboBox.setEditable(true);
		            titleTextField.setEditable(true);
		            userNameTextField.setEditable(true);
		            passwordTextField.setEditable(true);
		            urlTextField.setEditable(true);
		            notesTextArea.setEditable(true);
		        }
			}
		});
		btnEditButton.setBounds(338, 381, 70, 23);
		passwordScreen.add(btnEditButton);
		
		JButton btnDeleteButton = new JButton("Delete");
		btnDeleteButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		//action listener for the delete button
		btnDeleteButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
                    @Override
			public void actionPerformed(ActionEvent e) {
				//if the tree is selected
				if (tree.getSelectionPath() != null){
					//if the path is greater than 2
					if(tree.getSelectionPath().getPathCount() > 2){
						//gets the name of the folder and title and gives it to the delete method
						try {
							//call the delete method and pass the 2 paths indicating the foldername and title
							buss.delete(tree.getSelectionPath().getPathComponent(1).toString(), tree.getSelectionPath().getPathComponent(2).toString());
					} catch (Exception e1) {
						//show error message to user
						JOptionPane.showMessageDialog(null, "There was an issue with the application. Error 1003!");
					}
						//sets the items in the jtree		
						buss.tree();
						DefaultMutableTreeNode root = buss.getTree();
						//sets the model for the jtree
						tree.setModel(new DefaultTreeModel(root));
						//removes the items from each box
						folderComboBox.removeAllItems();
						folderComboBox.setSelectedItem("");
						titleTextField.setText("");
						userNameTextField.setText("");
						passwordTextField.setText("");
						urlTextField.setText("");
						notesTextArea.setText("");
						//initialize variable
						folderStack = new Stack<>();
						//get all the folders and set it to foderstack
						folderStack = buss.getFolders();
						//iterate through the stack
						for (String i : folderStack){
							//set the contents of the combo box to the stack
							folderComboBox.addItem(i);
						}
						//set the selected index to nothing
						folderComboBox.setSelectedIndex(-1);
					}
				}
			}	
		});
		btnDeleteButton.setBounds(418, 381, 70, 23);
		passwordScreen.add(btnDeleteButton);
		
		JButton btnCopyButton = new JButton("Copy");
		btnCopyButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		//action listener for the copy button
		btnCopyButton.addActionListener(new ActionListener() {
                    @Override
			public void actionPerformed(ActionEvent arg0) {
				//declare and initialize variable
				String allFields = "";
				//set all fields to not be editable
		        folderComboBox.setEditable(false);
		        titleTextField.setEditable(false);
		        userNameTextField.setEditable(false);
		        passwordTextField.setEditable(false);
		        urlTextField.setEditable(false);
		        notesTextArea.setEditable(false);
		        //if the fields are not empty, then get the text for each field
		        if (!"".equals(titleTextField.getText())) {
		        	allFields += titleTextField.getText() + "\n";
		        }
		        if (!"".equals(userNameTextField.getText())) {
		        	allFields += userNameTextField.getText() + "\n";
		        }
		        if (!"".equals(passwordTextField.getText())) {
		        	allFields += passwordTextField.getText() + "\n";
		        }
		        if (!"".equals(urlTextField.getText())) {
		        	allFields += urlTextField.getText() + "\n";
		        }
		        if (!"".equals(notesTextArea.getText())) {
		        	allFields += notesTextArea.getText();
		        }
		        //pass the fields to the string  selection
		        StringSelection stringSelection = new StringSelection(allFields);
		        //declare and initialize the clipboard
		        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		        //add the string selection to the clipboar
		        clipboard.setContents(stringSelection, stringSelection);
				
			}
		});
		btnCopyButton.setBounds(89, 381, 70, 23);
		passwordScreen.add(btnCopyButton);
		
		btnSaveButton = new JButton("Save");
		btnSaveButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		/*this is the action listener for the save button. This takes the contents of the textfields
		*and then stores them using the update method in the bussdata class
		*/
		btnSaveButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
                    @Override
			public void actionPerformed(ActionEvent arg0) {
				//set the save button to disabled
				btnSaveButton.setEnabled(false);
				//if both the length of the foldercombobox and the title field are greater that 0 then perform this block
				if (folderComboBox.getSelectedItem().toString().length() > 0 && titleTextField.getText().length() > 0){
					//if the title is not blank in the oldinfo array and the foldername or title from the textfields match the oldinfo array
					if (oldInfo[1].compareTo("") != 0 && folderComboBox.getSelectedItem().toString().trim().compareTo(oldInfo[0]) == 0 || 
							titleTextField.getText().trim().compareTo(oldInfo[1]) == 0){
							try {
								//call the update method and pass in the old data and the new data to be updated
								buss.update(oldInfo[0], oldInfo[1], folderComboBox.getSelectedItem().toString().trim(), titleTextField.getText().trim(), 
										userNameTextField.getText().trim(), passwordTextField.getText().trim(), 
										urlTextField.getText(), notesTextArea.getText());
							} catch (Exception e) {
								//shows an error message to the user
								JOptionPane.showMessageDialog(null, "There was an issue with the application. Error 1004!");
							}
					//otherwise perform this block		
					} else {
						try {
							//call the update method for insertion of data
							buss.update(folderComboBox.getSelectedItem().toString().trim(), titleTextField.getText().trim(), 
									userNameTextField.getText().trim(), passwordTextField.getText().trim(), 
									urlTextField.getText(), notesTextArea.getText());
							} catch (Exception e) {
								//shows an error message to the user
								JOptionPane.showMessageDialog(null, "There was an issue with the application. Error 1005!");
							}		
					}
					//fill the oldinfo array, more used for the length for an if block in teh application
					oldInfo = new String[]{folderComboBox.getSelectedItem().toString().trim(), titleTextField.getText().trim(), 
							userNameTextField.getText().trim(), passwordTextField.getText().trim(), 
							urlTextField.getText(), notesTextArea.getText()};
					//set all fields to not be editable
					folderComboBox.setEditable(false);
					titleTextField.setEditable(false);
					userNameTextField.setEditable(false);
					passwordTextField.setEditable(false);
					urlTextField.setEditable(false);
					notesTextArea.setEditable(false);
					//sets the items in the jtree		
			        buss.tree();
			        DefaultMutableTreeNode root = buss.getTree();
			        //sets the model for the jtree
			        tree.setModel(new DefaultTreeModel(root));
			        //remove all items from the combo box
			        folderComboBox.removeAllItems();
			        //initialize variable
			        folderStack = new Stack<>();
			        //set the folderstack to the folders
			        folderStack = buss.getFolders();
			        //iterate through the stack
			        for (String i : folderStack){
			        	//add the folders to the combobox
			        	folderComboBox.addItem(i);
			        }
			        //sets the combobox to the folder of the item that was updated
					folderComboBox.setSelectedItem(oldInfo[0]);
					//initializes array to a length of 2
					oldInfo = new String[]{"", ""};
				//otherwise do this block	
				} else {
					//if the folderbox or title fields are not filled in, then show an error message
					JOptionPane.showMessageDialog(getParent(),"Both the folderbox and the title field must be filled in.","Missing information!",JOptionPane.WARNING_MESSAGE, null);
				}
				
			}
		});
		btnSaveButton.setBounds(10, 381, 70, 23);
		passwordScreen.add(btnSaveButton);
		
		JButton btnCancelButton = new JButton("Cancel");
		btnCancelButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		//action listener for the cancel button
		btnCancelButton.addActionListener(new ActionListener() {
                    @Override
			public void actionPerformed(ActionEvent e) {
				//gets the last treeselection path if its available
				if (null != tree.getSelectionPath() && 2 < tree.getSelectionPath().getPathCount()) {
					//the search method is called for the tree selection and returned
					searchResults = buss.search(tree.getSelectionPath().getPathComponent(1).toString(), tree.getSelectionPath().getPathComponent(2).toString());
					//fills in the information depending on if the properties are empty or not
					if ("".equals(searchResults[0])){
						folderComboBox.setSelectedItem("");
					} else {
						folderComboBox.setSelectedItem(searchResults[0]);
					}
					if ("".equals(searchResults[1])){
						titleTextField.setText("");
					} else {
						titleTextField.setText(searchResults[1]);
					}
					if ("".equals(searchResults[2])){
						userNameTextField.setText("");
					} else {
						userNameTextField.setText(searchResults[2]);
					}
					if ("".equals(searchResults[3])){
						passwordTextField.setText("");
					} else {
						passwordTextField.setText(searchResults[3]);
					}
					if ("".equals(searchResults[4])){
						urlTextField.setText("");
					} else {
						urlTextField.setText(searchResults[4]);
					}
					if("".equals(searchResults[5])){
						notesTextArea.setText("");
					} else{
						notesTextArea.setText(searchResults[5]);
					}
				//if none of the above happens, then all the fields are set to be empty
				} else {
		            folderComboBox.setSelectedItem("");
		            titleTextField.setText("");
		            userNameTextField.setText("");
		            passwordTextField.setText("");
		            urlTextField.setText("");
		            notesTextArea.setText("");
		        }
				//set all the boxes to be empty
		        folderComboBox.setEditable(false);
		        titleTextField.setEditable(false);
		        userNameTextField.setEditable(false);
		        passwordTextField.setEditable(false);
		        urlTextField.setEditable(false);
		        notesTextArea.setEditable(false);
				//initializes array and sets its length to 2
		        oldInfo = new String[]{"", ""};
			} 
		});
		btnCancelButton.setBounds(169, 381, 70, 23);
		passwordScreen.add(btnCancelButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 306, 229, 64);
		passwordScreen.add(scrollPane);
		
		notesTextArea = new JTextArea();
		notesTextArea.setEditable(false);
		notesTextArea.setLineWrap(true);
		scrollPane.setViewportView(notesTextArea);
	}
}
