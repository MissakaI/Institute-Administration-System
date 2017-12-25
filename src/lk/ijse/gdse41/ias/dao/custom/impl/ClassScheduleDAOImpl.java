/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import lk.ijse.gdse41.ias.dao.custom.ClassScheduleDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.ClassScheduleDTO;

/**
 *
 * @author midda
 */
public class ClassScheduleDAOImpl implements ClassScheduleDAO{
    
    Connection conn;
    public ClassScheduleDAOImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public ArrayList<ClassScheduleDTO> getHallSchedule(ClassScheduleDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(ClassScheduleDTO dto) throws Exception {
        String SQL="INSERT INTO class_schedule VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, dto.getCid());
        stm.setObject(2, dto.getTsid());
        stm.setObject(3, dto.getBid());
        stm.setObject(4, dto.getHid_1());
        stm.setObject(5, dto.getHid_2());
        stm.setObject(6, dto.getC_day());
        stm.setObject(7, dto.getTime());
        stm.setObject(8, dto.getDuration());
        stm.setObject(9, dto.getM_fee());
        stm.setObject(10, dto.getReg_fee());
        return (stm.executeUpdate()>0);
    }

    @Override
    public boolean update(ClassScheduleDTO dto) throws Exception {
        String SQL="UPDATE class_schedule SET hid_1=?, hid_2=?, c_day=?, c_time=?, duration=?, m_fee=?, reg_fee=? WHERE cid=?";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(8, dto.getCid());
        stm.setObject(1, dto.getHid_1());
        stm.setObject(2, dto.getHid_2());
        stm.setObject(3, dto.getC_day());
        stm.setObject(4, dto.getTime());
        stm.setObject(5, dto.getDuration());
        stm.setObject(6, dto.getM_fee());
        stm.setObject(7, dto.getReg_fee());
        return (stm.executeUpdate()>0);
    }

    @Override
    public ArrayList<ClassScheduleDTO> get(ClassScheduleDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
