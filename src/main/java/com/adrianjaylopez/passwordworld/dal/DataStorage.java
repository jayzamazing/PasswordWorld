/**
 * Program name: Jay's Password World
 * Program Description: This program uses a gui to store and retrieve passwords in an embedded database.
 * Class description: This class is basically where everything comes together and interacts with the
 * business layer. This class brings all of the rest of the classes in this package together to interact
 * with the database to create, update, delete, and insert various items inside of the embedded database.
 *
 * @author: Adrian Lopez
 * Date: 8/7/2013
 * Version: 1.0
 */
package com.adrianjaylopez.passwordworld.dal;

//import of packages

import com.adrianjaylopez.passwordworld.dal.connections.IQueries;
import com.adrianjaylopez.passwordworld.dal.connections.LocalQueries;

import javax.sql.rowset.CachedRowSet;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

//implements runnable to create threads
public class DataStorage {
    // private DBConnections connection = new DBConnections();
    private String page;
    private PWClass pw = new PWClass();
    private IQueries storage;


    /**
     * This is used to login to the application.
     *
     * @param userName
     *            : user name to create a user or connect to existing databasee
     * @param password
     *            : password to connect to database and part of salt for
     *            encryption key
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public DataStorage(String userName, char[] password, String page) throws SQLException, InstantiationException,
            IllegalAccessException, ClassNotFoundException, IOException {

        // initializes variables
        this.page = page;
        storage = new LocalQueries();
        storage.setCredentials(userName, password);


        // if the page number equal 00 the execute
        if (this.page.compareTo("00") == 0) {
            // calls the firstpage method
            FirstPage();
            // otherwise do this bloc
        } else {
            // calls the secondpage method
            SecondPage();
        }

    }

    /**
     * This is used to get the page number that the application starts on.
     *
     * @throws IOException
     *
     */

    public DataStorage() throws IOException {

        // call to the setpage method
        SetPage();

    }

    /**
     * This method gets the page number for the application to start on and sets
     * it to the page variable.
     *
     * @throws IOException
     */
    public void SetPage() throws IOException {
        AppDataStorage appData = new AppDataStorage();
        try {
            // gets the starting page from the config file and stores it in page
            this.page = appData.getAppData();
        } finally {
            // closes any open connections to the config file

            appData.closeAppData();

        }
    }

    /**
     * This method is called the the page number is 00 and it creates the
     * database and sets the config file to the next page.
     *
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void FirstPage() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException,
            IOException {
        AppDataStorage appData = new AppDataStorage();
        try {


            // sets the config file to store 01 for the beginning page number
            appData.setAppData("01");
            this.page = "01";
            // method used to create the database
            storage.dbCreate();
        } finally {

            // closes open connections for the config file
            appData.closeAppData();


        }
    }

    /**
     * This method is called when the page number is 01 and the database has
     * already been created. It connects to the database and places the data in
     * a cachedrowset object.
     *
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public void SecondPage() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {


        // call the dbquery all method
        storage.dbQueryAll();

    }

    /**
     * Method to insert into the cache and the database.
     *
     * @param que
     *            : what to insert into the cachedrowset and database
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public void DBInsert(PasswordClass que) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        String [] update = new String[]{que.getFolders(), que.getTitle(), que.getUserName(), que.getPassword(),
                que.getUrl(), que.getNotes()};
			/*
			 * calls the dbinsert method and passes in the query to be inserted
			 * into the database, the connection to use, and the password cache
			 * to insert
			 */
        storage.dbInsert(update);

    }

    /**
     * Method to update the cache and database.
     *
     * @param oldI
     *            : used to store old username and password if they have
     *            changed.
     * @param que
     *            : what to update in the cachedrowset and database
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public void DBUpdate(String[] oldI, PasswordClass que) throws SQLException, InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        String [] update = new String[]{que.getFolders(), que.getTitle(), que.getUserName(), que.getPassword(),
        que.getUrl(), que.getNotes()};
        // passes in the old information, the query representing the new
        // data, connection to be used, and cache object to be updated
        storage.dbUpdate(oldI, update);


    }

    /**
     * Method to delete from the cache and the database
     *
     * @param que
     *            : what to delete from the cachedrowset and database
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public void DBDelete(PasswordClass que) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        String[] old = new String[] {que.getFolders(), que.getTitle()};
        // passes in the query, connection to be used, and cache object to
        // be deleted
        storage.dbDelete(old);


    }

    /**
     * Method to get the page number from which the application needs to start.
     *
     * @return: returns the page number
     */
    public String getPage() {
        return page;
    }

    /**
     * Method to get a list with the password information.
     *
     * @return: returns the PWClass
     */
    public PWClass getPass() throws SQLException {
        CachedRowSet rowSet = storage.getRowSet();
        while (rowSet.next()){
            pw.update(rowSet.getString(1),
                    rowSet.getString(2), rowSet.getString(3),
                    rowSet.getString(4), rowSet.getString(5),
                    rowSet.getString(6),
                    (Date) rowSet.getObject(7));
        }
        return pw;
    }

}
