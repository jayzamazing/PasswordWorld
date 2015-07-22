/**
 * Program name: Jay's Password World
 * Program Description: This program uses a gui to store and retrieve passwords in an embedded database.
 * Class description: This class deals with the opening and closing of the connections to the database. This class also deals with the
 * creation of the database and setting the encryption key for the database creation.
 *
 * @author: Adrian Lopez
 * Date: 8/7/2013
 * Version: 1.0
 */
package com.adrianjaylopez.passwordworld.dal.connections;

//import statements

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnections {
    // declare class variables
    private Connection conn;

    public DBConnections(){}

    /**
     * this class creates the connection and returns it to the class that called
     * this method. Used when creating the database and sets encryption.
     *
     * @param dtbUrl
     *            : the url for the database including the create, enabling
     *            encryption, the padding and the key
     * @param encryptionKey
     *            : the encryption key that is entered into this method
     * @return conn
     *              returns the connnection
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    protected Connection dbConnect(String dtbUrl, String encryptionKey) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        // the database url, create statement for database creation, sets the
        // encryption and passes the key
        dtbUrl += "create=true;dataEncryption=true;encryptionAlgorithm=AES/CBC/NoPadding;"
                + "encryptionKey=" + encryptionKey + ";";
        // driver that is used for the connection to the database
        //Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        // initialize conn using the url for the database along with the
        // driver
        conn = DriverManager.getConnection(dtbUrl);
        return conn;

    }

    ;

    /**
     * this class creates the connection and returns it to the class that called
     * this method. Used when the database has been created and a user has been
     * set.
     *
     * @param dtbUrl the
     *            url for the database including the create, enabling
     *            encryption, the padding, the key, the username and password
     * @param userName
     *            : the username used to enter the database
     * @param password
     *            : the password used to enter the database
     * @param encryptionKey
     *            : the encryption key that is entered into this method
     * @return: returns the connection
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    protected Connection dbConnect(String dtbUrl, String userName, String password,
                             String encryptionKey) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {


        // driver that is used for the connection to the database
        //Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        // initialize conn using the url, username, password and encryption
        // key for the database along with the driver
        conn = DriverManager.getConnection(dtbUrl + "user=" + userName
                + ";password=" + password
                + ";encryptionAlgorithm=AES/CBC/NoPadding;encryptionKey="
                + encryptionKey + ";");
        return conn;

    }

    ;

    /**
     * this method closes out the connection.
     *
     * @param dtbUrl
     *            : the database, used to shutdown the database
     * @param userName
     *            : the username entered in the method
     * @param password
     *            : the password entered in the method
     *
     * @throws SQLException
     */
    protected void dbDisconnect(String dtbUrl, String userName, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        // if the connection is not equal to null the perform this block
        if (conn != null) {

            try {
                // closes the connection
                conn.close();


                    // shuts down the database
                    DriverManager.getConnection(dtbUrl + "shutdown=true;user="
                            + userName + ";password=" + password + ";");


            } catch (SQLException e) {
                if ((e.getSQLState().equals("08006"))) {
                    //do nothing
                } else {
                    throw e;
                }
            }

        }

    }


}
