package danielproj.ver2.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import danielproj.ver2.Algorithms.GeneticAlgorithm;
import danielproj.ver2.objects.Constrains;
import danielproj.ver2.objects.Lesson;
import danielproj.ver2.objects.Schedule;
import danielproj.ver2.objects.School;
import danielproj.ver2.objects.SchoolClass;
import danielproj.ver2.objects.Teacher;
import danielproj.ver2.objects.Subject;

@Route("/page")
@PageTitle("TestPage")
public class Page extends VerticalLayout{
     


    public Page(){
        Button btn = new Button("print empty schedule");
        btn.addClickListener(event -> PrintEmptySchool());
        Button btn1 = new Button("test crossover()");
        btn1.addClickListener(event -> PreformCrossOver());
        Button btn2 = new Button("print tournamentSelection");
        btn2.addClickListener(event -> PreformtournamentSelection("יא"));
        Button btn3 = new Button("Prefotm mutate");
        btn3.addClickListener(event -> PerformMutate());
        Button btn4 = new Button("print Genetic Algo");
        btn4.addClickListener(event -> PerformGeneticAlgorithm());
        Button btn5 = new Button("Preform Avaliability");
        btn5.addClickListener(event -> PreformAvaliability());
        Button btn6 = new Button("prefome soms");
        btn6.addClickListener(e -> preformEmptys());
        
        
        //Button btn5 = new Button("CheckEvaluate");
        //btn5.addClickListener(event -> CheckEvaluate());
        //Button btn6 = new Button("ChecckgeneticAlgo");
        //btn6.addClickListener(event -> ChecckgeneticAlgo());
        // add list of theacher with prefereces and enable next lines

        // Button btn4 = new Button("Print GeneticAlgorithmSolution");
        // btn4.addClickListener(event -> GeneticAlgorithm());
        H1 s = new H1("Test Page");
        add(s, btn, btn1,btn2,btn3,btn4,btn5,btn6);
       // add(s, btn, btn1, btn2, btn3, btn4,btn5,btn6);

    }

    public void preformEmptys()
    {
        School sc = CreateSchool();
        sc.adjustEmptySubjects();
        sc.printweeklyhours();
    }

    public void PerformMutate()
    {
        School school = new School();
        school = CreateSchool();
        school.printSChool();
        System.out.println("Schedule : (Before the Mutation)");
        school.getClasses().get(0).getSchedule().FillThisScheduleWithLessons(school.getClasses().get(0).getSubjects());
        school.getClasses().get(0).getSchedule().printSchedule();
        Schedule s = new Schedule();
        s = school.getClasses().get(0).getSchedule().Bigmutate(school.getClasses().get(0).getSubjects());
        System.out.println("Schedule : (after the Mutation)");
        school.getClasses().get(0).setSchedule(s);
        school.getClasses().get(0).getSchedule().printSchedule();

    }

    public void PreformAvaliability()
    {
        School s = CreateSchool();
        s.Avaliability();
    }

    public void PrintEmptySchool()
    {
        School school = new School();
        SchoolClass sc1 = new SchoolClass("יא");
        SchoolClass sc2 = new SchoolClass("יב");
        school.addclass(sc1);
        school.addclass(sc2);
        for(SchoolClass s :school.getClasses())
        {
            s.initSchedule();
        }
        school.printSChool();
    }

    public void PreformtournamentSelection(String ClassName)
    {
        List<School> schools = new ArrayList<>();
        List<Integer> Evals = new ArrayList<>();
        School school = CreateSchool();
        for(SchoolClass cs : school.getClasses())
        {
            cs.getSchedule().FillThisScheduleWithLessons(cs.getSubjects());
        }
        School school2 = CreateSchool();
        for(SchoolClass cs : school2.getClasses())
        {
            cs.getSchedule().FillThisScheduleWithLessons(cs.getSubjects());
        }
        School school3 = CreateSchool();
        for(SchoolClass cs : school3.getClasses())
        {
            cs.getSchedule().FillThisScheduleWithLessons(cs.getSubjects());
        }
        schools.add(school);
        schools.add(school2);
        schools.add(school3);
        Evals.add(school.evaluateSchoolClass(ClassName));
        Evals.add(school2.evaluateSchoolClass(ClassName));
        Evals.add(school3.evaluateSchoolClass(ClassName));
        List<Integer> SortedIndexes = new ArrayList<>();
        getSortedIndexes(Evals);
        School schl = school3.tournamentSelection(schools, Evals,SortedIndexes);
        int i = 0 ;
        for(School s : schools)
        {
            System.out.println("===========================================school "+(i+1)+"================================================");
            s.printSChool();
            i++;
        }

        for(int unt : Evals)
        {
            System.out.println("Eval :"+ unt );
        }
        System.out.println("=================================returned : ============================================================");
        schl.printSChool();


    }

    public void PreformCrossOver()
    {
        School school = CreateSchool();
        for(SchoolClass cs : school.getClasses())
        {
            cs.getSchedule().FillThisScheduleWithLessons(cs.getSubjects());
        }
        School school2 = CreateSchool();
        for(SchoolClass cs : school2.getClasses())
        {
            cs.getSchedule().FillThisScheduleWithLessons(cs.getSubjects());
        }
        school.crossover(school2,school);
        System.out.println("=====================================returned=============================================");
        school.printSChool();
    }

    private static List<Integer> getSortedIndexes(List<Integer> values) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            indices.add(i);
        }

        // Sort indices based on corresponding values
        Collections.sort(indices, Comparator.comparing(values::get).reversed());

        return indices;
    }

    private void PerformGeneticAlgorithm()
    {
        School testSchool  = CreateSchool();
        testSchool.adjustEmptySubjects();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        School s = geneticAlgorithm.Geneticalgorithm(testSchool,"י");
        s = geneticAlgorithm.Geneticalgorithm(s,"יא");
        System.out.println("============================================Eval=======================");
        s.printEvaluation("י");
        System.out.println();
        s.printEvaluation("יא");
        s.PrintTeachersWeeklyHours();

       

    }

    public School CreateSchool()
    {
        //Create the School   
        School RetSchool = new School("Shuvo");
        //Create the classes
        SchoolClass class2 = new SchoolClass("י");
        SchoolClass class1 = new SchoolClass("יא");

        Teacher Guy = new Teacher("Guy");
        Constrains[] GuysConsts = new Constrains[Schedule.MaxDays];
        List<Subject> subjects = new ArrayList<>();
        GuysConsts[0] = new Constrains(8,14);
        GuysConsts[4] = new Constrains(12,16);
        Set<String> classes = new HashSet<>();
        classes.add("י");
        classes.add("יא");
        Guy.setConstrains(GuysConsts);
        subjects.add(new Subject("machine learning", classes));
        subjects.add(new Subject("python", classes));
        subjects.add(new Subject("operating systems", classes));
        Guy.setSubjects(subjects);
        RetSchool.addTeacher(Guy);

        Teacher kodesh = new Teacher("Harab le Kodesh");
        Constrains[] KodeshConsts = new Constrains[Schedule.MaxDays];
        List<Subject> Kodeshsubjects = new ArrayList<>();
        KodeshConsts[3] = new Constrains(12,14);
        kodesh.setConstrains(KodeshConsts);
        Kodeshsubjects.add(new Subject("Kodesh", classes));
        kodesh.setSubjects(Kodeshsubjects);
        RetSchool.addTeacher(kodesh);

        Teacher ilan = new Teacher("ilan");
        Constrains[] ilanConsts = new Constrains[Schedule.MaxDays];
        List<Subject> ilansubjects = new ArrayList<>();
        ilanConsts[0] = new Constrains(8,16);
        ilanConsts[1] = new Constrains(8,14);
        ilanConsts[4] = new Constrains(8,10);
        ilan.setConstrains(ilanConsts);
        ilansubjects.add(new Subject("java", classes));
        ilansubjects.add(new Subject("project", classes));
        ilansubjects.add(new Subject("Asmbly", classes));
        ilan.setSubjects(ilansubjects);
        RetSchool.addTeacher(ilan);

        Teacher trabelsy = new Teacher("trabelsy");
        Constrains[] trabelsyConsts = new Constrains[Schedule.MaxDays];
        List<Subject> trabelsysubjects = new ArrayList<>();
        trabelsyConsts[1] = new Constrains(8,16);
        trabelsyConsts[2] = new Constrains(14,16);
        trabelsyConsts[3] = new Constrains(14,16);
        trabelsyConsts[4] = new Constrains(8,14);
        trabelsy.setConstrains(trabelsyConsts);
        trabelsysubjects.add(new Subject("statistics", classes));
        trabelsysubjects.add(new Subject("linear", classes));
        trabelsy.setSubjects(trabelsysubjects);
        RetSchool.addTeacher(trabelsy);

        Teacher mergi = new Teacher("Mergi");
        Constrains[] mergiConsts = new Constrains[Schedule.MaxDays];
        List<Subject> mergisubjects = new ArrayList<>();
        mergiConsts[0] = new Constrains(15,16);
        mergiConsts[2] = new Constrains(10,13);
        mergiConsts[3] = new Constrains(8,9);
        mergi.setConstrains(mergiConsts);
        mergisubjects.add(new Subject("nets", classes));
        mergi.setSubjects(mergisubjects);
        RetSchool.addTeacher(mergi);

        Teacher Yaniv = new Teacher("Yaniv");
        Constrains[] YanivConsts = new Constrains[Schedule.MaxDays];
        List<Subject> Yanivsubjects = new ArrayList<>();
        YanivConsts[2] = new Constrains(8,16);
        YanivConsts[3] = new Constrains(8,13);
        Yaniv.setConstrains(YanivConsts);
        Yanivsubjects.add(new Subject("Python", classes));
        Yanivsubjects.add(new Subject("hystory", classes));
        Yanivsubjects.add(new Subject("halaha", classes));
        Yaniv.setSubjects(Yanivsubjects);
        RetSchool.addTeacher(Yaniv);

        Teacher Amsalem = new Teacher("Amsalem");
        Constrains[] AmsalemConsts = new Constrains[Schedule.MaxDays];
        List<Subject> Amsalemsubjects = new ArrayList<>();
        AmsalemConsts[1] = new Constrains(15,16);
        Amsalem.setConstrains(AmsalemConsts);
        Amsalemsubjects.add(new Subject("computers", classes));
        Amsalem.setSubjects(Amsalemsubjects);
        RetSchool.addTeacher(Amsalem);

        Teacher Daniel = new Teacher("Daniel");
        Constrains[] DanielConsts = new Constrains[Schedule.MaxDays];
        List<Subject> Danielsubjects = new ArrayList<>();
        DanielConsts[2] = new Constrains(8,10);
        DanielConsts[3] = new Constrains(10,16);
        DanielConsts[4] = new Constrains(15,16);
        Daniel.setConstrains(DanielConsts);
        Danielsubjects.add(new Subject("Life", classes));
        Daniel.setSubjects(Danielsubjects);
        RetSchool.addTeacher(Daniel);

        class1.initSchedule();
        
        //Create the Subjects of the Classes
        Subject c0s0 = new Subject(Guy.getSubjects().get(0));//class 0 subject 0 
        c0s0.setTeacherName(Guy.getName());
        c0s0.setWeaklyHours(2);        
        class1.addSubject(c0s0);

        Subject c0s1 = new Subject(Guy.getSubjects().get(01));
        c0s1.setTeacherName(Guy.getName());
        c0s1.setWeaklyHours(3);
        class1.addSubject(c0s1);

        Subject c0s2 = new Subject(Guy.getSubjects().get(2));
        c0s2.setTeacherName(Guy.getName());
        c0s2.setWeaklyHours(3);
        class1.addSubject(c0s2);

        Subject c0s3 = new Subject(ilan.getSubjects().get(0));
        c0s3.setTeacherName(ilan.getName());
        c0s3.setWeaklyHours(3);
        class1.addSubject(c0s3);

        Subject c0s4 = new Subject(ilan.getSubjects().get(1));
        c0s4.setTeacherName(ilan.getName());
        c0s4.setWeaklyHours(6);
        class1.addSubject(c0s4);

        Subject c0s5 = new Subject(trabelsy.getSubjects().get(0));
        c0s5.setTeacherName(trabelsy.getName());
        c0s5.setWeaklyHours(6);
        class1.addSubject(c0s5);

        Subject c0s6 = new Subject(trabelsy.getSubjects().get(1));
        c0s6.setTeacherName(trabelsy.getName());
        c0s6.setWeaklyHours(2);
        class1.addSubject(c0s6);

        Subject c0s7 = new Subject(mergi.getSubjects().get(0));
        c0s7.setTeacherName(mergi.getName());
        c0s7.setWeaklyHours(2);
        class1.addSubject(c0s7);

        Subject c0s8 = new Subject(Daniel.getSubjects().get(0));
        c0s8.setTeacherName(Daniel.getName());
        c0s8.setWeaklyHours(2);
        class1.addSubject(c0s8);

        Subject c0s9 = new Subject(Yaniv.getSubjects().get(1));
        c0s9.setTeacherName(Yaniv.getName());
        c0s9.setWeaklyHours(3);
        class1.addSubject(c0s9);

        Subject c0s10 = new Subject(kodesh.getSubjects().get(0));//class 0 subject 0 
        c0s10.setTeacherName(kodesh.getName());
        c0s10.setWeaklyHours(2);        
        class1.addSubject(c0s10);
        class1.initSchedule();

        Lesson s = new Lesson(Daniel.getSubjects().get(0).getSubjectName(),Daniel.getName(),3,12);
        class1.addLockedLesson(s);


        //add the class to the scool
        RetSchool.addclass(class1);



        Subject c1s0 = new Subject(ilan.getSubjects().get(2));
        c1s0.setTeacherName(ilan.getName());
        c1s0.setWeaklyHours(3);
        class2.addSubject(c1s0);

        Subject c1s1 = new Subject(Guy.getSubjects().get(2));
        c1s1.setTeacherName(Guy.getName());
        c1s1.setWeaklyHours(2);
        class2.addSubject(c1s1);

        Subject c1s2 = new Subject(Guy.getSubjects().get(0));
        c1s2.setTeacherName(Guy.getName());
        c1s2.setWeaklyHours(4);
        class2.addSubject(c1s2);

        Subject c1s3 = new Subject(ilan.getSubjects().get(0));
        c1s3.setTeacherName(ilan.getName());
        c1s3.setWeaklyHours(5);
        class2.addSubject(c1s3);

        Subject c1s4 = new Subject(trabelsy.getSubjects().get(1));
        c1s4.setTeacherName(trabelsy.getName());
        c1s4.setWeaklyHours(9);
        class2.addSubject(c1s4);

        Subject c1s5 = new Subject(Amsalem.getSubjects().get(0));
        c1s5.setTeacherName(Amsalem.getName());
        c1s5.setWeaklyHours(2);
        class2.addSubject(c1s5);

        Subject c1s6 = new Subject(Daniel.getSubjects().get(0));
        c1s6.setTeacherName(Daniel.getName());
        c1s6.setWeaklyHours(6);
        class2.addSubject(c1s6);

        Subject c1s7 = new Subject(Yaniv.getSubjects().get(0));
        c1s7.setTeacherName(Yaniv.getName());
        c1s7.setWeaklyHours(3);
        class2.addSubject(c1s7);

        Subject c1s8 = new Subject(Yaniv.getSubjects().get(2));
        c1s8.setTeacherName(Yaniv.getName());
        c1s8.setWeaklyHours(4);
        class2.addSubject(c1s8);

        class2.initSchedule();

        //add the secend class to the school
        RetSchool.addclass(class2);
        //System.out.println("Created this Schedule");
        //RetSchool.printSChool();
        return RetSchool;
    }

}
