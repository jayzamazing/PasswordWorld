/**
 * Program name: Jay's Password World
 * Program Description: This program uses a gui to store and retrieve passwords in an embedded database.
 * Class description: This class implements the contract from idatastorage and overrides those methods
 * to interact with both the database and the cachedrowset.
 *
 * @author: Adrian Lopez
 * Date: 8/7/2013
 * Version: 1.0
 */

package com.adrianjaylopez.passwordworld.dal.connections;

//import statements

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;
import javax.sql.rowset.spi.SyncResolver;
import java.math.BigInteger;
import java.sql.*;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class LocalQueries implements IQueries {
    /**
     *
     */

    // declare private variables
    private ResultSet results;
    private Statement statement;
    private String userName, encryptKey;
    private char[] password;
    private Date date;
    private Calendar calendar;
    private CachedRowSet psCache;
    private ResourceBundle resourceBundle;
    String dtbUrl;
    private Connection conn;

    public LocalQueries() {
        resourceBundle = ResourceBundle.getBundle("values/Strings");
        dtbUrl = resourceBundle.getString("dtbUrl");

    }

    /**
     * this method creates the database, the usernames and passwords to access
     * database, and the tables within the database
     *
     *
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */

    @Override
    public void dbCreate() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        try {
            DBConnections db = new DBConnections();
            conn = db.dbConnect(dtbUrl, encryptKey);
            /**
             * uses the connection as a point where the statements will be executed
             * and sets it to the statement variable
             */
            try {
                statement = conn.createStatement();


                // makes the database require authentication, and the provider for
                // the authentication be the database
                statement
                        .executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.connection.requireAuthentication', 'true')");
                statement
                        .executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.propertiesOnly', 'true')");
                statement
                        .executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.authentication.provider', 'BUILTIN')");
                // this creates the tables and the columns
                statement
                        .executeUpdate("CREATE TABLE jpwdb.passwordfolders(number INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1,INCREMENT BY 1),"
                                + "foldername VARCHAR(30) NOT NULL,title VARCHAR(30),"
                                + "username VARCHAR(30),password VARCHAR(30),url VARCHAR(100),notes VARCHAR(300),"
                                + "datetimeupdated DATE)");
                statement
                        .executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.user."
                                + this.userName
                                + "', '"
                                + String.valueOf(this.password) + "')");

            } finally {
                // close the statement
                statement.close();
                conn = null;
                // closes open connections for the database
                db.dbDisconnect(dtbUrl, this.userName, String.valueOf(this.password));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     * This class deals with getting the contents of the PASSWORDFOLDERS table
     * and placing it in the pscache variable
     *
     *
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     */
    @Override
    public void dbQueryAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        DBConnections db = new DBConnections();
        conn = db.dbConnect(dtbUrl, userName, String.valueOf(password),
                this.encryptKey);
        try {

            /*
			 * uses the connection as a point where the statements will be
			 * executed and sets it to the statementvariable
			 */
            statement = conn.createStatement();
			/*
			 * from the statement, query all the contents of the password folder
			 * and set them to the results variable
			 */
            results = statement
                    .executeQuery("Select foldername, title, username, password, url, notes, datetimeupdated from jpwdb.passwordfolders");
            // initialize the cachedrowset object
            psCache = new CachedRowSetImpl();
            // take the results of the query and set its contents to pscache
            psCache.populate(results);
            // close the statement
            statement.close();
            results.close();
            // sets psCache to the beginning of the rowset
            psCache.beforeFirst();
        } finally {
            conn = null;
            // closes open connections for the database
            db.dbDisconnect(dtbUrl, this.userName, String.valueOf(this.password));
        }
    }

    /**
     * this method inserts data into the cachedrowset and the database
     *
     * @param query
     *            : what is going to be inserted
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public void dbInsert(String[] query) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        DBConnections db = new DBConnections();
        conn = db.dbConnect(dtbUrl, userName, String.valueOf(password),
                this.encryptKey);
        try {

            // sets the cursor to the front of the cachedrowset
            psCache.beforeFirst();
            // initialize the calendar object
            calendar = Calendar.getInstance();
            // initialize date object
            date = new Date(calendar.getTimeInMillis());
            // move the cursor to a row where data can be inserted in the
            // cachedrowset
            psCache.moveToInsertRow();
            // insert the data into the cachedrowset
            psCache.updateString("foldername", query[0]);
            psCache.updateString("title", query[1]);
            psCache.updateString("username", query[2]);
            psCache.updateString("password", query[3]);
            psCache.updateString("url", query[4]);
            psCache.updateString("notes", query[5]);
            psCache.updateDate("datetimeupdated", date);
            // data is inserted into the cachedrowset
            psCache.insertRow();
            // move the cursor back
            psCache.moveToCurrentRow();
            // set the table to update
            psCache.setTableName("passwordfolders");
            // accept the changes and update the database with this data
            psCache.acceptChanges(conn);
            // sets psCache to the beginning of the rowset
            psCache.beforeFirst();
        } finally {
            conn = null;
            // closes open connections for the database
            db.dbDisconnect(dtbUrl, this.userName, String.valueOf(this.password));
        }

    }

    /**
     * @param oldInfo
     *           : data to be updated
     * @param query
     *            = information that the row is being updated to
     *
     * @throws SQLException
     */

    @Override
    public void dbUpdate(String[] oldInfo, String[] query) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        DBConnections db = new DBConnections();
        conn = db.dbConnect(dtbUrl, userName, String.valueOf(password),
                this.encryptKey);
        try {

            // sets the cursor to the front of the cachedrowset
            psCache.beforeFirst();
            // initialize calendar and date objects
            calendar = Calendar.getInstance();
            date = new Date(calendar.getTimeInMillis());
            // while the cachedrowset still has more data to iterate through
            try {
                while (psCache.next()) {
                    // if the old folder and title does not equal the new ones
                    // then update all fields
                    if (psCache.getString("foldername").compareTo(oldInfo[0]) == 0
                            && psCache.getString("title").compareTo(oldInfo[1]) == 0) {
                        psCache.updateString("foldername", query[0]);
                        psCache.updateString("title", query[1]);
                        psCache.updateString("username", query[2]);
                        psCache.updateString("password", query[3]);
                        psCache.updateString("url", query[4]);
                        psCache.updateString("notes", query[5]);
                        psCache.updateDate("datetimeupdated", date);
                        // data is inserted into the cachedrowset
                        psCache.updateRow();
                        // set the table to update
                        psCache.setTableName("passwordfolders");
                        // accept the changes and update the database with this
                        // data
                        psCache.acceptChanges(conn);
                        // break out of the loop
                        break;
                        // otherwise perform this block
                    } else if (psCache.getString("foldername").compareTo(
                            query[0]) == 0
                            && psCache.getString("title").compareTo(query[1]) == 0) {
                        // update everything except for the foldername and title
                        // fields
                        psCache.updateString("username", query[2]);
                        psCache.updateString("password", query[3]);
                        psCache.updateString("url", query[4]);
                        psCache.updateString("notes", query[5]);
                        psCache.updateDate("datetimeupdated", date);
                        // data is inserted into the cachedrowset
                        psCache.updateRow();
                        // set the table to update
                        psCache.setTableName("passwordfolders");
                        // accept the changes and update the database with this
                        // data
                        psCache.acceptChanges(conn);
                        // break out of the loop
                        break;

                    }

                }
                // used to resolve conflicts with updating the data, chooses the
                // information that is passed in and discards the old
                // information
            } catch (SyncProviderException spe) {
                // declare local variables
                Object psCacheValue;
                Object resolvedValue;
                // initialize and declare syncresolver
                SyncResolver resolver = spe.getSyncResolver();
                // continue while there is another conflict in the resolver
                // instance
                while (resolver.nextConflict()) {
                    // if the row has a conflict
                    if (resolver.getStatus() == SyncResolver.UPDATE_ROW_CONFLICT) {
                        // get the row
                        psCache.absolute(resolver.getRow());
                        // iterate through the row
                        for (int i = 1; i < psCache.getMetaData()
                                .getColumnCount(); i++) {
                            // if a column has a conflict
                            if (resolver.getConflictValue(i) != null) {
                                // get the column
                                psCacheValue = psCache.getObject(i);
                                // set the value of the cachedrowset and set it
                                // as the resolvedvalue
                                resolvedValue = psCacheValue;
                                // update the data with what is in the resolved
                                // value
                                resolver.setResolvedValue(i, resolvedValue);
                            }

                        }
                    }
                }

            }
            // sets pschache to the beginning
            psCache.beforeFirst();
        }  finally {
            conn = null;
            // closes open connections for the database
            db.dbDisconnect(dtbUrl, this.userName, String.valueOf(this.password));
        }
    }

    /**
     *
     * this method deletes the data for a specific title within a folder
     *
     * @param query
     *            : the item that is being deleted
     *
     * @throws SQLException
     */
    @Override
    public void dbDelete(String[] query) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        DBConnections db = new DBConnections();
        conn = db.dbConnect(dtbUrl, userName, String.valueOf(password),
                this.encryptKey);
        try {


            // sets the cursor to the front of the cachedrowset
            psCache.beforeFirst();
            // while the cachedrowset still has more data to iterate through
            while (psCache.next()) {
                // if the foldername and the title match the query then execute
                // this section
                if (psCache.getString("foldername").compareTo(query[0]) == 0
                        && psCache.getString("title").compareTo(query[1]) == 0) {
                    // delete the row inside of the cachedrowset
                    psCache.deleteRow();
                    // accept the changes and update the database with this data
                    psCache.acceptChanges(conn);
                    // break out of the loop
                    break;
                }
            }
            // sets the cursor to the front of the cachedrowset
            psCache.beforeFirst();
        } finally {
            conn = null;
            // closes open connections for the database
            db.dbDisconnect(dtbUrl, this.userName, String.valueOf(this.password));
        }
    }

    /**
     * used to get the rowset object
     */
    @Override
    public CachedRowSetImpl getRowSet() {
        return (CachedRowSetImpl) psCache;
    }

    @Override
    public void setCredentials(String userName, char[] password) {
        this.userName = userName;
        this.password = password;
        this.encryptKey = createEncryptionKey(password);

    }

    /**
     * Method to creates the encryption key using the password as salt. Temp
     * method until I work on the full database encryption.
     *
     * @param pass
     *            : character representing password
     *
     * @return encryKey aka string
     *
     */
    private String createEncryptionKey(char[] pass) {
        String encryKey = MessageFormat.format(resourceBundle.getString("encryKey"), String.format("%016x", new BigInteger(String.valueOf(pass)
                .getBytes())));
        String blah = encryKey.substring(0, 32);
        return encryKey.substring(0, 32);
    }

}
