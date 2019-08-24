/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.References;
import util.HibernateUtil;

/**
 *
 * @author nguye
 */
public class RefencesDAO {

    public static DefaultTableModel ShowRefencesTable(JTable table, String msv) {
        List<References> listDatArrayList = null;
        if (msv.isEmpty()) {
            listDatArrayList = listReferences();
        } else {
            listDatArrayList = listReferencesMsv(msv);
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object[] row = new Object[10];
        for (References item : listDatArrayList) {
            row[0] = item.getCodeStudent();
            row[1] = item.getName();
            row[2] = item.getMidtermScores();
            row[3] = item.getFinalScores();
            row[4] = item.getOrderScores();
            row[5] = item.getTotalScores();
            row[6] = item.getNameSubjects();
            row[7] = item.getCodeSubjects();
            row[8] = item.getReason();
            String trangthai = "chưa xem";
            if (item.getStatus() == 1) {
                trangthai = "Đã Cập Nhật Điểm";
            }
            if (item.getStatus() == 2) {
                trangthai = "Không Cập Nhật Điểm";
            }
            row[9] = trangthai;
            model.addRow(row);
        }
        table.setModel(model);
        //model.fireTableDataChanged();

        return model;
    }

    private static List<References> listReferences() {
        List<References> listData = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from References sv";
            Query query = session.createQuery(hql);
            listData = query.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }

        return listData;
    }

    private static List<References> listReferencesMsv(String msv) {
        List<References> listData = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from References sv where codeStudent=:msv";
            Query query = session.createQuery(hql);
            query.setParameter("msv", msv);
            listData = query.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }

        return listData;
    }

    public static References Referencescheck(String msv, String mmh) {
        List<References> listData = null;
        References itemReferences = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from References sv where codeStudent=:msv and codeSubjects = :mmh";
            Query query = session.createQuery(hql);
            query.setParameter("msv", msv);
            query.setParameter("mmh", mmh);
            listData = query.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        if (listData.size() > 0) {
            itemReferences = listData.get(0);
        }
        return itemReferences;
    }

    public static References GetId(int id) {
        References item = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            item = (References) session.get(References.class, id);
        } catch (HibernateException e) {
            System.out.println(e);

        }
        session.close();
        return item;
    }

    public static boolean editReferences(References item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (GetId(item.getId()) == null) {
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
            return false;

        } finally {
            session.close();
        }
        return true;
    }
     public static boolean insertReferences(References item) {
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
}
