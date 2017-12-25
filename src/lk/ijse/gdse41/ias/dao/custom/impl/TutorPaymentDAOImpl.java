/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lk.ijse.gdse41.ias.dao.custom.TutorPaymentDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.TutorPaymentDTO;

/**
 *
 * @author midda
 */
public class TutorPaymentDAOImpl implements TutorPaymentDAO{
    
    Connection c;
    public TutorPaymentDAOImpl() {
        c=ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public boolean add(TutorPaymentDTO dto) throws Exception {
        String SQL="INSERT INTO tutor_payment VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm=c.prepareStatement(SQL);
        stm.setObject(1, dto.getTpid());
        stm.setObject(2, dto.getTid());
        stm.setObject(3, dto.getCid());
        stm.setObject(4, dto.getPay_date());
        stm.setObject(5, dto.getPay_month());
        stm.setObject(6, dto.getNo_of_students());
        stm.setObject(7, dto.getNo_of_free_cards());
        stm.setObject(8, dto.getTotal_amount());
        stm.setObject(9, dto.getCommission_rate());
        stm.setObject(10, dto.getCommission());
        stm.setObject(11, dto.getPay_amount());
        return stm.executeUpdate()>0;
    }

    @Override
    public TutorPaymentDTO search(TutorPaymentDTO dto) throws Exception {
        String SQL="SELECT * FROM tutor_payment WHERE cid=? && pay_month=? ORDER BY pay_date DESC LIMIT 1";
        PreparedStatement stm=c.prepareStatement(SQL);
        stm.setObject(1, dto.getCid());
        stm.setObject(2, dto.getPay_month());
        ResultSet rst=stm.executeQuery();
        if (rst.next()){
            TutorPaymentDTO tpdto=new TutorPaymentDTO(rst.getString(1), rst.getString(2), 
                    rst.getString(3), rst.getString(4), rst.getInt(5), rst.getInt(6), 
                    rst.getInt(7), rst.getDouble(8), rst.getDouble(9), rst.getDouble(10), rst.getDouble(11));
            return tpdto;
        }
        return null;
    }
    
}
