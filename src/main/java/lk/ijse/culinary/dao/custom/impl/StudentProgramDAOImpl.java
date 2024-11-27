package lk.ijse.culinary.dao.custom.impl;

import lk.ijse.culinary.dao.custom.StudentProgramDAO;
import lk.ijse.culinary.entity.Student_Program;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentProgramDAOImpl implements StudentProgramDAO {

    private Session session;

    @Override
    public List<Student_Program> getAll() {
        String hql = "FROM Student_Program ";
        Query<Student_Program> query = session.createQuery(hql, Student_Program.class);
        return query.list();
    }

    @Override
    public void save(Student_Program entity) {
        session.save(entity);

    }

    @Override
    public void update(Student_Program entity) {
        session.update(entity);

    }

    @Override
    public void delete(Student_Program entity) {
        session.delete(entity);

    }

    @Override
    public Student_Program search(String id) {
        return session.get(Student_Program.class, id);
    }

    @Override
    public String generateNextId() {
        return null;
    }

    @Override
    public List<String> getIds() {
        return null;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Student_Program getStudentProgramById(String value) {
        return null;
    }
}
