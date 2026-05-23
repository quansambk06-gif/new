import java.util.*;

// 1. Lớp trừu tượng Room đại diện cho một phòng chung
abstract class Room {
    String code;
    int basePrice;
    abstract int totalPrice(int nights);
}

// 2. Lớp Single kế thừa từ Room
class Single extends Room {
    Single(String code, int basePrice) {
        this.code = code;
        this.basePrice = basePrice;
    }

    @Override
    int totalPrice(int nights) {
        return this.basePrice * nights;
    }
}

// 3. Lớp Double_ kế thừa từ Room
class Double_ extends Room {
    Double_(String code, int basePrice) {
        this.code = code;
        this.basePrice = basePrice;
    }

    @Override
    int totalPrice(int nights) {
        return this.basePrice * nights * 2;
    }
}

// 4. Lớp Suite kế thừa từ Room
class Suite extends Room {
    Suite(String code, int basePrice) {
        this.code = code;
        this.basePrice = basePrice;
    }

    @Override
    int totalPrice(int nights) {
        return this.basePrice * nights * 3 + 200000;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Đọc số lượng phòng N
        int n = sc.nextInt();
        
        // Tạo HashMap để quản lý phòng theo Mã phòng (Code)
        HashMap<String, Room> hotelMap = new HashMap<>();
        
        // Đọc thông tin N phòng
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String code = sc.next();
            int basePrice = sc.nextInt();
            
            // Đa hình: Tạo đối tượng cụ thể dựa vào loại phòng nhập vào
            if (type.equals("SINGLE")) {
                hotelMap.put(code, new Single(code, basePrice));
            } else if (type.equals("DOUBLE")) {
                hotelMap.put(code, new Double_(code, basePrice));
            } else if (type.equals("SUITE")) {
                hotelMap.put(code, new Suite(code, basePrice));
            }
        }
        
        // Đọc số lượng lệnh đặt phòng M
        int m = sc.nextInt();
        long totalRevenue = 0; // Dùng kiểu long đề phòng tổng doanh thu vượt giới hạn int
        
        // Xử lý M lệnh đặt phòng
        for (int i = 0; i < m; i++) {
            String code = sc.next();
            int nights = sc.nextInt();
            
            // Tìm phòng trong HashMap bằng mã code
            Room room = hotelMap.get(code);
            
            // Tính tiền của lệnh này
            int price = room.totalPrice(nights);
            totalRevenue += price; // Cộng dồn vào tổng doanh thu
            
            // In kết quả của lệnh đặt phòng hiện tại
            System.out.println(code + " " + price);
        }
        
        // In ra tổng doanh thu cuối cùng
        System.out.println("REVENUE " + totalRevenue);
        
        sc.close();
    }
}
//cau 2
import java.util.*;

// 1. Tạo lớp Student với các thuộc tính theo yêu cầu
class Student {
    String name;
    int age;
    double score;

    // Constructor để khởi tạo sinh viên
    Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int N = sc.nextInt();
        
        // 2. Tạo một ArrayList sử dụng Generics <Student> để quản lý danh sách
        ArrayList<Student> list = new ArrayList<>();
        
        // 3. Thêm N sinh viên từ bàn phím vào danh sách
        for (int i = 0; i < N; i++) {
            String name = sc.next();
            int age = sc.nextInt();
            double score = sc.nextDouble();
            list.add(new Student(name, age, score));
        }
        
        // Biến dùng để lưu sinh viên có điểm cao nhất
        Student bestStudent = null;
        
        // 4. Tìm sinh viên có điểm cao nhất
        for (Student s : list) {
            if (bestStudent == null || s.score > bestStudent.score) {
                bestStudent = s; // Cập nhật nếu tìm thấy điểm lớn hơn hẳn
            }
        }
        
        // 5. In ra thông tin sinh viên điểm cao nhất theo đúng định dạng mẫu
        if (bestStudent != null) {
            System.out.println("Name: " + bestStudent.name);
            System.out.println("Age: " + bestStudent.age);
            System.out.printf(Locale.US, "Score: %.2f\n", bestStudent.score);
        }
        
        sc.close();
    }
}
//cau 3
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        // --- BƯỚC 1: ĐỌC TỪ BÀN PHÍM VÀ GHI VÀO FILE DATA.TXT ---
        // Mở luồng ghi file hiệu năng cao BufferedWriter
        BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"));
        
        // Đọc liên tục cho đến khi hết dữ liệu (EOF)
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            bw.write(line);   // Ghi dòng văn bản vào file
            bw.newLine();     // Tự động thêm ký tự xuống dòng xuống file
        }
        bw.close(); // Ghi xong bắt buộc phải đóng luồng để dữ liệu lưu xuống đĩa
        
        // --- BƯỚC 2: ĐỌC LẠI FILE DATA.TXT VÀO ARRAYLIST ---
        // Mở luồng đọc file hiệu năng cao BufferedReader
        BufferedReader br = new BufferedReader(new FileReader("data.txt"));
        ArrayList<String> list = new ArrayList<>();
        String currentLine;
        
        // Đọc từng dòng một cho đến khi hết file (null)
        while ((currentLine = br.readLine()) != null) {
            list.add(currentLine); // Ném dòng vừa đọc được vào ArrayList
        }
        br.close(); // Đọc xong đóng luồng
        
        // --- BƯỚC 3: IN CÁC DÒNG THEO THỨ TỰ NGƯỢC ---
        // Vòng lặp chạy lùi từ vị trí cuối cùng (list.size() - 1) về vị trí đầu tiên (0)
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
        
        sc.close();
    }
}
//cau 4
import java.util.*;

public class Main {
    static final Object lock = new Object();
    static int turn = 0; // 0 = lượt Thread A, 1 = lượt Thread B
    static StringBuilder out = new StringBuilder();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        
        // 1. Định nghĩa công việc và tạo Thread A
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                synchronized (lock) {
                    // Nếu chưa đến lượt mình (turn != 0), cắm chìa khóa đợi ở đây
                    while (turn != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // Đến lượt: Ghi 'A' vào StringBuilder dùng chung
                    out.append('A');
                    turn = 1; // Nhường lượt cho Thread B
                    lock.notifyAll(); // Đánh thức Thread B dậy làm việc
                }
            }
        });

        // 2. Định nghĩa công việc và tạo Thread B
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                synchronized (lock) {
                    // Nếu chưa đến lượt mình (turn != 1), cắm chìa khóa đợi ở đây
                    while (turn != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // Đến lượt: Ghi 'B' vào StringBuilder dùng chung
                    out.append('B');
                    turn = 0; // Nhường lượt lại cho Thread A
                    lock.notifyAll(); // Đánh thức Thread A dậy làm việc
                }
            }
        });

        // 3. Kích hoạt cả 2 luồng cùng chạy ngầm song song
        threadA.start();
        threadB.start();

        // 4. Bắt hàm main dừng lại chờ cả 2 luồng hoàn thành xong nhiệm vụ
        threadA.join();
        threadB.join();

        // 5. In kết quả cuối cùng theo đúng yêu cầu đề bài
        System.out.println(out.toString());

        sc.close();
    }
}
//cau 5
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextDouble());
        }

        // --- BẮT ĐẦU PHẦN ĐIỀN THÊM CHO TODO ---
        
        // 1. Kiểm tra xem có ÍT NHẤT một điểm nào >= 9 hay không
        boolean anyGioi = list.stream().anyMatch(s -> s >= 9);
        System.out.println("ANY " + anyGioi);

        // 2. Kiểm tra xem có phải TẤT CẢ các điểm đều >= 5 hay không
        boolean allDat = list.stream().allMatch(s -> s >= 5);
        System.out.println("ALL " + allDat);

        // 3. Kiểm tra xem KHÔNG CÓ điểm nào bị ngoài khoảng [0, 10] hay không
        // Điều kiện ngoài khoảng: s < 0 hoặc s > 10
        boolean noneHopLe = list.stream().noneMatch(s -> s < 0 || s > 10);
        System.out.println("NONE " + noneHopLe);

        sc.close();
    }
}