package model;

import java.io.Serializable;

public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    private Student student;
    private Tutor tutor;
    private String comment;
    private int rating;

    public Review(Student student, Tutor tutor, String comment, int rating) {
        this.student = student;
        this.tutor = tutor;
        this.comment = comment;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review from " + student.getName() + " -> " + tutor.getName() +
                ": " + comment + " (" + rating + "/5)";
    }
}
