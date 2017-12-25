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
import lk.ijse.gdse41.ias.dao.custom.HallDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.HallDTO;

/**
 *
 * @author midda
 */
public class HallDAOImpl implements HallDAO{
    
    Connection conn;
    public HallDAOImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public boolean add(HallDTO dto) throws Exception {
        String SQL="INSERT INTO hall VALUES (?,?,?)";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, dto.getHid());
        stm.setObject(2, dto.getSeatingCap());
        stm.setObject(3, dto.isAc());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean update(HallDTO dto) throws Exception {
        String SQL="UPDATE hall SET seating_capacity=?, ac=? WHERE hid=?";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(3, dto.getHid());
        stm.setObject(1, dto.getSeatingCap());
        stm.setObject(2, dto.isAc());
        return stm.executeUpdate()>0;
    }

    @Override
    public ArrayList<HallDTO> get(HallDTO dto) throws Exception {
        String SQL="SELECT * FROM hall WHERE seating_capacity>=? ORDER BY seating_capacity ASC";
        if (dto.isAc()){
            SQL="SELECT * FROM hall WHERE seating_capacity>=? && ac=1 ORDER BY seating_capacity ASC";
        }
        ArrayList<HallDTO> dtoList=new ArrayList<>();
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, dto.getSeatingCap());
        ResultSet rst=stm.executeQuery();
        while (rst.next()){
            HallDTO hdto=new HallDTO(rst.getString(1), rst.getInt(2), rst.getBoolean(3));
            dtoList.add(hdto);
        }
        return dtoList;
    }

    @Override
    public ArrayList<HallDTO> getAll() throws Exception {
        String SQL="SELECT * FROM hall ORDER BY seating_capacity DESC";
        Statement stm=conn.createStatement();
        ArrayList<HallDTO> dtoList=new ArrayList<>();
        ResultSet rst=stm.executeQuery(SQL);
        while (rst.next()){
            HallDTO dto=new HallDTO(rst.getString(1), rst.getInt(2), rst.getBoolean(3));
            dtoList.add(dto);
        }
        return dtoList;
    }
   
}
