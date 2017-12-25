/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom.impl;

import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.ClassScheduleDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.BatchController;
import lk.ijse.gdse41.ias.controller.custom.ClassScheduleController;
import lk.ijse.gdse41.ias.dao.DAOFactory;
import lk.ijse.gdse41.ias.dao.custom.ClassScheduleDAO;
import lk.ijse.gdse41.ias.dao.custom.QueryDAO;
import lk.ijse.gdse41.ias.dto.BatchDTO;
import lk.ijse.gdse41.ias.other.IDGenarator;

/**
 *
 * @author midda
 */
public class ClassScheduleControllerImpl implements ClassScheduleController{

    Connection conn;
    ClassScheduleDAO classScheduleDAO;
    QueryDAO queryDAO;
    public ClassScheduleControllerImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
        classScheduleDAO=(ClassScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CLASS_SCHEDULE);
        queryDAO=(QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }
    
    
    
    @Override
    public boolean add(ArrayList<ClassScheduleDTO> dtoList) throws Exception {
        conn.setAutoCommit(false);
        try{
            ArrayList<BatchDTO> bdtos=new ArrayList<>();
            String bid="";
            for (ClassScheduleDTO csdto : dtoList) {
                if (csdto.getBid().equals("")){
                    if(bdtos.isEmpty()){
                        bid=IDGenarator.getNewID("batch", "bid", "B");
                    }else{
                        bid=IDGenarator.getNewID(bid, "B");
                    }
                    BatchDTO batchDTO=new BatchDTO(bid, csdto.getbName(), csdto.getYear(), csdto.getSection());
                    csdto.setBid(bid);
                    bdtos.add(batchDTO);
                    System.out.println("Add to bdtos");                         //print
                }
            }
            BatchController ctrlBatch=(BatchController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.BATCH);
            System.out.println(bdtos.isEmpty());
            if (ctrlBatch.add(bdtos)){
                System.out.println("Batches succesfully added");
                String cid="";
                for (ClassScheduleDTO csdto : dtoList) {
                    if(dtoList.indexOf(csdto)==0){
                        cid=IDGenarator.getNewID("class_schedule", "cid", "CS");
                    }else{
                        cid=IDGenarator.getNewID(cid, "CS");
                    }
                    csdto.setCid(cid);
                    if(!add(csdto)){
                        conn.rollback();
                        System.out.println("Rollbacked");
                        return false;
                    }
                }
            }
            conn.commit();
            System.out.println("Commited");
            return true;
        }finally{
            System.out.println("Auto Commit : true");
            conn.setAutoCommit(true);
        }
    }

    @Override
    public boolean add(ClassScheduleDTO dto) throws Exception {
        return classScheduleDAO.add(dto);
    }
    
    @Override
    public boolean update(ArrayList<ClassScheduleDTO> dtoList) throws Exception {
        for (ClassScheduleDTO cs : dtoList) {
            if (!update(cs)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(ClassScheduleDTO dto) throws Exception {
        return classScheduleDAO.update(dto);
    }

    @Override
    public ArrayList<ClassScheduleDTO> get(ClassScheduleDTO dto) throws Exception {
        return queryDAO.getCSandStudentCount(dto);
    }
    
    @Override
    public ArrayList<ClassScheduleDTO> getHallSchedule(ClassScheduleDTO dto) throws Exception {
        return queryDAO.getHallSchedule(dto);
    }

    
    
    public static ClassScheduleDTO getClassSchedule(String bid, String tid, String subID) throws SQLException, ClassNotFoundException{
        String SQL="SELECT * FROM class_schedule WHERE bid=? && tsid "
                + "IN (SELECT tsid FROM tutor_subject WHERE tid=? && subID=?)";
        Connection conn=ConnectionFactory.getInstance().getConnection();
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1,bid);
        stm.setObject(2,tid);
        stm.setObject(3,subID);
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            ClassScheduleDTO cs=new ClassScheduleDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),
                        rst.getInt(6), rst.getString(7), rst.getInt(8), rst.getDouble(9), rst.getDouble(10), 0);
            return cs;
        }
        return null;
    }

}
