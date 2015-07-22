/**
 * Program name: Jay's Password World
 * Program Description: This program uses a gui to store and retrieve passwords in an embedded database.
 * Class description: This class deals with the interaction with the datastorage class and the jpframe. So
 * more specifically, this class pulls in the data from the cachedrowset from the datalayer, and sets its
 * contents to a list collection of the password class that will store the password data for usage. This class
 * updates, deletes, and inserts the data into the list and then propagates the changes back to the data layer.
 * It also takes the data and provides it to the presentation layer for view.
 *
 * @author: Adrian Lopez
 * Date: 8/7/2013
 * Version: 1.0
 */

package com.adrianjaylopez.passwordworld.bl;

//import statements

import com.adrianjaylopez.passwordworld.dal.DataStorage;
import com.adrianjaylopez.passwordworld.dal.PWClass;
import com.adrianjaylopez.passwordworld.dal.PasswordClass;

import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class BussData {

    // declare private variables
    private CachedRowSet psCache;
    private PWClass pw = new PWClass();
    PasswordClass info;
    private AppSettings ui = new AppSettings();
    private DataStorage dataLayer;
    private String[] oldInfo, updateInfo;
    private Stack<String> folderStack;
    private DefaultMutableTreeNode parent;
    private char[] password;
    private String userName;

    /**
     * Constructor used after the application knows what page to start on
     *
     * @param userName
     *            : username to login
     * @param password
     *            : password to login
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public BussData(String userName, char[] password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
        // call to the empty bussdata construct
        this();

        this.userName = userName;
        this.password = password;
        // used to decide what part of the thread needs to run based on what
        // the page variable is set to
        Login();

    }

    /**
     * constructor that is used when the program starts to determine the
     * applications start page
     *
     * @throws IOException
     *
     */
    public BussData() throws IOException {
        // used to decide what part of the thread needs to run

        GetPage();

    }

    /**
     * This method gets the page and sets it to the page variable.
     *
     * @throws IOException
     */
    public void GetPage() throws IOException {
        class page {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                @Override
                protected Void doInBackground() throws Exception {

                    // initializes datastorage instance
                    dataLayer = new DataStorage();

                    // calls the getpage method and passes its results to the
                    // page variable
                    ui.setCard(dataLayer.getPage());
                    return null;
                }

                @Override
                protected void done() {
                    // sets the instance to null
                    dataLayer = null;
                }

            };
        }
        page startPage = new page();
        startPage.worker.execute();
        while (!startPage.worker.isDone()) {
            // do nothing
        }

    }

    /**
     * This method deals with loging into the application and once logged in, it
     * grabs the cachedrowset data and puts it inside of a passwordclass
     * collection.
     *
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void Login() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException,
            IOException {
        class login {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {

                    // calls the datastorage constructor and passes the
                    // username, password, and page number to it
                    dataLayer = new DataStorage(userName, password,
                            ui.getCard());
                    if(ui.getCard().compareTo("00") != 0 ) {
                        // initializes variable
                        pw = dataLayer.getPass();
                    }
                    // calls the getpage method and passes its results to the
                    // page variable
                    ui.setCard(dataLayer.getPage());
                    return null;
                }

                @Override
                protected void done() {
                    // set instances to null
                    psCache = null;
                }
            };

        }
        login userLogin = new login();
        userLogin.worker.execute();
        while (!userLogin.worker.isDone()) {
            // Do nothing
        }

    }

    /**
     * This method takes the information from the presentation layer to insert
     * into the passwordclass collection and then passes it to the datalayer.
     *
     * @param folder
     *            = updateInfo[0]
     * @param title
     *            = updateInfo[1]
     * @param userName
     *            = updateInfo[2]
     * @param password
     *            = updateInfo[3]
     * @param url
     *            = updateInfo[4]
     * @param notes
     *            = updateInfo[5]
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void update(final String folder, final String title,
                       final String userName, final String password, final String url,
                       final String notes) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {

        // initializes and stores the data
        info = new PasswordClass(folder, title, userName, password, url, notes);
        dataLayer = new DataStorage(this.userName, this.password,
                ui.getCard());
        class update1 {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {

                    // initialize variable
                    pw.update(info);
                    // call the dbinsert method and pass the instance
                    dataLayer.DBInsert(info);

                    return null;
                }

                @Override
                protected void done() {
                    info = null;
                }

            };
        }
        update1 update = new update1();
        update.worker.execute();
        while (!update.worker.isDone()) {
            // do nothing
        }

    }

    /**
     * This method is used for updating the information of an existing entry in
     * the passwordclass collection and then passes the old for the foldername
     * and title if it has changed to the datalayer.
     *
     * @param oldFolder
     *            = updateInfo[0]
     * @param oldTitle
     *            = updateInfo[1]
     * @param folder
     *            = updateInfo[2]
     * @param title
     *            = updateInfo[3]
     * @param userName
     *            = updateInfo[4]
     * @param password
     *            = updateInfo[5]
     * @param url
     *            = updateInfo[6]
     * @param notes
     *            = updateInfo[7]
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void update(final String oldFolder, final String oldTitle,
                       final String folder, final String title, final String userName,
                       final String password, final String url, final String notes) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
        // initializes and stores the data
        info = new PasswordClass(folder, title, userName, password, url, notes);
        // store the old foldername and title in the array
        oldInfo = new String[]{oldFolder, oldTitle};
        dataLayer = new DataStorage(this.userName, this.password,
                ui.getCard());
        class update2 {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    // TODO Auto-generated method stub
                    pw.update(oldInfo, info);


                    // call the dbupdate method and pass the oldinfo and pw
                    // variables
                    dataLayer.DBUpdate(oldInfo, info);

                    return null;
                }

                @Override
                protected void done() {
                    updateInfo = null;
                    oldInfo = null;
                }
            };

        }
        update2 update2 = new update2();
        update2.worker.execute();
        while (!update2.worker.isDone()) {
            // do nothing
        }
    }

    /**
     * This method looks for the object and then uses the index to remove it
     * from the passwordclass collection.
     *
     * @param folder
     *            = updateInfo[0]
     * @param title
     *            = updateInfo[1]
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void delete(String folder, String title) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {

        info = new PasswordClass(folder, title);
        dataLayer = new DataStorage(this.userName, this.password,
                ui.getCard());

        class delete {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    // TODO Auto-generated method stub

                    // initializes and stores the data in the updateinfo array

                    pw.delete(info);

                    // pass the single instance to the dbdelete method
                    dataLayer.DBDelete(info);

                    return null;
                }

                @Override
                protected void done() {
                    updateInfo = null;
                }

            };
        }
        delete g = new delete();
        g.worker.execute();
        while (!g.worker.isDone()) {

        }

    }

    /**
     * This method sets the tree structure for the jtree
     */

    // TODO CONTINUE FROM HERE
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void tree() {
        // initialize variables
        folderStack = new Stack();
        // declare local variables
        DefaultMutableTreeNode folder;
        DefaultMutableTreeNode title;
        Set<Map.Entry<String, ArrayList>> set;
        // declare and initialize variables
        parent = new DefaultMutableTreeNode("Password Folders");
        TreeMap<String, ArrayList> tree = new TreeMap<>();
        class settTree1 {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {

                    return null;
                }

                @Override
                protected void done() {

                }

            };
        }
        settTree1 setTree = new settTree1();
        setTree.worker.execute();
        while (!setTree.worker.isDone()) {
            // do nothing
        }

        // iterates through passwordclass collection
        for (String[] pwc : pw.getFolderNTitle()) {
            // if the tree has this iterations folder then do this block
            if (tree.containsKey(pwc[0])) {
                // get the instance of the folder object
                ArrayList<String> jList = tree.get(pwc[0]);
                // add the title to the instance
                jList.add(pwc[1]);
                // otherwise do this block
            } else {
                // initialize and declare arraylist
                ArrayList<String> jList = new ArrayList<String>();
                // add title to the list
                jList.add(pwc[1]);
                // add the folder to the tree with the title under it
                tree.put(pwc[0], jList);
            }
        }
        // sets the trees map to the set variable
        set = tree.entrySet();
        // iterates through the map
        for (Map.Entry<String, ArrayList> sMap : set) {
            // sets folder to the current instances folder property
            folder = new DefaultMutableTreeNode(sMap.getKey());
            // adds the foldername to the folderstack for use in the combobox
            folderStack.add(sMap.getKey());
            // iterates through the map
            for (int i = 0; i < sMap.getValue().toArray().length; i++) {
                // sets the title to the instances pw title properties
                title = new DefaultMutableTreeNode(sMap.getValue().get(i));
                // add the title to the folder
                folder.add(title);
                // add the folder to the parent
                parent.add(folder);
            }
        }
    }

    /**
     * This method returns the entire contents of a row using the folder name
     * and title.
     *
     * @param folder
     *            : folder to search
     * @param title
     *            : title to search
     * @return
     */
    public String[] search(final String folder, final String title) {
        info = new PasswordClass(folder, title);
        class search1 {
            SwingWorker<String[], Void> worker = new SwingWorker<String[], Void>() {
                @Override
                protected String[] doInBackground() throws Exception {
                    // return the password class instance
                    return pw.search(info);
                }

                @Override
                protected void done() {

                }

            };
        }
        search1 searching = new search1();
        searching.worker.execute();
        while (!searching.worker.isDone()) {
            // do nothing
        }

        try {
            return searching.worker.get();
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method returns the page number to the presentation layer to
     * determine the start page.
     *
     * @return: returns the page number
     */
    public String getPage() {
        return ui.getCard();
    }

    /**
     * This method returns the tree object.
     *
     * @return: returns defaultmutabletreenode
     */
    public DefaultMutableTreeNode getTree() {
        return parent;
    }

    /**
     * This method returns a stack full of the foldernames for the combo box in
     * the presentation layer.
     *
     * @return: returns folderstack
     */
    public Stack<String> getFolders() {
        return folderStack;
    }

}