package ua.com.foxminded.dao.interfaces;

import java.util.List;

import ua.com.foxminded.dao.entity.Teacher;

public interface TeacherDao {
    public void addTeacher(Teacher teacher);
    public void editTeacher(Teacher teacher);
    public void deleteTeacher(Teacher teacher);
    public Teacher findTeacher(String id);
    public List<Teacher> findAllTeacher();
}
