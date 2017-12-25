/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import lk.ijse.gdse41.ias.dao.custom.AttendanceDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.AttendanceDTO;

/**
 *
 * @author midda
 */
public class AttendanceDAOImpl implements AttendanceDAO{
    
    Connection conn;
    public AttendanceDAOImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public boolean add(AttendanceDTO dto) throws Exception {
        String SQL="INSERT INTO attendance VALUES (?,?,?)";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, dto.getId());
        stm.setObject(2, dto.getA_date());
        stm.setObject(3, dto.getCheck_in());
        return stm.executeUpdate()>0;
    }

    @Override
    public ArrayList<AttendanceDTO> get(AttendanceDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
