package com.example.dao;

import java.util.List;

public interface UserDaoImpl {
    boolean checkUserByUserNameAndPassword(String userName, String password);
    List getAllUser();
}
