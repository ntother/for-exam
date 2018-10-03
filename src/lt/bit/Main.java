package lt.bit;

import lt.bit.beans.Grade;
import lt.bit.beans.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
        private static final SessionFactory FACTORY = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).addAnnotatedClass(Grade.class).buildSessionFactory();

    public static void main(String[] args) {
        List students = uzkrautiDuomenis();
        System.out.println(students);
        int vidurkis = kursoVidurkis(2017);
        System.out.println(vidurkis);
    }

    private static List uzkrautiDuomenis() {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            List studentsList = session.createQuery("from Student s order by s.surname asc, s.name asc").getResultList();
            session.getTransaction().commit();
            return studentsList;
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static int kursoVidurkis(int metai) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            List<Grade> gradeList = session.createQuery("Select g from Grade g where year(g.date) = " + metai, Grade.class).getResultList();
            int totalGradeSum = 0;
            int gradeCount = 0;
            if (gradeList.isEmpty()) {
                return 0;
            }
            for (Grade grade : gradeList) {
                totalGradeSum += grade.getGrade();
                gradeCount++;
            }
            int averageGrade = totalGradeSum / gradeCount;
            session.getTransaction().commit();
            return averageGrade;
        }
    }
}
