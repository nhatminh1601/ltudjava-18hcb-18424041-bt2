/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.ClassRoomDAO.GetcodeClass;
import static dao.ClassRoomDAO.insertClassItem;
import static dao.ScheduleDAO.insertSchedule;
import static dao.ScheduleDAO.listSchedulescode;
import static dao.StudentDAO.GetCodeStudent;
import static dao.StudentDAO.GetListCodeStudent;
import static dao.StudentDAO.insertStudent;
import static dao.TranscriptDAO.editScoretow;
import static dao.TranscriptDAO.insertScores;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import pojo.Classroom;
import pojo.Schedules;
import pojo.Students;
import pojo.Transcripts;

/**
 *
 * @author nguye
 */
public class HandleFile {

    public static Boolean ReadWriteFileImStudent(String path) {
        File file = new File(path);
        BufferedReader reader = null;
        Object temp, lop;
        Classroom listClass = new Classroom();
        List<Students> listStudent = new ArrayList<>();

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String[] malop = ((String) reader.readLine()).replace("\uFEFF", "").split(",");
            String classNameS = malop[0];
            if (classNameS != null) {
                listClass.setCode(classNameS);
                Classroom tempClassroom = GetcodeClass(classNameS);
                if (tempClassroom != null) {
                    listClass = tempClassroom;
                } else {
                    listClass = insertClassItem(listClass);
                }
            } else {
                return false;
            }
            reader.readLine();
            System.out.println("pass");
            while ((temp = reader.readLine()) != null) {
                System.out.println(temp);
                String[] Data = ((String) temp).split(",");
                Byte sexByte = 0;
                if (Data[3].equals("Nữ")) {
                    sexByte = 1;
                }
                Students item = new Students(listClass, Data[1], Data[2], sexByte, Data[4], Data[1]);
                listStudent.add(item);
            }
            for (Students itemStudents : listStudent) {
                insertStudent(itemStudents);
            }
            reader.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Boolean ReadWriteFileImSchedule(String path) {
        File file = new File(path);
        Classroom listClass = new Classroom();
        ArrayList<Schedules> listImSchedule = new ArrayList<>();
        ArrayList<Transcripts> listImScores = new ArrayList<>();
        List<Students> listStudent = new ArrayList<>();
        BufferedReader reader = null;
        Object temp;
        String mamh = "";

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String[] malop = ((String) reader.readLine()).replace("\uFEFF", "").split(",");
            String classNameS = malop[0];
            if (classNameS != null) {
                listClass.setCode(classNameS);
                Classroom tempClassroom = GetcodeClass(classNameS);
                if (tempClassroom != null) {
                    listClass = tempClassroom;
                } else {
                    listClass = insertClassItem(listClass);
                }
            } else {
                return false;
            }
            listStudent = GetListCodeStudent(listClass);
            reader.readLine();
            System.out.println("passlop");
            while ((temp = reader.readLine()) != null) {
                System.out.println(temp);
                String[] Data = ((String) temp).split(",");
                Schedules itemSchedules = new Schedules(listClass, Data[1], Data[2], Data[3]);
                listImSchedule.add(itemSchedules);
            }
            for (Schedules item : listImSchedule) {
                Schedules check = insertSchedule(item);
                if (check != null) {
                    for (Students itmeStudents : listStudent) {
                        Byte status = 0;
                        Transcripts itemTr = new Transcripts(itmeStudents, check, 0, 0, 0, 0, status);
                        insertScores(itemTr);
                    }

                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public static Boolean ReadWriteFileImScores(String path) {
        File file = new File(path);
        ArrayList<Transcripts> listImScores = new ArrayList<>();
        Classroom lop = null;
        Schedules lichSchedules = null;
        BufferedReader reader = null;
        Object temp, temp2;
        String maMonHoc = "";

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String[] malop = ((String) reader.readLine()).replace("\uFEFF", "").split(",");
            String[] list = malop[0].split("-");
            String classNameS = list[0];
            maMonHoc = list[1];
            lichSchedules = listSchedulescode(maMonHoc);
            if (lichSchedules == null) {
                return false;
            }
            reader.readLine();
            System.out.println("passlop");
            while ((temp = reader.readLine()) != null) {
                System.out.println(temp);
                String[] Data = ((String) temp).split(",");
                Students svStudents = GetCodeStudent(Data[1]);
                if (svStudents != null) {
                    Byte status = 1;
                    Transcripts temTranscripts = new Transcripts(svStudents,
                            lichSchedules, Float.parseFloat(Data[3]), Float.parseFloat(Data[4]),
                            Float.parseFloat(Data[5]), Float.parseFloat(Data[6]), status);
                    listImScores.add(temTranscripts);
                }
            }
            for (Transcripts item : listImScores) {
                editScoretow(item);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

}
