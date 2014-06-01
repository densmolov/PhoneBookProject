package com.densmolov.service;

import com.densmolov.dao.PhoneBookDAO;
import com.densmolov.entity.NamePhone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class PhoneBookServiceImpl implements PhoneBookService {

    private final Logger logger = LoggerFactory.getLogger(PhoneBookServiceImpl.class);

    @Autowired
    private PhoneBookDAO phoneBookDAO;

    private boolean flag = false;

    @Override
    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Transactional
    public void addNamePhone(NamePhone namePhone) {
        validateIncomingRecord(namePhone);
        if (flag) {
            phoneBookDAO.addNamePhone(namePhone);
        }
    }

    @Override
    public void validateIncomingRecord(NamePhone namePhone) {
        setFlag(true);
        List<NamePhone> namePhones = phoneBookDAO.listNamePhones();
        for (NamePhone n : namePhones) {
            if (namePhone.getName().toLowerCase().equals(n.getName().toLowerCase())) {
                setFlag(false);
                logger.error("Client is trying to push the name that already exists in the database.");
                break;
            }
        }
    }

    @Transactional
    public List<NamePhone> listNamePhones() {
        return phoneBookDAO.listNamePhones();
    }

}