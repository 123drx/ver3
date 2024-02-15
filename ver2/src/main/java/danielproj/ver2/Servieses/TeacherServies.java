package danielproj.ver2.Servieses;import java.util.List;

import org.springframework.stereotype.Service;

import danielproj.ver2.Repositorys.TeacherRepository;
import danielproj.ver2.objects.Teacher;

@Service
public class TeacherServies {

    private TeacherRepository Trepo;

    public TeacherServies(TeacherRepository r)
    {
        this.Trepo = r;
    }
    
    public void insertTeacher(Teacher t)
    {
        Trepo.insert(t);
    }

    public List<Teacher> GetAllTeachers()
    {
        return Trepo.findAll(); 
    }

    public Teacher findByName(String Name)
    {
        return Trepo.findByName(Name);
    }
}
