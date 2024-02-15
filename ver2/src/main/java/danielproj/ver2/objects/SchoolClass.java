package danielproj.ver2.objects;


import java.util.ArrayList;
import java.util.List;

public class SchoolClass {
    private String className;
    private Schedule schedule;
    private List<Subject> subjects = new ArrayList<>();
    private List<String> Teachers = new ArrayList<>();
    private List<Lesson> LockedLessons = new ArrayList<>();

    public void adjustEmptySubject() {
        int MaxD = Schedule.MaxDays;
        int MaxH = Schedule.MaxHours;
        int EmptySubjectWeeklyHours = (MaxD * MaxH);

        for (Subject subject : subjects) {
            EmptySubjectWeeklyHours -= subject.getWeaklyHours();
        }

        if (!IsSubjectExist("Empty")) {
            Subject s = new Subject("Empty");
            s.setTeacherName("Non");
            s.setWeaklyHours(EmptySubjectWeeklyHours);
            System.out.println("Empty Hours : "+ EmptySubjectWeeklyHours);
            addSubject(s);
        }
        else {
            subjects.get(getSubjectIndex("Empty")).setWeaklyHours(EmptySubjectWeeklyHours);
            System.out.println(subjects.get(getSubjectIndex("Empty")).getWeaklyHours() + "<- Weekly hours");
        }
        
        
        //System.out.println("Empty Hours : "+ EmptySubjectWeeklyHours);
    }

   public Subject getSubject(String SubjectName)
   {
    for(int i = 0 ; i < subjects.size() ; i++)
    {
        if(subjects.get(i).getSubjectName().equals(SubjectName))
        {
            return subjects.get(i);
        }
    }
    return null;
   }

    public void printSubjectsweeklyhours()
    {
        String s = "";
        for(Subject subj : subjects) 
        {
            s += subj.getSubjectName() + " : " + "Hours : "+ subj.getWeaklyHours() +"\t"; 
        }       
        System.out.println(s);
    }

    public int getSubjectIndex(String SubjectName) {
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getSubjectName().equals(SubjectName)) {
                return i;
            }
        }
        return -1;
    }

    public boolean IsSubjectExist(String SubjectName) {
        for (Subject subject : subjects) {
            if (SubjectName.equals(subject.getSubjectName())) {
                return true;
            }
        }
        return false;
    }

    public void addLockedLesson(Lesson lesson) {
        this.LockedLessons.add(lesson);
    }

    public SchoolClass() {

    }

    public SchoolClass(SchoolClass otherClass) {
        this.className = otherClass.className;
        this.schedule = new Schedule(otherClass.schedule);

        // Deep copy for subjects
        for (Subject subject : otherClass.subjects) {
            this.subjects.add(new Subject(subject));
        }

        // Copy Teachers (assuming it's just a list of names, so a shallow copy is fine)
        this.Teachers = new ArrayList<>(otherClass.Teachers);
    }

    public void printsubjects() {
        for (Subject s : this.subjects) {
            System.out.println("stubject " + s.getSubjectName());
        }
    }

    public boolean isSubjectExist(String SubjectName) {
        for (Subject s : subjects) {
            if (s.getSubjectName().equals(SubjectName)) {
                return true;
            }
        }
        return false;
    }

    public void initSchedule() {
        this.schedule = new Schedule();
        this.schedule.InitSchedule();
    }

    public SchoolClass(String Name) {
        this.className = Name;
    }

    public String getClassName() {
        return className;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule s) {
        this.schedule = s;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addteacher(String teacher) {
        this.Teachers.add(teacher);
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject Subject) {
        this.subjects.add(Subject);
        if (Subject.getTeacherName() != null) {
            if (!IsTeacherExists(Subject.getTeacherName())) {
                addteacher(Subject.getTeacherName());
            }
        }
    }

    public void RemoveSubject(int index) {
        this.subjects.remove(index);
    }

    public String tostring() {
        String s;
        s = "class" + className + "\n";
        s += "Subjects : ";
        for (Subject sbj : subjects) {
            s += sbj.getSubjectName() + " , ";
        }
        return s;
    }

    public boolean IsTeacherExists(String Name) {
        if (this.Teachers == null) {
            return false;
        }
        for (String t : this.Teachers) {
            if (t.equals(Name))
                return true;
        }
        return false;
    }

    public List<String> getTeachers() {
        return Teachers;
    }

    public void setTeachers(List<String> teachers) {
        Teachers = teachers;
    }

    public List<Lesson> getLockedLessons() {
        return LockedLessons;
    }

    public void setLockedLessons(List<Lesson> lockedLessons) {
        LockedLessons = lockedLessons;
    }

}