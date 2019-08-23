/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Users;
import util.HibernateUtil;

/**
 *
 * @author nguye
 */
public class UserDAO {
     public static Users compareUser(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Users item = null;
        List<Users> oList = null;
        try {
            String hsq= "from Users s where s.username= :username and s.password= :password";
            Query query= session.createQuery(hsq);
            query.setString("username", username);
            query.setString("password", password);
            oList= query.list();
            if(oList.size()>0){
                item=oList.get(0);
            }
        } catch (HibernateException e) {
            System.err.println(e);
        }
        finally {
            session.close();
        }
        return item;
    }
      public static boolean editUser(Users item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (StudentDAO.GetIdStudent(item.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);

        } finally {
            session.close();
        }
        return true;
    }
    
}
