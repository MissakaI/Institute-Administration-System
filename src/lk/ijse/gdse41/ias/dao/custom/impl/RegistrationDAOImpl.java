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
import lk.ijse.gdse41.ias.dao.custom.RegistrationDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.RegistrationDTO;

/**
 *
 * @author midda
 */
public class RegistrationDAOImpl implements RegistrationDAO{
    
    Connection conn;
    public RegistrationDAOImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public boolean add(RegistrationDTO dto) throws Exception {
        String SQL="INSERT INTO registration VALUES (?,?,?)";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, dto.getRid());
        stm.setObject(2, dto.getSid());
        stm.setObject(3, dto.getCid());
        return stm.executeUpdate()>0;
    }

    @Override
    public RegistrationDTO search(RegistrationDTO dto) throws Exception {
        String SQL=String.format("SELECT * FROM registration WHERE sid='%s' && cid='%s'", dto.getSid(),dto.getCid());
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(SQL);
        if(rst.next()){
            RegistrationDTO r=new RegistrationDTO(rst.getString(1), dto.getSid(), dto.getCid());
            return r;
        }
        return null;
    }

    
}
