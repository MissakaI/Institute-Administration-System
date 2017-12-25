/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.SuperController;
import lk.ijse.gdse41.ias.dto.RegistrationDTO;

/**
 *
 * @author midda
 */
public interface RegistrationController extends SuperController<RegistrationDTO>{

    @Override
    public default boolean update(RegistrationDTO dto) throws Exception {return false;}

    @Override
    public default boolean delete(RegistrationDTO dto) throws Exception {return false;}

    @Override
    public default ArrayList<RegistrationDTO> get(RegistrationDTO dto) throws Exception {return null;}

    @Override
    public default ArrayList<RegistrationDTO> getAll() throws Exception {return null;}
    
    public boolean add(ArrayList<RegistrationDTO> dtoList) throws Exception;

}
