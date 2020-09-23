package ua.com.foxminded.dao.interfaces;

import java.util.List;

import ua.com.foxminded.dao.entity.Teacher;

public interface TeacherDao {

    public void addTeacher(Teacher teacher);
    public void editTeacher(Teacher teacher);
    public void deleteTeacher(String id);
    public List<Teacher> findTeacher(String id);
    public List<Teacher> findAllTeacher();  
}
