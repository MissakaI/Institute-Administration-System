/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.SuperController;
import lk.ijse.gdse41.ias.dto.PaymentDTO;

/**
 *
 * @author midda
 */
public interface PaymentController extends SuperController<PaymentDTO>{

    @Override
    public default boolean update(PaymentDTO dto) throws Exception {return false;}

    @Override
    public default boolean delete(PaymentDTO dto) throws Exception {return false;}

    @Override
    public default ArrayList<PaymentDTO> get(PaymentDTO dto) throws Exception {return null;}

    @Override
    public default ArrayList<PaymentDTO> getAll() throws Exception {return null;}
    
    public PaymentDTO getTutorsMonthlyPayment(String cid, int month) throws Exception;
    
}
