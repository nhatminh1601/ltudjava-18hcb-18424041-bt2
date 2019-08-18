/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import org.hibernate.HibernateError;
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

    public static DefaultComboBoxModel showClassComboBoxModel(JComboBox comboBox) {
        List<Classroom> listClassrooms = listClass();
        DefaultComboBoxModel model = (DefaultComboBoxModel) comboBox.getModel();
        for (Classroom itemClassroom : listClassrooms) {
            model.addElement(itemClassroom.getCode());
        }
        return model;
    }

    public static Classroom GetcodeClass(String code) {
        List<Classroom> Listitem = null;
        Classroom item = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hqlString = "FROM Classroom s WHERE s.code = :codesv";
            Query query = session.createQuery(hqlString);
            query.setParameter("codesv", code);
            Listitem = query.list();
        } catch (HibernateError e) {
            System.err.print(e);
        }
        if (Listitem.size() > 0) {
            item = Listitem.get(0);
        }
        return item;
    }
}
