/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao;

import lk.ijse.gdse41.ias.dao.custom.impl.AttendanceDAOImpl;
import lk.ijse.gdse41.ias.dao.custom.impl.BatchDAOImpl;
import lk.ijse.gdse41.ias.dao.custom.impl.ClassScheduleDAOImpl;
import lk.ijse.gdse41.ias.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.gdse41.ias.dao.custom.impl.HallDAOImpl;
import lk.ijse.gdse41.ias.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.gdse41.ias.dao.custom.impl.QueryDAOImpl;
import lk.ijse.gdse41.ias.dao.custom.impl.RegistrationDAOImpl;
import lk.ijse.gdse41.ias.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse41.ias.dao.custom.impl.SubjectDAOImpl;
import lk.ijse.gdse41.ias.dao.custom.impl.TutorDAOImpl;
import lk.ijse.gdse41.ias.dao.custom.impl.TutorPaymentDAOImpl;
import lk.ijse.gdse41.ias.dao.custom.impl.TutorSubjectDAOImpl;

/**
 *
 * @author midda
 */
public class DAOFactory {
    private static DAOFactory daoFactory;
    
    public enum DAOTypes{
        ATTENDANCE,BATCH,CLASS_SCHEDULE,EMPLOYEE,PAYMENT,REGISTRATION,STUDENT,SUBJECT,TUTOR,TUTOR_SUBJECTS,HALL,TUTOR_PAYMENT,QUERY
    }
    
    private DAOFactory(){
        
    }

    public static DAOFactory getInstance() {
        if (daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }
    
    public SuperDAO getDAO(DAOTypes dao){
        switch(dao){
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case SUBJECT:
                return new SubjectDAOImpl();
            case TUTOR:
                return new TutorDAOImpl();
            case TUTOR_SUBJECTS:
                return new TutorSubjectDAOImpl();
            case HALL:
                return new HallDAOImpl();
            case BATCH:
                return new BatchDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case REGISTRATION:
                return new RegistrationDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case CLASS_SCHEDULE:
                return new ClassScheduleDAOImpl();
            case TUTOR_PAYMENT:
                return new TutorPaymentDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default : 
                return null;
        }
    }
}
