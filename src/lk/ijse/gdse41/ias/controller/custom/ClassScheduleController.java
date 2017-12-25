/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.SuperController;
import lk.ijse.gdse41.ias.dto.ClassScheduleDTO;

/**
 *
 * @author midda
 */
public interface ClassScheduleController extends SuperController<ClassScheduleDTO>{

    @Override
    public default boolean delete(ClassScheduleDTO dto) throws Exception {return false;}

    @Override
    public default ClassScheduleDTO search(ClassScheduleDTO dto) throws Exception {return null;}

    @Override
    public default ArrayList<ClassScheduleDTO> getAll() throws Exception {return null;}
    
    public boolean add(ArrayList<ClassScheduleDTO> dtoList) throws Exception;
    
    public boolean update(ArrayList<ClassScheduleDTO> dtoList) throws Exception;
    
    public ArrayList<ClassScheduleDTO> getHallSchedule(ClassScheduleDTO dto) throws Exception;
    
}
