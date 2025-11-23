package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Request> requests;

    public Student(String id, String name, String email, String password) {
        super(id, name, email, password);
        this.requests = new ArrayList<>();
    }

    public void addRequest(Request req) {
        requests.add(req);
    }

    public List<Request> getRequests() {
        return requests;
    }

    @Override
    public void displayProfile() {
        System.out.println("Student: " + name + " (" + email + ")");
    }
}
