/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.SinhVien;
import util.HibernateUtil;

/**
 *
 * @author nguye
 */
public class SinhVienDAO {

    public static List<SinhVien> listStudent() {
        List<SinhVien> listData = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from SinhVien ";
            Query query = session.createQuery(hql);
            listData = query.list();
        } catch (Exception e) {
            System.err.println(e);
        }
        return listData;
    }
}
