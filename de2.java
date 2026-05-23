public class de2 {
    
}
import java.time.LocalDate;
import java.util.Scanner;

// a. Lớp trừu tượng Employee
abstract class Employee {
    private String name;
    private LocalDate started;

    // Constructor nhận đầy đủ 2 thuộc tính
    public Employee(String name, LocalDate started) {
        this.name = name;
        this.started = started;
    }

    // Các phương thức getter/setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getStarted() { return started; }
    public void setStarted(LocalDate started) { this.started = started; }

    // Các phương thức trừu tượng
    public abstract void showInfo();
    public abstract double calcSalary();
}

// b.1. Lớp FullTimeEmployee kế thừa từ Employee
class FullTimeEmployee extends Employee {
    private double monthlySalary;
    private double bonus;

    // Constructor
    public FullTimeEmployee(String name, LocalDate started, double monthlySalary, double bonus) {
        super(name, started); // Gọi constructor của lớp cha (Employee)
        this.monthlySalary = monthlySalary;
        this.bonus = bonus;
    }

    @Override
    public double calcSalary() {
        return monthlySalary + bonus;
    }

    @Override
    public void showInfo() {
        System.out.println("=== Nhân viên Full-Time ===");
        System.out.println("Tên: " + getName());
        System.out.println("Ngày bắt đầu: " + getStarted());
        System.out.printf("Lương cơ bản: %.2f\n", monthlySalary);
        System.out.printf("Thưởng: %.2f\n", bonus);
        System.out.printf("Lương thực nhận: %.2f\n", calcSalary());
    }
}

// b.2. Lớp PartTimeEmployee kế thừa từ Employee
class PartTimeEmployee extends Employee {
    private int workingHour;
    private double rate;

    // Constructor
    public PartTimeEmployee(String name, LocalDate started, int workingHour, double rate) {
        super(name, started); // Gọi constructor của lớp cha (Employee)
        this.workingHour = workingHour;
        this.rate = rate;
    }

    @Override
    public double calcSalary() {
        return workingHour * rate;
    }

    @Override
    public void showInfo() {
        System.out.println("=== Nhân viên Part-Time ===");
        System.out.println("Tên: " + getName());
        System.out.println("Ngày bắt đầu: " + getStarted());
        System.out.println("Số giờ làm: " + workingHour);
        System.out.printf("Đơn giá/giờ: %.2f\n", rate);
        System.out.printf("Lương thực nhận: %.2f\n", calcSalary());
    }
}

// c. Phương thức main để chạy chương trình
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Đọc thông tin Full-time employee
        String ftName = sc.nextLine();
        LocalDate ftStarted = LocalDate.parse(sc.nextLine());
        double ftSalary = sc.nextDouble();
        double ftBonus = sc.nextDouble();
        sc.nextLine(); // Xóa bộ nhớ đệm (clear buffer) sau khi đọc số

        // Đọc thông tin Part-time employee
        String ptName = sc.nextLine();
        LocalDate ptStarted = LocalDate.parse(sc.nextLine());
        int ptHour = sc.nextInt();
        double ptRate = sc.nextDouble();

        // Tạo 2 đối tượng nhân viên cụ thể
        FullTimeEmployee ftEmp = new FullTimeEmployee(ftName, ftStarted, ftSalary, ftBonus);
        PartTimeEmployee ptEmp = new PartTimeEmployee(ptName, ptStarted, ptHour, ptRate);

        // Gọi phương thức hiển thị thông tin
        ftEmp.showInfo();
        System.out.println(); // In ra một dòng trống phân tách giữa 2 nhân viên theo mẫu đề bài
        ptEmp.showInfo();

        sc.close();
    }
}
//cau 2
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. Đọc số lượng phần tử N
        int n = sc.nextInt();
        
        // 2. Tạo một ArrayList để chứa các số nguyên
        ArrayList<Integer> a = new ArrayList<>();
        
        // 3. Dùng vòng lặp để đọc N số nguyên từ bàn phím và thêm vào danh sách
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }
        
        // 4. Sử dụng công cụ có sẵn để đảo ngược toàn bộ danh sách
        Collections.reverse(a);
        
        // 5. In các số sau khi đảo ngược ra màn hình, cách nhau bởi khoảng trắng
        for (int i = 0; i < a.size(); i++) {
            System.out.print(a.get(i));
            if (i < a.size() - 1) {
                System.out.print(" "); // Thêm khoảng trắng giữa các số
            }
        }
        
        sc.close();
    }
}
// cau 3
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        // 1. Đọc số lượng số thực N
        int n = sc.nextInt();
        
        // --- BƯỚC 1: GHI SỐ THỰC VÀO FILE NHỊ PHÂN ---
        // Mở luồng ghi vào file "double.dat" theo đúng yêu cầu đề bài
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("double.dat"));
        
        for (int i = 0; i < n; i++) {
            double x = sc.nextDouble();
            dos.writeDouble(x); // Ghi số thực x dạng nhị phân vào file
        }
        dos.close(); // Ghi xong bắt buộc phải đóng file
        
        // --- BƯỚC 2: ĐỌC FILE VÀ TÍNH TRUNG BÌNH CỘNG ---
        DataInputStream dis = new DataInputStream(new FileInputStream("double.dat"));
        
        double sum = 0.0;
        int count = 0;
        
        try {
            while (true) {
                double value = dis.readDouble(); // Đọc ra từng số thực một
                sum += value; // Cộng dồn vào tổng
                count++;      // Tăng biến đếm số lượng số đã đọc
            }
        } catch (EOFException e) {
            // Khi đọc hết sạch dữ liệu trong file, Java nhảy vào đây để dừng lại an toàn
        } finally {
            dis.close(); // Đảm bảo đóng luồng đọc file
        }
        
        // --- BƯỚC 3: TÍNH VÀ IN KẾT QUẢ ---
        double average = sum / count;
        // In ra theo đúng định dạng "Average: <giá trị làm tròn 2 chữ số thập phân>"
        System.out.printf("Average: %.2f\n", average);
        
        sc.close();
    }
}

//cau 4
import java.util.*;

// 1. Lớp EvenSumThread đảm nhận việc tính tổng số chẵn của một đoạn mảng
class EvenSumThread extends Thread {
    private int[] arr;
    private int start, end;
    private long sum = 0; // Dùng long để tránh bị tràn số khi cộng dồn các số lớn

    // Constructor để nhận mảng và phạm vi đoạn cần tính
    EvenSumThread(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    // Hành động xử lý của luồng
    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            // Kiểm tra điều kiện số chẵn (áp dụng đúng cho cả số âm và số dương)
            if (arr[i] % 2 == 0) {
                sum += arr[i];
            }
        }
    }

    // Hàm lấy kết quả tổng của luồng này
    public long getSum() {
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Đọc dữ liệu mảng
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        
        // Đọc số lượng luồng K
        int K = sc.nextInt();
        
        // Khởi tạo mảng quản lý K luồng
        EvenSumThread[] threads = new EvenSumThread[K];
        int chunkSize = N / K;
        
        // Chia mảng cho từng luồng và kích hoạt chạy song song
        for (int i = 0; i < K; i++) {
            int start = i * chunkSize;
            // Luồng cuối cùng gánh nốt phần dư nếu N không chia hết cho K
            int end = (i == K - 1) ? N : (start + chunkSize);
            
            threads[i] = new EvenSumThread(arr, start, end);
            threads[i].start(); // Kích hoạt luồng chạy ngầm
        }
        
        long totalSum = 0;
        
        // Chờ tất cả các luồng làm việc xong rồi thu hoạch kết quả
        for (int i = 0; i < K; i++) {
            try {
                threads[i].join(); // Bắt hàm main dừng lại đợi luồng i tính xong
                totalSum += threads[i].getSum(); // Cộng dồn kết quả vào tổng chung
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // In ra tổng cuối cùng
        System.out.println(totalSum);
        
        sc.close();
    }
}
//cau 5
import java.util.*;

// 1. Lớp EvenSumThread đảm nhận việc tính tổng số chẵn của một đoạn mảng
class EvenSumThread extends Thread {
    private int[] arr;
    private int start, end;
    private long sum = 0; // Dùng long để tránh bị tràn số khi cộng dồn các số lớn

    // Constructor để nhận mảng và phạm vi đoạn cần tính
    EvenSumThread(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    // Hành động xử lý của luồng
    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            // Kiểm tra điều kiện số chẵn (áp dụng đúng cho cả số âm và số dương)
            if (arr[i] % 2 == 0) {
                sum += arr[i];
            }
        }
    }

    // Hàm lấy kết quả tổng của luồng này
    public long getSum() {
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Đọc dữ liệu mảng
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        
        // Đọc số lượng luồng K
        int K = sc.nextInt();
        
        // Khởi tạo mảng quản lý K luồng
        EvenSumThread[] threads = new EvenSumThread[K];
        int chunkSize = N / K;
        
        // Chia mảng cho từng luồng và kích hoạt chạy song song
        for (int i = 0; i < K; i++) {
            int start = i * chunkSize;
            // Luồng cuối cùng gánh nốt phần dư nếu N không chia hết cho K
            int end = (i == K - 1) ? N : (start + chunkSize);
            
            threads[i] = new EvenSumThread(arr, start, end);
            threads[i].start(); // Kích hoạt luồng chạy ngầm
        }
        
        long totalSum = 0;
        
        // Chờ tất cả các luồng làm việc xong rồi thu hoạch kết quả
        for (int i = 0; i < K; i++) {
            try {
                threads[i].join(); // Bắt hàm main dừng lại đợi luồng i tính xong
                totalSum += threads[i].getSum(); // Cộng dồn kết quả vào tổng chung
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // In ra tổng cuối cùng
        System.out.println(totalSum);
        
        sc.close();
    }
}