/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.dao.SuperDAO;
import lk.ijse.gdse41.ias.dto.PaymentDTO;

/**
 *
 * @author midda
 */
public interface PaymentDAO extends SuperDAO<PaymentDTO>{

    @Override
    public default boolean update(PaymentDTO dto) throws Exception {return false;}

    @Override
    public default boolean delete(PaymentDTO dto) throws Exception {return false;}

    @Override
    public default ArrayList<PaymentDTO> get(PaymentDTO dto) throws Exception {return null;}

    @Override
    public default ArrayList<PaymentDTO> getAll() throws Exception {return null;}
    
}
