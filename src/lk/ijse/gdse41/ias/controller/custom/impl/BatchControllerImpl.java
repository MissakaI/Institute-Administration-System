/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.custom.BatchController;
import lk.ijse.gdse41.ias.dao.DAOFactory;
import lk.ijse.gdse41.ias.dao.custom.BatchDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.BatchDTO;
import lk.ijse.gdse41.ias.other.IDGenarator;

/**
 *
 * @author midda
 */
public class BatchControllerImpl implements BatchController{

    BatchDAO batchDAO;
    public BatchControllerImpl() {
        batchDAO=(BatchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BATCH);
    }
    
    
    @Override
    public boolean add(ArrayList<BatchDTO> dtoList) throws Exception {
        String bid="";
        for (BatchDTO batchDTO : dtoList) {
            if(batchDTO.getBid().equals("")){
                if(dtoList.indexOf(batchDTO)==0){
                    bid=IDGenarator.getNewID("batch", "bid", "B");
                }else{
                    bid=IDGenarator.getNewID(bid, "B");
                }
                batchDTO.setBid(bid);
            }
            System.out.println(batchDTO.getBid());
            
            if(!add(batchDTO)){
                System.out.print("Batch add failed   ");
                return false;
            }
            System.out.println("Batch add success");
        }
        return true;
    }
    
    @Override
    public boolean add(BatchDTO dto) throws Exception {
        return batchDAO.add(dto);
    }

    @Override
    public BatchDTO search(BatchDTO dto) throws Exception {
        return batchDAO.search(dto);
    }

    @Override
    public ArrayList<BatchDTO> get(BatchDTO dto) throws Exception {
        return batchDAO.get(dto);
    }

    @Override
    public ArrayList<String> getSections() throws Exception {
        return batchDAO.getSections();
    }

    @Override
    public ArrayList<Integer> getSectionYears(String section) throws Exception {
        return batchDAO.getSectionYears(section);
    }

    
    
}
