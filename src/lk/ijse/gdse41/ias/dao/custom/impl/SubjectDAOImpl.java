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
import lk.ijse.gdse41.ias.dao.custom.SubjectDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.SubjectDTO;

/**
 *
 * @author midda
 */
public class SubjectDAOImpl implements SubjectDAO{
    
    Connection conn;
    public SubjectDAOImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public boolean add(SubjectDTO dto) throws Exception {
        String SQL="INSERT INTO subject VALUES (?,?,?)";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1,dto.getSubID());
        stm.setObject(2,dto.getSubName());
        stm.setObject(3,dto.getGrade());
        return stm.executeUpdate()>0;
    }

    @Override
    public ArrayList<SubjectDTO> getAll() throws Exception {
        String SQL="SELECT * FROM subject ORDER BY s_section";
        Statement stm=conn.createStatement();
        ArrayList<SubjectDTO> subjectList=new ArrayList<>();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()){
            SubjectDTO subject=new SubjectDTO(rst.getString(1), rst.getString(2), rst.getString(3));
            subjectList.add(subject);
        }
        return subjectList;  
    }
    
}
