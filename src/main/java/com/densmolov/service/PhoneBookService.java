package com.densmolov.service;

import com.densmolov.entity.NamePhone;
import java.util.List;


public interface PhoneBookService {

    public void addNamePhone(NamePhone namePhone);

    public boolean getFlag();

    public void validateIncomingRecord(NamePhone namePhone);

    public List<NamePhone> listNamePhones();

}
