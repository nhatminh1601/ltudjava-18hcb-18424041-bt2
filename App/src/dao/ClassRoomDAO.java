/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.Classroom;
import util.HibernateUtil;

/**
 *
 * @author nguye
 */
public class ClassRoomDAO {
     public static List<Classroom> listClass() {
        List<Classroom> listData = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select c from Classroom c";
            Query query = session.createQuery(hql);
            listData = query.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }

        return listData;
    }
}
