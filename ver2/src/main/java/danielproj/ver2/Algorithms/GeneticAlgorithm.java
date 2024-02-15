package danielproj.ver2.Algorithms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import java.util.Random;
import java.util.Set;

import danielproj.ver2.objects.Schedule;
import danielproj.ver2.objects.School;
import danielproj.ver2.objects.Subject;

//TODO finish the secend milestone

public class GeneticAlgorithm {
    
    private static final int POPULATION_SIZE = 250;
    private static int MAX_GENERATIONS = 400;
    private static double MUTATION_RATE = 0.55;
    private static double BIGMUTATION_RATE = 0.17;  
    private int CrossOverPrecent = 75; //what precentage of the population will crossover

   // TODO add that if a teacher teaches 2 classes in 2 days he 
   // TODO wont see all each classes in each day but seperatad and exe...

    
    
    
   
    public School Geneticalgorithm(School school , String ClassName) {
        int count = 0;
        //adds empty subject for every class
        List<School> population = initializePopulationByClass(school,ClassName);
        List<Integer> SortedIndexes = new ArrayList<>();
        // Evolution
        for (int generation = 1; generation <= MAX_GENERATIONS; generation++) {
            // Evaluate fitness
            List<Integer> Fitnesses = new ArrayList<>();
            for (School scl : population) {
                Fitnesses.add(scl.evaluateSchoolClass(ClassName));
            }

            List<School> newPopulation = new ArrayList<>();

            // Tournament selection for parents
            for (int i = 0; i < POPULATION_SIZE; i++) {
                if (i < (CrossOverPrecent * (POPULATION_SIZE / 100))) {
                    SortedIndexes = getSortedIndexes(Fitnesses);
                    School parent1 =new School();
                    parent1.tournamentSelection(population, Fitnesses,SortedIndexes);
                    School parent2 = new School();
                    parent2.tournamentSelection(population, Fitnesses,SortedIndexes);
                    while (parent2.equals(parent1)) {
                        parent2.tournamentSelection(population, Fitnesses,SortedIndexes);
                    }

                    // Perform crossover and mutation to generate new population
                    School child = new School(parent1);
                    child.crossover(parent1, parent2,ClassName);

                    Random rand = new Random();
                    double randomNumber = rand.nextInt(101) / 100.0;
                    //count how many diffrent Evauations there is in the population
                    // int Diversity = calculateDiversity(Evaluations);

                    // if(Diversity < POPULATION_SIZE/5)
                    // {
                    //     SUPERMUTATION_RATE += 0.02;
                    //     MUTATION_RATE += 0.05;
                    // }
                    // else if(Diversity < POPULATION_SIZE/10)
                    // {
                    //     SUPERMUTATION_RATE += 0.01;
                    //     MUTATION_RATE += 0.04;
                    // }
                    // else if(Diversity < POPULATION_SIZE/25)
                    // {
                    //     MUTATION_RATE += 0.15;
                    // }
                    // else if(Diversity < POPULATION_SIZE/50)
                    // {
                    //     MUTATION_RATE += 0.1;
                    // }
                    // else if(Diversity > POPULATION_SIZE/2)
                    // {
                    //     SUPERMUTATION_RATE -= 0.01;
                    //     MUTATION_RATE += 0.05;
                    // }
                    

                    
                    if (randomNumber < BIGMUTATION_RATE) {
                        child.BigMuate(ClassName, child.getClasses().get(child.getClassIndexByName(ClassName)).getSubjects());
                    }
                    else if (randomNumber < MUTATION_RATE) {
                        child.mutate(ClassName, child.getClasses().get(child.getClassIndexByName(ClassName)).getSubjects()); 
                    }

                    newPopulation.add(child);
                } 
                else {

                    int in = i % (CrossOverPrecent * (POPULATION_SIZE / 100));
                    newPopulation.add(population.get(SortedIndexes.get(in)));

                }
                // if(generation == 25&&i==1||generation==30&&i==1||generation==35&&i==1||generation==36&&i==1||generation==37&&i==1)
                // {
                //     String s = "Evals : ";
                //         s+="[";
                //     for(int Eval : Evaluations)
                //     {
                //         s+=Eval+",";
                //     }
                //     s+="]";
                //     System.out.println(s);
                // }
                // System.out.println(
                // "=========================================================================generation
                // : " + i
                // + "=======================");
                // int bestIndex = Evaluations.indexOf(Collections.max(Evaluations));
                // population.get(bestIndex).printScheduleteachers();
                // System.out.println(
                // "==============+=====================++===========================+++====================================++++================");
            }

            // Replace old population with new population
            population = newPopulation;
           

            // Output best schedule in this generation
            int bestIndex = Fitnesses.indexOf(Collections.max(Fitnesses));
            if(generation == MAX_GENERATIONS)
            {
                if( Fitnesses.get(bestIndex) <= 70 && count < 100)
                {
                    MAX_GENERATIONS +=1;
                    count += 1;

                }
            }
            // population.get(bestIndex).printSchedule();
            System.out.println("Generation " + generation + ": Best Fitness : " + Fitnesses.get(bestIndex)+ "\t"+"diaversity : "+calculateDiversity(Fitnesses) +  "Avrage Fitnnes : "+claculateavragefittnes(Fitnesses));
            // System.out.println("Generation " + generation + ": Best Fitness : " + Evaluations.get(bestIndex)+" Mutation Rate " + MUTATION_RATE+" SuperMutation Rate : "+SUPERMUTATION_RATE + "DIaversity : "+calculateDiversity(Evaluations));
            // population.get(bestIndex).printScheduleteachers();
        }

        // Output final best schedule
        int bestIndex = evaluateBestScheduleIndex(population, ClassName);
        population.get(bestIndex).printSChool();
        int v = population.get(bestIndex).evaluateSchoolClass(ClassName);
        System.out.println("Fittnes ; " + v);
        System.out.println("Mutation R" + MUTATION_RATE);
        System.out.println("SuperMutation R"+BIGMUTATION_RATE);
        return population.get(bestIndex);
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

    public int claculateavragefittnes(List<Integer> fitnesses)
    {
        int avrage = 0;
        for(int value : fitnesses)
        {
            avrage += value;
        }
        avrage = avrage / fitnesses.size();
        //TODO finish:
        return avrage;
    }

    public int calculateDiversity(List<Integer> list) {
        // Use a Set to automatically eliminate duplicates
        Set<Integer> uniqueElements = new HashSet<>(list);
        
        // Return the size of the Set, which represents the number of unique elements
        return uniqueElements.size();
    }

    private List<School> initializePopulationByClass(School school,String ClassName) {
        List<School> Population = new ArrayList<>();
        List<Subject> Subjects = new ArrayList<>();
        
        for(int i = 0 ; i < POPULATION_SIZE ; i++)
        {
            School s = new School(school);
            for(int j = 0 ; j <school.getClasses().size();j++)
            {
                if(school.getClasses().get(j).getClassName().equals(ClassName))
                {
                Schedule schedule = new Schedule();
                Subjects = school.getClasses().get(j).getSubjects();
                schedule.InitSchedule();
                schedule.FillThisScheduleWithLessons(Subjects);
                
                s.getClasses().get(j).setSchedule(schedule);
                Population.add(s);
                }
            }
        }
        return Population;
    }

    private int evaluateBestScheduleIndex(List<School> population ,String ClassName) {
        List<Integer> fitnessValues = new ArrayList<>();
        for (School Schedule : population) {
            fitnessValues.add(Schedule.evaluateSchoolClass(ClassName));
        }
        return fitnessValues.indexOf(Collections.max(fitnessValues));
    }


    
}
