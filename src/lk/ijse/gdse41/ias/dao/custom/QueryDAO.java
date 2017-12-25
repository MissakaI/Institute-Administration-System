/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.dao.SuperDAO;
import lk.ijse.gdse41.ias.dto.AttendanceDTO;
import lk.ijse.gdse41.ias.dto.ClassScheduleDTO;
import lk.ijse.gdse41.ias.dto.PaymentDTO;
import lk.ijse.gdse41.ias.dto.StudentDTO;
import lk.ijse.gdse41.ias.dto.SubjectDTO;
import lk.ijse.gdse41.ias.dto.SuperDTO;
import lk.ijse.gdse41.ias.dto.TutorDTO;
import lk.ijse.gdse41.ias.dto.TutorSubjectDTO;

/**
 *
 * @author midda
 */
public interface QueryDAO extends SuperDAO<SuperDTO>{
    
    public ArrayList<StudentDTO> getStudentsInClass(String cid) throws Exception;
    
    public ArrayList<AttendanceDTO> getAttendanceToClass(String cid, String date) throws Exception;
    
    public ArrayList<ClassScheduleDTO> getCSandStudentCount(ClassScheduleDTO dto) throws Exception;
    
    public ArrayList<ClassScheduleDTO> getHallSchedule(ClassScheduleDTO dto) throws Exception;
    
    public PaymentDTO getTutorsMonthlyPayment(String cid, int month) throws Exception;
    
    public ArrayList<SubjectDTO> getSubjectsOfBatch(String bid) throws Exception;
    
    public ArrayList<TutorDTO> getTutorsTeachingSubjectForBatch(TutorDTO dto) throws Exception;
    
    public ArrayList<TutorSubjectDTO> getSubjectWithTutor(String tid) throws Exception;

    @Override
    public default boolean add(SuperDTO dto) throws Exception {return false;}

    @Override
    public default boolean update(SuperDTO dto) throws Exception {return false;}

    @Override
    public default boolean delete(SuperDTO dto) throws Exception {return false;}

    @Override
    public default SuperDTO search(SuperDTO dto) throws Exception {return null;}

    @Override
    public default ArrayList<SuperDTO> get(SuperDTO dto) throws Exception {return null;}

    @Override
    public default ArrayList<SuperDTO> getAll() throws Exception {return null;}
    
}
