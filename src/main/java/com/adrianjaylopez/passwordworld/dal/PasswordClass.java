package com.adrianjaylopez.passwordworld.dal;

import java.sql.Date;

public class PasswordClass {

    private String _userName, _password, _url, _notes;
    private String _folders, _title;
    private Date _date;

    public PasswordClass(String _folders, String _title, String _userName, String _password, String _url, String _notes, Date _date) {
        this(_folders, _title, _userName, _password, _url, _notes);
        this._date = _date;
    }

    public PasswordClass(String _folders, String _title, String _userName, String _password, String _url, String _notes) {
        this(_folders, _title);
        this._password = _password;
        this._notes = _notes;
        this._url = _url;
        this._userName = _userName;
    }

    public PasswordClass(String _folders, String _title){
        this._folders = _folders;
        this._title = _title;
    }

    public PasswordClass() {
    }

    public void setFolders(String folders) {
        // TODO Auto-generated method stub
        this._folders = folders;
    }

    public String getFolders() {
        // TODO Auto-generated method stub
        return _folders;
    }

    public void setTitle(String title) {
        // TODO Auto-generated method stub
        this._title = title;
    }

    public String getTitle() {
        // TODO Auto-generated method stub
        return _title;
    }

    public void setUserName(String userName) {
        // TODO Auto-generated method stub
        this._userName = userName;
    }

    public String getUserName() {
        // TODO Auto-generated method stub
        return _userName;
    }

    public void setPassword(String password) {
        // TODO Auto-generated method stub
        this._password = password;
    }

    public String getPassword() {
        // TODO Auto-generated method stub
        return _password;
    }

    public void setUrl(String url) {
        // TODO Auto-generated method stub
        this._url = url;
    }

    public String getUrl() {
        // TODO Auto-generated method stub
        return _url;
    }

    public void setNotes(String notes) {
        // TODO Auto-generated method stub
        this._notes = notes;
    }

    public String getNotes() {
        // TODO Auto-generated method stub
        return _notes;
    }

    public void setDate(Date date) {
        // TODO Auto-generated method stub
        this._date = date;
    }

    public Date getDate() {
        // TODO Auto-generated method stub
        return _date;
    }

}

