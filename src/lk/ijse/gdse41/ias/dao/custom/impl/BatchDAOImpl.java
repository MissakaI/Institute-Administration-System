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
import lk.ijse.gdse41.ias.dao.custom.BatchDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.BatchDTO;

/**
 *
 * @author midda
 */
public class BatchDAOImpl implements BatchDAO{

    Connection conn;
    public BatchDAOImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
    }
    
    

    @Override
    public ArrayList<String> getSections() throws Exception {
        String SQL="SELECT DISTINCT b_section FROM batch";
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(SQL);
        ArrayList<String> sectionList=new ArrayList<>();
        while (rst.next()){
            sectionList.add(rst.getString(1));
        }
        return sectionList;
    }

    @Override
    public ArrayList<Integer> getSectionYears(String section) throws Exception {
        String SQL=String.format("SELECT b_year FROM batch WHERE b_section='%s' ORDER BY b_year DESC",section);
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(SQL);
        ArrayList<Integer> sectionYearList=new ArrayList<>();
        while (rst.next()){
            sectionYearList.add(rst.getInt(1));
        }
        return sectionYearList;
    }

    @Override
    public boolean add(BatchDTO dto) throws Exception {
        String SQL="INSERT INTO batch VALUES (?,?,?,?)";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, dto.getBid());
        stm.setObject(2, dto.getbName());
        stm.setObject(3, dto.getYear());
        stm.setObject(4, dto.getSection());
        return stm.executeUpdate()>0;
    }

    @Override
    public BatchDTO search(BatchDTO dto) throws Exception {
        String SQL="SELECT * FROM batch WHERE bid=? || b_section=? && b_year=?";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, dto.getBid());
        stm.setObject(2, dto.getSection());
        stm.setObject(3, dto.getYear());
        ResultSet rst=stm.executeQuery();
        if (rst.next()){
            BatchDTO b=new BatchDTO(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getString(4));
            return b;
        }
        return null;
    }

    @Override
    public ArrayList<BatchDTO> get(BatchDTO dto) throws Exception {
        String SQL=String.format("SELECT * FROM batch WHERE b_section='%s' ORDER BY b_year DESC",dto.getSection());
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(SQL);
        ArrayList<BatchDTO> bdtoList=new ArrayList<>();
        while (rst.next()){
            BatchDTO b=new BatchDTO(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getString(4));
            bdtoList.add(b);
        }
        return bdtoList;
    }
    
}
