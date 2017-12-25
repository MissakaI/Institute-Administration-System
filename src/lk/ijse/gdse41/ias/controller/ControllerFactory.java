/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller;

import lk.ijse.gdse41.ias.controller.custom.TutorPaymentController;
import lk.ijse.gdse41.ias.controller.custom.impl.AttendanceControllerImpl;
import lk.ijse.gdse41.ias.controller.custom.impl.BatchControllerImpl;
import lk.ijse.gdse41.ias.controller.custom.impl.ClassScheduleControllerImpl;
import lk.ijse.gdse41.ias.controller.custom.impl.EmployeeControllerImpl;
import lk.ijse.gdse41.ias.controller.custom.impl.HallControllerImpl;
import lk.ijse.gdse41.ias.controller.custom.impl.PaymentControllerImpl;
import lk.ijse.gdse41.ias.controller.custom.impl.RegistrationControllerImpl;
import lk.ijse.gdse41.ias.controller.custom.impl.StudentControllerImpl;
import lk.ijse.gdse41.ias.controller.custom.impl.SubjectControllerImpl;
import lk.ijse.gdse41.ias.controller.custom.impl.TutorControllerImpl;
import lk.ijse.gdse41.ias.controller.custom.impl.TutorPaymentControllerImpl;
import lk.ijse.gdse41.ias.controller.custom.impl.TutorSubjectControllerImpl;

/**
 *
 * @author midda
 */
public class ControllerFactory {
    private static ControllerFactory controllerFactory;
    
    public enum ControllerTypes{
        ATTENDANCE,BATCH,CLASS_SCHEDULE,EMPLOYEE,PAYMENT,REGISTRATION,STUDENT,SUBJECT,TUTOR,TUTOR_SUBJECTS,HALL,TUTOR_PAYMENT
    }
    
    private ControllerFactory(){
        
    }

    public static ControllerFactory getInstance() {
        if (controllerFactory==null){
            controllerFactory=new ControllerFactory();
        }
        return controllerFactory;
    }
    
    public SuperController getController(ControllerTypes controller){
        switch(controller){
            case ATTENDANCE:
                return new AttendanceControllerImpl();
            case EMPLOYEE:
                return new EmployeeControllerImpl();
            case SUBJECT:
                return new SubjectControllerImpl();
            case TUTOR:
                return new TutorControllerImpl();
            case TUTOR_SUBJECTS:
                return new TutorSubjectControllerImpl();
            case HALL:
                return new HallControllerImpl();
            case BATCH:
                return new BatchControllerImpl();
            case PAYMENT:
                return new PaymentControllerImpl();
            case REGISTRATION:
                return new RegistrationControllerImpl();
            case STUDENT:
                return new StudentControllerImpl();
            case CLASS_SCHEDULE:
                return new ClassScheduleControllerImpl();
            case TUTOR_PAYMENT:
                return new TutorPaymentControllerImpl();
            default : 
                return null;
        }
    }
    
    
}
