/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.custom.TutorSubjectController;
import lk.ijse.gdse41.ias.dao.DAOFactory;
import lk.ijse.gdse41.ias.dao.custom.QueryDAO;
import lk.ijse.gdse41.ias.dao.custom.TutorSubjectDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.TutorSubjectDTO;
import lk.ijse.gdse41.ias.other.IDGenarator;

/**
 *
 * @author midda
 */
public class TutorSubjectControllerImpl implements TutorSubjectController{

    Connection conn;
    public TutorSubjectControllerImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
    }


    @Override
    public boolean add(ArrayList<TutorSubjectDTO> dtoList) throws Exception {
        String tsid="";
        for (TutorSubjectDTO tsdto : dtoList) {
            if(dtoList.indexOf(tsdto)==0){
                tsid=IDGenarator.getNewID("tutor_subject", "tsid", "TS");
            }else{
                tsid=IDGenarator.getNewID(tsid, "TS");
            }
            tsdto.setTsid(tsid);
            if(!add(tsdto)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean add(TutorSubjectDTO dto) throws Exception {
        TutorSubjectDAO tutorSubjectDAO=(TutorSubjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.TUTOR_SUBJECTS);
        return tutorSubjectDAO.add(dto);
    }

    @Override
    public ArrayList<TutorSubjectDTO> get(TutorSubjectDTO dto) throws Exception {
        QueryDAO queryDAO=(QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
        return queryDAO.getSubjectWithTutor(dto.getTid());
    }
}
