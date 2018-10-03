package lt.bit.beans;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pazymiai", schema = "bandomasis")
public class Grade {
    private Integer id;
    private Integer grade;
    private Student student;
    private LocalDate date;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pazymis")
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer pazymis) {
        this.grade = pazymis;
    }

    @Basic
    @Column(name = "data")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "studentas_id", referencedColumnName = "id", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student studentaiByStudentasId) {
        this.student = studentaiByStudentasId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return id == grade.id &&
                this.grade == grade.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grade);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", grade=" + grade +
                ", date=" + date +
                '}';
    }
}
