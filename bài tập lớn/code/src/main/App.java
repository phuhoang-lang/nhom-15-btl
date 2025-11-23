package main;

import model.*;
import java.util.*;

public class App {
    static Scanner sc = new Scanner(System.in);
    static SystemManager system = new SystemManager();
    static final String DATA_FILE = "data.dat";

    public static void main(String[] args) {
        // 🔹 Tự động đọc dữ liệu khi khởi động
        system.loadFromFile(DATA_FILE);

        int choice;
        while (true) {
            showMenu();
            System.out.print("Chọn chức năng: ");
            String input = sc.nextLine();
            if (input.isEmpty()) continue;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addTutor();
                case 3 -> createRequest();
                case 4 -> system.displayAllTutors();
                case 5 -> system.saveToFile(DATA_FILE);
                case 6 -> system.loadFromFile(DATA_FILE);
                case 0 -> exitProgram();
                default -> System.out.println("Chức năng không hợp lệ!");
            }
        }
    }

    public static void showMenu() {
        System.out.println("\n===== DNU TUTOR CONNECT =====");
        System.out.println("1. Thêm Student");
        System.out.println("2. Thêm Tutor");
        System.out.println("3. Tạo Request học");
        System.out.println("4. Xem danh sách Tutor");
        System.out.println("5. Lưu dữ liệu ra file");
        System.out.println("6. Đọc dữ liệu từ file");
        System.out.println("0. Thoát và lưu");
        System.out.println("==============================");
    }

    // ➤ Thêm student
    public static void addStudent() {
        System.out.print("Nhập ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Nhập tên: ");
        String name = sc.nextLine().trim();
        System.out.print("Nhập email: ");
        String email = sc.nextLine().trim();
        System.out.print("Nhập mật khẩu: ");
        String pass = sc.nextLine().trim();

        if (id.isEmpty() || name.isEmpty()) {
            System.out.println("Dữ liệu không hợp lệ!");
            return;
        }

        Student s = new Student(id, name, email, pass);
        system.addUser(s);
        System.out.println("Thêm Student thành công!");
    }

    // ➤ Thêm tutor
    public static void addTutor() {
        System.out.print("Nhập ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Nhập tên: ");
        String name = sc.nextLine().trim();
        System.out.print("Nhập email: ");
        String email = sc.nextLine().trim();
        System.out.print("Nhập mật khẩu: ");
        String pass = sc.nextLine().trim();

        System.out.print("Nhập môn dạy (ngăn cách bằng dấu phẩy, ví dụ: Toán,Lý,Hóa): ");
        String[] arr = sc.nextLine().split(",");
        List<String> subjects = new ArrayList<>();
        for (String s : arr) {
            if (!s.trim().isEmpty()) subjects.add(s.trim());
        }

        System.out.print("Nhập thời gian rảnh: ");
        String time = sc.nextLine().trim();
        System.out.print("Nhập học phí mỗi giờ (chỉ nhập số, ví dụ: 80000 hoặc 80k/h): ");
        String rateInput = sc.nextLine().toLowerCase().replace("k", "000").replace("/h", "").trim();

        double rate = 0;
        try {
            rate = Double.parseDouble(rateInput);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Học phí phải là số hợp lệ!");
            return;
        }

        Tutor t = new Tutor(id, name, email, pass, subjects, time, rate);
        system.addUser(t);
        System.out.println("Thêm Tutor thành công!");
    }

    // ➤ Tạo request học
    public static void createRequest() {
        System.out.print("Nhập ID Student: ");
        String id = sc.nextLine().trim();

        Student student = null;
        for (User u : system.getUsers()) {
            if (u instanceof Student && u.getId().equalsIgnoreCase(id)) {
                student = (Student) u;
                break;
            }
        }

        if (student == null) {
            System.out.println("Không tìm thấy Student!");
            return;
        }

        System.out.print("Nhập môn cần học: ");
        String sub = sc.nextLine();
        System.out.print("Mô tả yêu cầu: ");
        String des = sc.nextLine();
        System.out.print("Lịch mong muốn: ");
        String time = sc.nextLine();

        // Danh sách Tutor
        System.out.println("\nDanh sách Tutor hiện có:");
        system.displayAllTutors();
        System.out.print("Nhập ID Tutor muốn học cùng: ");
        String tid = sc.nextLine().trim();

        Tutor tutor = null;
        for (User u : system.getUsers()) {
            if (u instanceof Tutor && u.getId().equalsIgnoreCase(tid)) {
                tutor = (Tutor) u;
                break;
            }
        }

        if (tutor == null) {
            System.out.println("Không tìm thấy Tutor!");
            return;
        }

        Request req = new Request(sub, des, time, tutor);
        student.addRequest(req);
        system.addRequest(req);

        System.out.println("Tạo Request thành công!");
    }

    // ➤ Thoát chương trình
    public static void exitProgram() {
        system.saveToFile(DATA_FILE);
        System.out.println("Dữ liệu đã được lưu.");
        System.exit(0);
    }
}
