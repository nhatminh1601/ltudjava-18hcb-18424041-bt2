/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.ScheduleDAO.listSchedulescode;
import static dao.StudentDAO.GetCodeStudent;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Schedules;
import pojo.Students;
import pojo.Transcripts;
import util.HibernateUtil;

/**
 *
 * @author nguye
 */
public class TranscriptDAO {

    public static List<Transcripts> listScores() {
        List<Transcripts> listData = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from Transcripts sv";
            Query query = session.createQuery(hql);
            listData = query.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }

        return listData;
    }

    public static List<Transcripts> listScores(String MaMonHoc) {
        List<Transcripts> listData = null;
        Schedules itemSchedules = listSchedulescode(MaMonHoc);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from Transcripts sv where scheduleId = :Schedules";
            Query query = session.createQuery(hql);
            query.setParameter("Schedules", itemSchedules);
            listData = query.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }

        return listData;
    }

    public static DefaultTableModel ShowScoresTable(JTable table, String code, JLabel jthongke) {
        List<Transcripts> listDatArrayList = null;
        System.out.print(code);
        if (code.toString().equals("All")) {
            listDatArrayList = listScores();
        } else {
            listDatArrayList = listScores(code);
        }
        int tonghs = listDatArrayList.size();
        int hsdau = 0;
        int hsrot = 0;
        int hocsinhchuacodiem = 0;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object[] row = new Object[9];
        for (Transcripts item : listDatArrayList) {
            Students hs = item.getStudentId();
            row[0] = hs.getCode();
            row[1] = hs.getName();
            row[2] = item.getMidtermScores();
            row[3] = item.getFinalScores();
            row[4] = item.getOtherScores();
            row[5] = item.getTotalScores();
            Schedules schedules = item.getScheduleId();
            row[6] = schedules.getName();
            row[7] = schedules.getCode();
            String kq = "Chưa có kết quả";
            if (item.getStatus() == 1) {
                if (item.getTotalScores() >= 5) {
                    kq = "đậu";
                    hsdau += 1;
                } else {
                    hsrot += 1;
                    kq = "rớt";
                }
            }
            if (item.getStatus() == 0) {
                hocsinhchuacodiem += 0;
            }
            row[8] = kq;
            model.addRow(row);
        }
        table.setModel(model);
        //model.fireTableDataChanged();
        float hocsinhdau = Math.round((float) (hsdau) / (float) (tonghs) * (float) (100));
        float hocrot = Math.round((float) (hsrot) / (float) (tonghs) * (float) (100));
        jthongke.setText("Tổng số sinh viên: " + tonghs + "--" + " Tổng Học Sinh Đậu: " + hsdau
                + " -- Tổng số học sinh rớt: " + hsrot + "\n Tỷ lệ học sinh rớt: "
                + hocrot + "% -- Tỷ lệ số học sinh đậu: " + hocsinhdau + "% -- Học sinh chưa có điểm: " + hocsinhchuacodiem
        );
        return model;
    }

    public static Transcripts GetIdScores(int studentID, int scheduleID) {
        List<Transcripts> listData = null;
        Transcripts item = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from Transcripts sv where sv.scheduleId = :Schedules and sv.studentId = :student";
            Query query = session.createQuery(hql);
            query.setParameter("Schedules", scheduleID);
            query.setParameter("student", studentID);
            listData = query.list();
        } catch (HibernateException e) {
            System.out.println(e);
        }
        if (listData.size() > 0) {
            item = listData.get(0);
        }
        return item;
    }

    public static boolean editScore(Transcripts item, String studentid, String scheduleCode) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transcripts Data = GetIdScores(GetCodeStudent(studentid).getId(), listSchedulescode(scheduleCode).getId());
        if (Data == null) {
            return false;
        }
        Data.setMidtermScores(item.getMidtermScores());
        Data.setFinalScores(item.getFinalScores());
        Data.setOtherScores(item.getOtherScores());
        Data.setTotalScores(item.getTotalScores());
        Data.setStatus(1);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(Data);
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
