package com.densmolov.dao;

import com.densmolov.entity.NamePhone;
import java.util.List;


public interface PhoneBookDAO {

    public void addNamePhone(NamePhone namePhone);

    public List<NamePhone> listNamePhones();

}
