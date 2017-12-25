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
import lk.ijse.gdse41.ias.dao.custom.StudentDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.StudentDTO;

/**
 *
 * @author midda
 */
public class StudentDAOImpl implements StudentDAO{
    
    Connection conn;
    public StudentDAOImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public boolean add(StudentDTO s) throws Exception {
        String SQL="INSERT INTO student VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, s.getSid());
        stm.setObject(2, s.getfName());
        stm.setObject(3, s.getlName());
        stm.setObject(4, s.getAddress());
        stm.setObject(5, s.getDOB());
        stm.setObject(6, s.getGender());
        stm.setObject(7, s.getNIC());
        stm.setObject(8, s.getStudentTele());
        stm.setObject(9, s.getGuardian());
        stm.setObject(10, s.getGuardianTele());
        stm.setObject(11, s.getBID());
        stm.setObject(12, s.getPhoto());
        int res=stm.executeUpdate();
        return res>0;
    }

    @Override
    public StudentDTO search(StudentDTO dto) throws Exception {
        String SQL=String.format("SELECT * FROM student WHERE sid='%s'",dto.getSid());
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(SQL);
        if (rst.next()){
            StudentDTO s=new StudentDTO(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getBoolean(6)
            ,rst.getString(7),rst.getString(8),rst.getString(9),rst.getString(10),rst.getString(11),rst.getString(12));
            return s;
        }
        return null;
    }


    @Override
    public ArrayList<StudentDTO> getAll() throws Exception {
        String SQL="SELECT * FROM student";
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(SQL);
        ArrayList<StudentDTO> studentList=new ArrayList<>();
        while (rst.next()){
            StudentDTO s=new StudentDTO(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getBoolean(6)
            ,rst.getString(7),rst.getString(8),rst.getString(9),rst.getString(10),rst.getString(11),rst.getString(12));
            studentList.add(s);
        }
        return studentList;
    }
    
}
