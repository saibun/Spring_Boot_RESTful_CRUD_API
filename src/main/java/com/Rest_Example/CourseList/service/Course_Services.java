package com.Rest_Example.CourseList.service;
import com.Rest_Example.CourseList.entity.Courses;



import java.util.List;
//Course_Service taking request from Course_Controller
public interface Course_Services {
    public List<Courses> getCourse();
    public Courses addCourse(Courses newOne);

    public Courses singleCourse( long id);

    public void deleteCourse(long id);

}
