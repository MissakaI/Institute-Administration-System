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
import java.time.LocalDate;
import lk.ijse.gdse41.ias.dao.custom.PaymentDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.PaymentDTO;
import lk.ijse.gdse41.ias.other.IDGenarator;

/**
 *
 * @author midda
 */
public class PaymentDAOImpl implements PaymentDAO{

    Connection c;
    public PaymentDAOImpl() {
        c=ConnectionFactory.getInstance().getConnection();
    }
    
    @Override
    public boolean add(PaymentDTO dto) throws Exception {
        int month=LocalDate.now().getMonth().getValue();
        String date=LocalDate.now().toString();
        
        String SQL="INSERT INTO payment VALUES (?,?,?,?,?)";
        PreparedStatement stm=c.prepareStatement(SQL);
        stm.setObject(1, IDGenarator.getNewID("payment", "pid", "P"));
        stm.setObject(2, dto.getRid());
        stm.setObject(3, month);
        stm.setObject(4, date);
        stm.setObject(5, dto.isFree_card());
        return stm.executeUpdate()>0;
    }

    @Override
    public PaymentDTO search(PaymentDTO dto) throws Exception {
        String SQL=String.format("SELECT * FROM payment WHERE rid='%s' && p_month=%d",dto.getRid(),dto.getP_month());
        Statement stm=c.createStatement();
        ResultSet rst=stm.executeQuery(SQL);
        if(rst.next()){
            PaymentDTO p=new PaymentDTO(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getString(4), rst.getBoolean(5));
            return p;
        }
        return null;
    }
    
}
