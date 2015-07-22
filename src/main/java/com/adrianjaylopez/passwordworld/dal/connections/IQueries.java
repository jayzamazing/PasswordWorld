/**
 * Program name: Jay's Password World
 * Program Description: This program uses a gui to store and retrieve passwords in an embedded database.
 * Class description: This interface establishes the contract that must be accepted and used by other
 * programmers. It provides the methods that can be used within a class and is used for updating the
 * database along with the cachedrowset object.
 *
 * @author: Adrian Lopez
 * Date: 8/7/2013
 * Version: 1.0
 */

package com.adrianjaylopez.passwordworld.dal.connections;

import com.sun.rowset.CachedRowSetImpl;

import java.io.Serializable;
import java.sql.SQLException;

//this interface is the contract that needs to be accepted and used
public interface IQueries extends Serializable {
    //all the methods that make up the contract, used for various changes to the database and cached rows
    void dbQueryAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

    void dbInsert(String[] query) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    void dbCreate() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;

    void dbUpdate(String[] oldInfo, String[] query) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

    void dbDelete(String[] query) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

    CachedRowSetImpl getRowSet();

    void setCredentials(String userName, char[] password);
}
