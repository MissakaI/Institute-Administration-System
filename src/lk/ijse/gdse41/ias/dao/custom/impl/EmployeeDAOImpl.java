/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import lk.ijse.gdse41.ias.dao.custom.EmployeeDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.EmployeeDTO;

/**
 *
 * @author midda
 */
public class EmployeeDAOImpl implements EmployeeDAO{

    Connection conn;
    public EmployeeDAOImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
    }
    
    @Override
    public boolean add(EmployeeDTO dto) throws Exception {
        String SQL="INSERT INTO employee VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, dto.getEid());
        stm.setObject(2, dto.getfName());
        stm.setObject(3, dto.getlName());
        stm.setObject(4, dto.getAddress());
        stm.setObject(5, dto.getDob());
        stm.setObject(6, dto.getNic());
        stm.setObject(7, dto.getTpNo1());
        stm.setObject(8, dto.getTpNo2());
        stm.setObject(9, dto.getUsername());
        stm.setObject(10, dto.getPassword());
        stm.setObject(11, dto.getAccessLevel());
        return stm.executeUpdate()>0;
    }
    
}
