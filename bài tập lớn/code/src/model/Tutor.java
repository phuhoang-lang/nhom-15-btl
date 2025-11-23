package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tutor extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> subjects;
    private String availableTime;
    private double hourlyRate;
    private List<Review> reviews;

    public Tutor(String id, String name, String email, String password,
                 List<String> subjects, String availableTime, double hourlyRate) {
        super(id, name, email, password);
        this.subjects = subjects;
        this.availableTime = availableTime;
        this.hourlyRate = hourlyRate;
        this.reviews = new ArrayList<>();
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<String> getSubjects() { return subjects; }

    @Override
    public void displayProfile() {
        System.out.println("Tutor: " + name + " | Môn: " + subjects + " | " + hourlyRate + "đ/h");
    }
}
