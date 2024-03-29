package pt.ulisboa.tecnico.socialsoftware.tutor.dashboard.domain;

import pt.ulisboa.tecnico.socialsoftware.tutor.exceptions.ErrorMessage;
import pt.ulisboa.tecnico.socialsoftware.tutor.exceptions.TutorException;
import pt.ulisboa.tecnico.socialsoftware.tutor.impexp.domain.DomainEntity;
import pt.ulisboa.tecnico.socialsoftware.tutor.impexp.domain.Visitor;

import pt.ulisboa.tecnico.socialsoftware.tutor.user.domain.Student;
import pt.ulisboa.tecnico.socialsoftware.tutor.utils.DateHandler;
import pt.ulisboa.tecnico.socialsoftware.tutor.execution.domain.CourseExecution;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.*;

@Entity
public class Dashboard implements DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime lastCheckDifficultQuestions;

    @ManyToOne
    private CourseExecution courseExecution;

    @ManyToOne
    private Student student;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dashboard", orphanRemoval = true)
    private Set<DifficultQuestion> difficultQuestions = new HashSet<>();

    public Dashboard() {
    }

    public Dashboard(CourseExecution courseExecution, Student student) {
        setCourseExecution(courseExecution);
        setStudent(student);
    }

    public void remove() {
        student.getDashboards().remove(this);
        student = null;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getLastCheckDifficultQuestions() {
        return lastCheckDifficultQuestions;
    }

    public void setLastCheckDifficultQuestions(LocalDateTime lastCheckDifficultAnswers) {
        this.lastCheckDifficultQuestions = lastCheckDifficultAnswers;
    }

    public CourseExecution getCourseExecution() {
        return courseExecution;
    }

    public void setCourseExecution(CourseExecution courseExecution) {
        this.courseExecution = courseExecution;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        this.student.addDashboard(this);
    }

    public Set<DifficultQuestion> getDifficultQuestions() {
        return difficultQuestions;
    }

    public void setDifficultQuestions(Set<DifficultQuestion> difficultQuestions) {
        this.difficultQuestions = difficultQuestions;
    }

    public void addDifficultQuestion(DifficultQuestion difficultQuestion) {
        if (difficultQuestions.stream()
                .anyMatch(difficultQuestion1 -> difficultQuestion1.getQuestion() == difficultQuestion.getQuestion())) {
            throw new TutorException(ErrorMessage.DIFFICULT_QUESTION_ALREADY_CREATED);
        }
        difficultQuestions.add(difficultQuestion);
    }

    public void accept(Visitor visitor) {
    }

    @Override
    public String toString() {
        return "Dashboard{" +
                "id=" + id +
                ", lastCheckDifficultAnswers=" + lastCheckDifficultQuestions +
                "}";
    }

}