package danielproj.ver2.objects;

public class Lesson {

    private String LessonSubject;
    private String Teacher;
    private int StartHour;
    private int day;
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Lesson(String LessonSubject, String Teacher, int Day , int StartHour) {
        this.LessonSubject = LessonSubject;
        this.Teacher = Teacher;
        this.StartHour = StartHour;
        this.day = Day;        
    }

    public Lesson(Lesson lesson) {
        this.LessonSubject = lesson.getLessonSubject();
        this.Teacher = lesson.getTeacher();
        this.StartHour = lesson.getStartHour();
        this.day = lesson.getDay();
    }

    public Lesson() {

    }

    public String getLessonSubject() {
        return LessonSubject;
    }

    public void setLessonSubject(String lessonSubject) {
        LessonSubject = lessonSubject;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
    }

    public int getStartHour() {
        return StartHour;
    }

    public void setStartHour(int startHour) {
        StartHour = startHour;
    }

    public String GetLesson() {
        String s;
        s = "" + LessonSubject + " " + Teacher;
        return s;
    }

    public void PrintLesson() {
        String s;
        s = "" + LessonSubject + " " + Teacher;
        System.out.println(s);
    }

    public String PrintHours() {
        String s;
        s = "Day : " + day + " hour : " + StartHour +"\t"; 
        return s;
    }

}
