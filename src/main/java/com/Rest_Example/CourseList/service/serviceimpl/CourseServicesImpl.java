package com.Rest_Example.CourseList.service.serviceimpl;

import com.Rest_Example.CourseList.customException.ServiceException;
import com.Rest_Example.CourseList.entity.Courses;
import com.Rest_Example.CourseList.repository.CoursesRepo;
import com.Rest_Example.CourseList.service.Course_Services;
import com.Rest_Example.CourseList.utility.GlobalUse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//CourseServiceImpl declere all thoes method define into service
public class CourseServicesImpl implements Course_Services {


    private final Logger serviceImplLog = GlobalUse.getLogger(CourseServicesImpl.class);
    @Autowired
    private CoursesRepo cr;

    @Override
    public List<Courses> getCourse(){
        String method = "getCourse";
        List<Courses> result = cr.findAll();
        if(cr.findAll().isEmpty()){
            serviceImplLog.warn("ServiceImpl Called {} is empty", method);
            throw  new ServiceException("404","No Data Found into Database");
        }
        try {

            serviceImplLog.info("ServiceImpl Called {}", method);
            return result;
        }catch (Exception e){
            serviceImplLog.warn("ServiceImpl Called {} with {}", method,e.getMessage());
            throw new ServiceException("500","Server Error  "+e.getMessage());
        }
    }

    @Override
    public Courses addCourse(Courses newOne){
        if(newOne.getName().isEmpty()||newOne.getId()<0||newOne.getIntro().isEmpty()){
            throw new ServiceException("406","Mandotory Fields Missing");
        }
        try{

            String method = "addCourse";
            serviceImplLog.info("ServiceImpl Called {} with {}",method,newOne);
            cr.save(newOne);
            return  newOne;
        }catch (Exception e){
            throw new ServiceException("500","Server Problem " +e.getMessage());

        }

    }

    @Override
    public Courses singleCourse( long id) {
        String method = null;
        try {
            method = "singleCourse";
            serviceImplLog.info("ServiceImpl Called {} with {}", method, id);

            return cr.findById(id).get();
        } catch (Exception e) {
            serviceImplLog.error("ServiceImpl called {} with {}", method,id );
            throw  new ServiceException("404", "Id Mismatch " + e.getMessage());
        }

    }

    @Override
    public void deleteCourse(long id){
        String method ="deleteCourse";
        try{
            serviceImplLog.info("ServiceImpl Called {} with {}",method,id);
            Courses entity = cr.findById(id).get();
            cr.delete(entity);
        }catch (Exception e){

            serviceImplLog.error("ServiceImpl Called {} with {} error {}",method,id,e.getMessage());
            throw new ServiceException("404","Id Mismatch"+e.getMessage());
        }


    }
}
