package com.densmolov.dao;

import com.densmolov.entity.NamePhone;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class PhoneBookDAOImpl implements PhoneBookDAO {

    private final Logger logger = LoggerFactory.getLogger(PhoneBookDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addNamePhone(NamePhone namePhone) {
        sessionFactory.getCurrentSession().save(namePhone);
    }

    @Override
    public List<NamePhone> listNamePhones() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(NamePhone.class).addOrder(Order.asc("id") );
        @SuppressWarnings("unchecked")
        List<NamePhone> namePhones = (List<NamePhone>) criteria.list();
        if (namePhones!=null && namePhones.size()!=0) {
            System.out.println("Total results: " + namePhones.size());
            for (NamePhone namePhone : namePhones) {
                System.out.println(namePhone.getId() + " - " + namePhone.getName() + " - " + namePhone.getTelephone());
            }
        }
        return namePhones;
    }

}