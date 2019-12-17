package com.example.dao;

import android.database.Cursor;

import com.example.model.KeepInformation;
import com.example.model.User;
import com.example.myappandroid.Database;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserDaoImpl {
    Database database;

    public UserDao(Database database) {
        this.database = database;
    }

    @Override
    public List getAllUser() {
        Cursor cursor = database.getData("SELECT * FROM User");
        List list = new ArrayList();
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setFullName(cursor.getString(1));
            user.setRole(cursor.getString(2));
            user.setUserName(cursor.getString(3));
            user.setPassword(cursor.getString(4));
            user.setIdBooking_User(cursor.getInt(5));
            list.add(user);
        }
        return list;
    }

    @Override
    public boolean checkUserByUserNameAndPassword(String userName, String password) {
        Cursor cursor = database.getData("SELECT * FROM User WHERE userName = '" + userName +"' AND password = '" + password + "'");
        User user = new User();
        while (cursor.moveToNext()) {
            user.setId(cursor.getInt(0));
            user.setFullName(cursor.getString(1));
            user.setRole(cursor.getString(2));
            user.setUserName(cursor.getString(3));
            user.setPassword(cursor.getString(4));
        }
        if (user.getId() != 0) {
            KeepInformation.setIdUser(user.getId());
            KeepInformation.setRole(user.getRole());
            return true;
        } else {
            return false;
        }
    }
}
