package com.Rest_Example.CourseList.repository;
import com.Rest_Example.CourseList.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

//taking request from service and connceted with database
public interface CoursesRepo extends JpaRepository<Courses,Long>{

}
