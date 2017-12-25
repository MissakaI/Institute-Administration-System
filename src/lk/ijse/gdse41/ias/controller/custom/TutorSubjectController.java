/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.SuperController;
import lk.ijse.gdse41.ias.dto.TutorSubjectDTO;

/**
 *
 * @author midda
 */
public interface TutorSubjectController extends SuperController<TutorSubjectDTO>{

    @Override
    public default boolean update(TutorSubjectDTO dto) throws Exception {return false;}

    @Override
    public default boolean delete(TutorSubjectDTO dto) throws Exception {return false;}

    @Override
    public default TutorSubjectDTO search(TutorSubjectDTO dto) throws Exception {return null;}

    @Override
    public default ArrayList<TutorSubjectDTO> getAll() throws Exception {return null;}
    
    public boolean add(ArrayList<TutorSubjectDTO> dtoList) throws Exception;
    
}
