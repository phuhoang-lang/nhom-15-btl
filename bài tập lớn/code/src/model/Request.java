package model;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    private String subject;
    private String description;
    private String schedule;
    private Tutor assignedTutor;
    private boolean confirmed;

    public Request(String subject, String description, String schedule, Tutor assignedTutor) {
        this.subject = subject;
        this.description = description;
        this.schedule = schedule;
        this.assignedTutor = assignedTutor;
        this.confirmed = false;
    }

    public void confirm() {
        confirmed = true;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    @Override
    public String toString() {
        return "Request: " + subject + " - " + description + " (" + schedule + ")";
    }
}
