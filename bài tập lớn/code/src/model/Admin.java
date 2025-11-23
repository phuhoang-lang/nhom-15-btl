package model;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    public Admin(String id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public void manageUser(User user) {
        System.out.println("Admin đang kiểm tra tài khoản: " + user.getName());
    }

    @Override
    public void displayProfile() {
        System.out.println("Admin: " + name);
    }
}
