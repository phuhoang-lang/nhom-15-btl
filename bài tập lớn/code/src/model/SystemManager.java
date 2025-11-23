package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SystemManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<User> users;
    private List<Request> requests;

    public SystemManager() {
        users = new ArrayList<>();
        requests = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    public void displayAllTutors() {
        System.out.println("Danh sách gia sư:");
        for (User u : users) {
            if (u instanceof Tutor) {
                u.displayProfile();
            }
        }
    }

    public List<User> getUsers() {
        return users;
    }

    // 💾 Lưu dữ liệu ra file
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(users);
            oos.writeObject(requests);
            System.out.println("Dữ liệu đã được lưu vào file: " + fileName);
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu dữ liệu: " + e.getMessage());
        }
    }

    // 📂 Đọc dữ liệu từ file
    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            users = (List<User>) ois.readObject();
            requests = (List<Request>) ois.readObject();
            System.out.println("Dữ liệu đã được đọc từ file: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("⚠Chưa có dữ liệu cũ (file chưa tồn tại).");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }
    }
}
