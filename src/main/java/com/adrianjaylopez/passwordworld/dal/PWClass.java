/** Program name: Jay's Password World
 * Program Description: This program uses a gui to store and retrieve passwords in an embedded database. 
 * Class description: This class deals with storage and getting of password information. 
 * @author: Adrian Lopez
 * Date: 8/7/2013
 * Version: 1.0
 */
package com.adrianjaylopez.passwordworld.dal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;



public class PWClass {
	//declare variables
	List<PasswordClass> jw = new ArrayList<>();
	PasswordClass kablah = null;

	
	protected void update(String folder, String title, String userName, String password, String url, String notes, Date date){
		kablah= new PasswordClass(folder, title, userName, password, url, notes, date);
		jw.add(kablah);
	}
	
	/**This method takes the information from the presentation layer to insert into the passwordclass collection and then
	 * passes it to the datalayer. 
	 *  
	 * @param pw : password information to insert
	 */
	public void update(PasswordClass pw) {
		
		Calendar.getInstance();
		Date date = new Date(Calendar.DAY_OF_WEEK_IN_MONTH);
		pw.setDate(date);

			//add items to the list collection
			jw.add(pw);
	}
	
	/**
	 * This method is used for updating the information of an existing entry in the passwordclass collection and then passes the old
	 * for the foldername and title if it has changed to the datalayer. 
	 * @param oldInfo : old information that is going to be changed
	 * @param newInfo : change old info to this
	 **/
	public void update(String[] oldInfo, PasswordClass newInfo) {
		Calendar.getInstance();
		Date date = new Date(Calendar.DAY_OF_WEEK_IN_MONTH);
		//searches for the index of the object and returns an integer that gets set to the index
		int index = getIndex(oldInfo[0], oldInfo[1]);
		//update the data inside of the collection
		jw.get(index).setFolders(newInfo.getFolders());
		jw.get(index).setTitle(newInfo.getTitle());
		jw.get(index).setUserName(newInfo.getUserName());
		jw.get(index).setPassword(newInfo.getPassword());
		jw.get(index).setUrl(newInfo.getUrl());
		jw.get(index).setNotes(newInfo.getNotes());
		jw.get(index).setDate(date);
	}
	
	/**This method looks for the object and then uses the index to remove it from the passwordclass collection. 
	 * 
	 * @param oldInfo : data to delete
	 */
	public void delete(PasswordClass oldInfo) {
		
		//call the search method and set the index with the value returned
		int index = getIndex(oldInfo.getFolders(), oldInfo.getTitle());
		//remove that instance from the collection
		jw.remove(index);
		
	}
		
	/**
	 * This method returns the entire contents of a row using the folder name and title. 
	 * @param info: information to search
	 * @return: returns a passwordclass object with all the fields filled in
	 */
	public String[] search(PasswordClass info){
		//initialize variable
		String [] results = new String[6];
		//call the getindex method
		int index = getIndex(info.getFolders(), info.getTitle());
		//if the index does not equal -1
		if (index != -1){
			//get the contents of the row and add them to a passwordclass instance
			results[0] = jw.get(index).getFolders();
			results[1] = jw.get(index).getTitle();
			results[2] = (jw.get(index).getUserName());
			results[3] = (jw.get(index).getPassword());
			results[4] =(jw.get(index).getUrl());
			results[5] =(jw.get(index).getNotes());
		}
		//return the password class instance
		return results;
	}
	
	/**This method searches for an object in a list.
	 *  
	 * @param folder = folder name to search for
	 * @param title = title to search for
	 * @return = returns index
	 */
	private int getIndex(String folder, String title){
		//initializes index to the value of -1
		int index = -1;
		//initialize variables
		kablah = new PasswordClass();
		//if the passwordclass collection is not null
		if (jw != null && !jw.isEmpty()){
			index++;//increment index
			//iterates through jw collection
			for (ListIterator<PasswordClass> it = jw.listIterator(); it.hasNext();) {
				//gets the contents of the index that jw is currently on
				kablah = it.next();
				if (kablah.getFolders().compareTo(folder) == 0) {
					//if this instances folder and title equal what was entered in
					if (kablah.getFolders().compareTo(folder) == 0 && kablah.getTitle().compareTo(title) == 0) {
						//break out of the iteration
						break;
					}


				}
			}
		}
		return index;
	}
	
	
	public List<String[]> getFolderNTitle(){
		
		List<String[]> categories = new ArrayList<>();
		for(PasswordClass pwc : jw){
			String[] category = new String[]{(String) pwc.getFolders(), (String) pwc.getTitle()};
			categories.add(category);
			
			
		}
		
		return categories;
	}
}


