/** Program name: Jay's Password World
 * Program Description: This program uses a gui to store and retrieve passwords in an embedded database. 
 * Class description: This class deals with the storage and the retrieval of user configurations in a
 * configuration file. Currently this configuration is used to tell the program whether it needs to start
 * on the first or second page. The setappdata sets the config file to store the page data while
 * getappdata retrieves that information for use.  
 * @author: Adrian Lopez
 * Date: 8/7/2013
 * Version: 1.0
 */

package com.adrianjaylopez.passwordworld.dal;
//import of packages
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

;

//only accessible within the jays package, not public
class AppDataStorage {
	//declare and initialize user config file to a file name
	private final String USERS_CONFIG_FILE = "uconnect.cfg";
	//declare variables
    private Properties usersConfig;
    private FileOutputStream out;
    private FileInputStream in;
    private File checkFile;
    
    /**this method takes application data as input and stores it inside a config file
     * 
     * @param page: either 00 or 01 to  indicate the start page
     * @throws IOException
     */
	public void setAppData(String page) throws IOException{
		

			//initializes the usersconfig
			usersConfig = new Properties();
			//initializes the file output stream for writting data to the config file
			out = new FileOutputStream(USERS_CONFIG_FILE);
			//sets the properties for the card number to the string that is entered into the method
			usersConfig.setProperty("Card Number", page);
			//saves the file with the information
			usersConfig.store(out, USERS_CONFIG_FILE);

        
    }
    
	/**this method gets the data that is stored inside of the config file and returns it as a string.
	 * if the file does not exist, then the file is created and the page property is set to 00
	 * 
	 * @return: returns the page the program should start on, either 00 or 01
	 * @throws IOException
	 */
    public String getAppData() throws IOException{
    	//sets the checkfile variable to equal the users_config_file
    	checkFile = new File(USERS_CONFIG_FILE);

			//tests to see it the file exists
			if (!checkFile.exists()){
				//if the file does not exist then create the file
				checkFile.createNewFile();
				//set the card number property to 00
				setAppData("00");
			}
			//initializes user config
			usersConfig = new Properties();
      
			//initializes file input stream to the config file
			in = new FileInputStream(USERS_CONFIG_FILE);
			//loads the config file contents to usersconfig
			usersConfig.load(in);

        //returns the contents that match the string inside of getproperty
        return usersConfig.getProperty("Card Number");
               
    }
    /**this is used to close any open connections on either the input or output streams
     * while I could do it within each class, i did it this way to handle it as a finalized
     * method within the datastorage class
     * 
     * @throws IOException
     */
    public void closeAppData() throws IOException{

			//initializes variables
			usersConfig = null;
			checkFile = null;
			//if the variable in has an input stream open then perform following block
			if (in != null) {
				//close the connection to the file input stream
				in.close();
				//if the variable out has an output stream open then perform following block
			} else if (out != null) {
				//close the connection to the file output stream
				out.close();
			}

    }


    	
	
}
