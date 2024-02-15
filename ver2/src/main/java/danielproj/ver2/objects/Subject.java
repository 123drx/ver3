package danielproj.ver2.objects;

import java.util.HashSet;
import java.util.Set;

public class Subject {
    private String SubjectName;
    private String TeacherName;
    private Set<String> classes = new HashSet<>();
    private int WeaklyHours;


    public void addClass(String ClassName)
    {
        this.classes.add(ClassName);
    }

    public Subject()
    {

    }

    public Subject(String SubjectName,Set<String> Classes)
    {
        this.setSubjectName(SubjectName);
        this.setClasses(Classes);
    }
    
    public Subject(Subject otherSubject) {
        this.SubjectName = otherSubject.getSubjectName();
        this.TeacherName = otherSubject.getTeacherName();
        this.WeaklyHours = otherSubject.getWeaklyHours();
        this.classes = otherSubject.getClasses();
    }


    public Subject(String SubjectName ,String className)
    {

    }

    public Subject(String Name)
    {
        this.SubjectName = Name;
    }
    
    public String getSubjectName() {
        return SubjectName;
    }
    
    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }
    
    public String getTeacherName() {
        return TeacherName;
    }
    
    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }
    
    public int getWeaklyHours() {
        return WeaklyHours;
    }
    
    public void setWeaklyHours(int weaklyHours) {
        WeaklyHours = weaklyHours;
    }

    public Set<String> getClasses() {
        return classes;
    }

    public void setClasses(Set<String> classes) {
        this.classes = classes;
    }
    



}
