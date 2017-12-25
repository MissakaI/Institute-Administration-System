/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.gdse41.ias.dao.custom.QueryDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.AttendanceDTO;
import lk.ijse.gdse41.ias.dto.ClassScheduleDTO;
import lk.ijse.gdse41.ias.dto.PaymentDTO;
import lk.ijse.gdse41.ias.dto.StudentDTO;
import lk.ijse.gdse41.ias.dto.SubjectDTO;
import lk.ijse.gdse41.ias.dto.TutorDTO;
import lk.ijse.gdse41.ias.dto.TutorSubjectDTO;

/**
 *
 * @author midda
 */
public class QueryDAOImpl implements QueryDAO{

    Connection conn;
    public QueryDAOImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
    }
    
    

    @Override
    public ArrayList<StudentDTO> getStudentsInClass(String cid) throws Exception {
        String SQL="SELECT * FROM student WHERE sid IN ("
                + "SELECT sid FROM registration WHERE cid=?)";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, cid);
        ResultSet rst=stm.executeQuery();
        ArrayList<StudentDTO> studentList=new ArrayList<>();
        while (rst.next()){
            StudentDTO s=new StudentDTO(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getBoolean(6)
            ,rst.getString(7),rst.getString(8),rst.getString(9),rst.getString(10),rst.getString(11),rst.getString(12));
            studentList.add(s);
        }
        return studentList;
    }

    @Override
    public ArrayList<AttendanceDTO> getAttendanceToClass(String cid, String date) throws Exception {
        String SQL="SELECT * FROM attendance WHERE a_date=? && rid IN (SELECT rid FROM registration WHERE cid=?)";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, date);
        stm.setObject(2, cid);
        ArrayList<AttendanceDTO> attendanceList=new ArrayList<>();
        ResultSet rst=stm.executeQuery();
        while (rst.next()){
            AttendanceDTO a=new AttendanceDTO(rst.getString(1), rst.getString(2), rst.getString(3));
            attendanceList.add(a);
        }
        return attendanceList;
    }

    @Override
    public ArrayList<ClassScheduleDTO> getCSandStudentCount(ClassScheduleDTO dto) throws Exception {
        String SQL="SELECT CS.*, COUNT(R.rid), B.b_year FROM class_schedule CS, registration R, batch B WHERE CS.cid=R.cid && CS.bid=B.bid && CS.tsid=? HAVING COUNT(R.rid)>0";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1,dto.getTsid());
        ArrayList<ClassScheduleDTO> csdtoList=new ArrayList<>();
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            if(rst.getString(1)!=null){
                ClassScheduleDTO cs=new ClassScheduleDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),
                        rst.getInt(6), rst.getString(7), rst.getInt(8), rst.getDouble(9), rst.getDouble(10), rst.getInt(11));
                cs.setYear(rst.getInt(12));
                csdtoList.add(cs);
            }
        }
        return csdtoList;
    }

    @Override
    public ArrayList<ClassScheduleDTO> getHallSchedule(ClassScheduleDTO dto) throws Exception {
        String SQL="SELECT CS.*,B.b_year FROM class_schedule CS, batch B WHERE CS.bid=B.bid && (hid_1=? || hid_2=?)";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1,dto.getHid_1());
        stm.setObject(2,dto.getHid_2());
        ArrayList<ClassScheduleDTO> csdtoList=new ArrayList<>();
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            if(rst.getString(1)!=null){
                ClassScheduleDTO cs=new ClassScheduleDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),
                        rst.getInt(6), rst.getString(7), rst.getInt(8), rst.getDouble(9), rst.getDouble(10), 0);
                cs.setYear(rst.getInt(11));
                csdtoList.add(cs);
            }
        }
        return csdtoList;
    }

    @Override
    public PaymentDTO getTutorsMonthlyPayment(String cid, int month) throws Exception {
        String SQL="SELECT COUNT(R.rid), COUNT(P.pid), SUM(if(free_card=TRUE, 1, 0)) FROM registration R LEFT JOIN payment P USING (rid) WHERE R.cid=? && P.p_month=? && YEAR(P.p_date)=YEAR(CURDATE()) GROUP BY cid";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, cid);
        stm.setObject(2, month);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            PaymentDTO dto=new PaymentDTO(rst.getInt(1), rst.getInt(2), rst.getInt(3));
            return dto;
        }
        return null;
    }

    @Override
    public ArrayList<SubjectDTO> getSubjectsOfBatch(String bid) throws Exception {
        String SQL=String.format("SELECT DISTINCT S.* FROM tutor_subject T, subject S, class_schedule C, batch B "
                + "WHERE T.subID=S.subID && B.bid=c.BID && C.tsid=T.tsid && B.bid='%s'",bid);
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(SQL);
        ArrayList<SubjectDTO> subjectList=new ArrayList<>();
        while(rst.next()){
            SubjectDTO subject=new SubjectDTO(rst.getString(1),rst.getString(2),rst.getString(3));
            subjectList.add(subject);
        }
        return subjectList;
    }

    @Override
    public ArrayList<TutorDTO> getTutorsTeachingSubjectForBatch(TutorDTO dto) throws Exception {
        String SQL=String.format("SELECT T.* FROM tutor_subject TS, subject S, class_schedule C, batch B, Tutor T "
                + "WHERE TS.subID=S.subID && B.bid=c.BID && C.tsid=TS.tsid && TS.tid=T.tid && B.bid='%s' && S.subID='%s'",dto.getBid(),dto.getSubId());
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(SQL);
        ArrayList<TutorDTO> tutorList=new ArrayList<>();
        while(rst.next()){
            TutorDTO tutor = new TutorDTO(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),rst.getString(8),rst.getString(9),rst.getString(10));
            tutorList.add(tutor);
        }
        return tutorList;
    }

    @Override
    public ArrayList<TutorSubjectDTO> getSubjectWithTutor(String tid) throws Exception {
        String SQL="SELECT S.*,TS.tsid,TS.tid FROM subject S, tutor_subject TS WHERE S.subId=TS.subId && S.subId IN "
                + "(SELECT subId FROM tutor_subject WHERE tid=?);";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, tid);
        ResultSet rst=stm.executeQuery();
        ArrayList<TutorSubjectDTO> tsdtoList=new ArrayList<>();
        while (rst.next()){
            TutorSubjectDTO sdto=new TutorSubjectDTO(rst.getString(4), rst.getString(5), rst.getString(1));
            sdto.setSubID(rst.getString(1));
            sdto.setSubName(rst.getString(2));
            sdto.setGrade(rst.getString(3));
            tsdtoList.add(sdto);
        }
        return tsdtoList;
    }
    
    
}
