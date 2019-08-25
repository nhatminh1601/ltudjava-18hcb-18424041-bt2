/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.ClassRoomDAO.GetcodeClass;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Classroom;
import pojo.Schedules;
import util.HibernateUtil;

/**
 *
 * @author nguye
 */
public class ScheduleDAO {

    public static List<Schedules> listSchedule() {
        List<Schedules> listData = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from Schedules sv";
            Query query = session.createQuery(hql);
            listData = query.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }

        return listData;
    }

    public static List<Schedules> listSchedulesClass(String lop) {
        List<Schedules> listData = null;
        Classroom itemClassrooms = GetcodeClass(lop);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from Schedules sv where classroom = :classroom";
            Query query = session.createQuery(hql);
            query.setParameter("classroom", itemClassrooms);
            listData = query.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }

        return listData;
    }
    public static Schedules listSchedulescode(String code) {
        List<Schedules> listData = null;
        Schedules item = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from Schedules sv where code = :code";
            Query query = session.createQuery(hql);
            query.setParameter("code", code);
            listData = query.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        if(listData.size()>0){
           item = listData.get(0);
        }
        return item;
    }

    public static DefaultTableModel ShowScheduleTable(JTable table, String lop) {
        List<Schedules> listDatArrayList = null;
        System.out.print(lop);
        if (lop.toString().equals("All")) {
            listDatArrayList = listSchedule();
        } else {
            listDatArrayList = listSchedulesClass(lop);
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for (Schedules item : listDatArrayList) {
            row[0] = item.getCode();
            row[1] = item.getName();

            row[2] = item.getRoom();
            Classroom classroom;
            classroom = item.getClassroom();
            row[3] = classroom.getCode();
            model.addRow(row);
        }
        table.setModel(model);
        //model.fireTableDataChanged();

        return model;
    }
    
     public static DefaultComboBoxModel showScheduleComboBoxModel(JComboBox comboBox) {
        List<Schedules> schedulList = listSchedule();
        DefaultComboBoxModel model = (DefaultComboBoxModel) comboBox.getModel();
         model.removeAllElements();
         model.addElement("All");
        for (Schedules item : schedulList) {
            model.addElement(item.getCode());
        }
        return model;
    }
     
      public static Schedules insertSchedule(Schedules item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (listSchedulescode(item.getCode()) != null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return item;
    }
      

}
