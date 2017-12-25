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
import lk.ijse.gdse41.ias.dao.custom.TutorDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.TutorDTO;

/**
 *
 * @author midda
 */
public class TutorDAOImpl implements TutorDAO{
    
    Connection conn;
    public TutorDAOImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
    }


    @Override
    public boolean add(TutorDTO dto) throws Exception {
        String SQL="INSERT INTO tutor VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, dto.getTid());
        stm.setObject(2, dto.getTitleName());
        stm.setObject(3, dto.getfName());
        stm.setObject(4, dto.getlName());
        stm.setObject(5, dto.getAddress());
        stm.setObject(6, dto.getNIC());
        stm.setObject(7, dto.getTelPrimary());
        stm.setObject(8, dto.getTelSecondary());
        stm.setObject(9, dto.getUsername());
        stm.setObject(10, dto.getPassword());
        return stm.executeUpdate()>0;
    }

    @Override
    public ArrayList<TutorDTO> getAll() throws Exception {
        String SQL="SELECT * FROM tutor";
        Statement stm=conn.createStatement();
        ArrayList<TutorDTO> tutorList=new ArrayList<>();
        ResultSet rst=stm.executeQuery(SQL);
        while (rst.next()){
            TutorDTO tdto=new TutorDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), 
                    rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10)
            );
            tutorList.add(tdto);
        }
        return tutorList;
    }
    
}
