/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.ClassRoomDAO.GetcodeClass;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Classroom;
import pojo.Students;
import util.HibernateUtil;

/**
 *
 * @author nguye
 */
public class StudentDAO {

    public static List<Students> listStudent() {
        List<Students> listData = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from Students sv";
            Query query = session.createQuery(hql);
            listData = query.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }

        return listData;
    }

    public static List<Students> listStudentClass(String lop) {
        List<Students> listData = null;
        Classroom itemClassrooms = GetcodeClass(lop);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from Students sv where classroom = :classroom";
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

    public static DefaultTableModel ShowStudentTable(JTable table, String lop) {
        List<Students> listDatArrayList = null;
        System.out.print(lop);
        if (lop.toString().equals("All")) {
            listDatArrayList = listStudent();
        } else {
            listDatArrayList = listStudentClass(lop);
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for (Students item : listDatArrayList) {
            row[0] = item.getCode();
            row[1] = item.getName();
            String sex = item.getSex() == 0 ? "nam" : "nữ";
            row[2] = sex;
            Classroom classroom;
            classroom = item.getClassroom();
            row[3] = classroom.getCode();
            row[4] = item.getIndentityCard();
            model.addRow(row);
        }
        table.setModel(model);
        //model.fireTableDataChanged();

        return model;
    }

    public static Students GetCodeStudent(String codeSv) {
        Students item = null;
        List<Students> listItemList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hqlString = "FROM Students s WHERE s.code = :codesv";
            Query query = session.createQuery(hqlString);
            query.setParameter("codesv", codeSv);
            listItemList = query.list();
        } catch (HibernateException e) {
            System.out.println(e);
        } finally {
            if (listItemList.size() > 0) {
                item = listItemList.get(0);
            }
            return item;
        }
    }

    public static Students GetIdStudent(int id) {
        Students item = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            item = (Students) session.get(Students.class, id);
        } catch (HibernateException e) {
            System.out.println(e);
        }
        return item;
    }

    public static boolean insertStudent(Students item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (StudentDAO.GetCodeStudent(item.getCode()) != null) {
            return false;
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
        return true;
    }

    public static boolean editStudent(Students itemStudent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (StudentDAO.GetIdStudent(itemStudent.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(itemStudent);
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

    public static boolean deleteStudent(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Students item = GetIdStudent(id);
        if (item == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(item);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.print(e);
        } finally {
            session.close();
        }
        return true;

    }

    public static Students compareStudent(String code, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Students item = null;
        List<Students> oList = null;
        try {
            String hsq = "from Students s where s.code= :code and s.password= :password";
            Query query = session.createQuery(hsq);
            query.setString("code", code);
            query.setString("password", password);
            oList = query.list();
            if (oList.size() > 0) {
                item = oList.get(0);
            }
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return item;
    }

}
