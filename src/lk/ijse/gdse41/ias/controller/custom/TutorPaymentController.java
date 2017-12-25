/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.SuperController;
import lk.ijse.gdse41.ias.dto.TutorPaymentDTO;

/**
 *
 * @author midda
 */
public interface TutorPaymentController extends SuperController<TutorPaymentDTO>{

    @Override
    public default boolean update(TutorPaymentDTO dto) throws Exception {return false;}

    @Override
    public default boolean delete(TutorPaymentDTO dto) throws Exception {return false;}

    @Override
    public default ArrayList<TutorPaymentDTO> get(TutorPaymentDTO dto) throws Exception {return null;}

    @Override
    public default ArrayList<TutorPaymentDTO> getAll() throws Exception {return null;}
    
    public boolean add(ArrayList<TutorPaymentDTO> dtoList) throws Exception;
    
}
