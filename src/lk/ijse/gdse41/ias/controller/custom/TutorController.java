/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.SuperController;
import lk.ijse.gdse41.ias.dto.TutorDTO;
import lk.ijse.gdse41.ias.dto.TutorSubjectDTO;

/**
 *
 * @author midda
 */
public interface TutorController extends SuperController<TutorDTO>{

    @Override
    public default boolean update(TutorDTO dto) throws Exception {return false;}

    @Override
    public default boolean delete(TutorDTO dto) throws Exception {return false;}

    @Override
    public default TutorDTO search(TutorDTO dto) throws Exception {return null;}
    
    public boolean add(TutorDTO tutor, ArrayList<TutorSubjectDTO> dtoList) throws Exception;
    
}
