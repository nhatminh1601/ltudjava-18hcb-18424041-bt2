/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.StudentDAO.GetIdStudent;
import java.util.Date;
import java.util.List;
import javax.persistence.TemporalType;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.ScheduleReferences;
import pojo.Students;
import util.HibernateUtil;

/**
 *
 * @author nguye
 */
public class ScheduleReferencesDAO {
    public static List<ScheduleReferences> listScheduleReferences() {
        List<ScheduleReferences> listData = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from ScheduleReferences sv";
            Query query = session.createQuery(hql);
            listData = query.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }

        return listData;
    }
     public static DefaultTableModel ShowScheduleReferencesTable(JTable table) {
        List<ScheduleReferences> listDatArrayList = null;
      
            listDatArrayList = listScheduleReferences();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object[] row = new Object[3];
        for (ScheduleReferences item : listDatArrayList) {
            row[0] = item.getId();
            row[1] = item.getStart();
            row[2] = item.getEnd();
           
            model.addRow(row);
        }
        table.setModel(model);
        //model.fireTableDataChanged();

        return model;
    }
     public static boolean insertScheduleReferences(ScheduleReferences item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
            return false;
        } finally {
            session.close();
        }
        return true;
    }
      public static boolean checkngay() {
        Date datenow= new Date();
        List<ScheduleReferences> listData = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from ScheduleReferences sv Where sv.start<= :date and sv.end >= :date";
            Query query = session.createQuery(hql);
            query.setParameter("date",datenow);
            listData = query.list();
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            session.close();
        }
        if(listData.size()>0){
            return true;
        }
        return false;
    }
}
