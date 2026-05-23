import java.util.*;

class Subject {
    private String code;
    private int credit;
    private double score;

    Subject(String code, int credit, double score) {
        this.code = code; this.credit = credit; this.score = score;
    }

    String getCode() { return code; }
    int getCredit() { return credit; }
    double getScore() { return score; }

    double gradePoint() {
        if (this.score >= 8.5){
            return 4.0;
        }
        else if (this.score >= 7.0 ) {
            return 3.0;
        }
        else if (this.score >= 5.5 ) {
            return 2.0;
        }
        else if (this.score >= 4.0  ) {
            return 1.0;
        }
        else{
            return 0.0;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<Subject> list = new ArrayList<>();

        int totalCredits = 0;
        double totalWeightedPoints = 0.0;

        for (int i = 0; i < n; i++){
            String code = sc.next();
            int credit = sc.nextInt();
            double score = sc.nextDouble();
    
        Subject sub = new Subject(code, credit, score);
        list.add(sub);

        totalCredits += sub.getCredit();
        totalWeightedPoints += sub.getCredit() * sub.gradePoint();
        }
        for (Subject sub : list) {
            System.out.printf("%s %.1f\n", sub.getCode(), sub.gradePoint());
        }
        double gpa = totalWeightedPoints / totalCredits;
        System.out.printf("GPA %.2f\n", gpa);

        sc.close();
    }
}
//cau 2
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<String, Integer> cnt = new HashMap<>();
        cnt.put("PASS", 0);
        cnt.put("FAIL", 0);
        // đếm

        for (int i=0;i<n;i++){
            String name = sc.next();
            double score = sc.nextDouble();

            if (score >= 5.0){
                cnt.put("PASS", cnt.get("PASS")+1);
            }
            else {
                cnt.put("FAIL", cnt.get("FAIL") + 1);
            }
        }
        System.out.println("PASS " + cnt.get("PASS"));
        System.out.println("FAIL " + cnt.get("FAIL"));
    }
}
// cau 3
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        // --- BƯỚC 1: ĐỌC DỮ LIỆU VÀ GHI VÀO FILE NHỊ PHÂN ---
        // Mở luồng để ghi dữ liệu dạng nhị phân vào file tên là "ints.bin"
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("ints.bin"));
        
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            dos.writeInt(x); // Ghi số nguyên x dưới dạng mã nhị phân vào file
        }
        dos.close(); // Ghi xong thì phải đóng file lại
        
        // --- BƯỚC 2: MỞ FILE RA ĐỌC LẠI VÀ TÌM MIN / MAX ---
        // Mở luồng để đọc dữ liệu nhị phân từ file "ints.bin"
        DataInputStream dis = new DataInputStream(new FileInputStream("ints.bin"));
        
        // Khởi tạo min với giá trị lớn nhất có thể, max với giá trị nhỏ nhất có thể
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        // Dùng vòng lặp vô hạn kết hợp try-catch để đọc cho đến khi hết file
        try {
            while (true) {
                int value = dis.readInt(); // Đọc ra từng số nguyên một
                
                // So sánh để tìm min, max
                if (value < min) {
                    min = value;
                }
                if (value > max) {
                    max = value;
                }
            }
        } catch (EOFException e) {
            // Khi hàm readInt() đọc tới cuối file và không còn gì để đọc, 
            // Java sẽ tự động ném ra ngoại lệ EOFException (End Of File).
            // Chúng ta bắt ngoại lệ này để biết file đã đọc xong thành công.
        } finally {
            dis.close(); // Đảm bảo đóng luồng đọc dữ liệu
        }
        
        // --- BƯỚC 3: IN KẾT QUẢ ---
        System.out.println("min: " + min);
        System.out.println("max: " + max);
        
        sc.close();
    }
}
// cau 4
import java.util.*;

// 1. Tạo lớp Luồng kế thừa từ lớp Thread để tính tổng bình phương của một đoạn
class SquareThread extends Thread {
    private int[] arr;
    private int start, end;
    private long sum = 0; // Dùng kiểu long vì bình phương số lớn rất dễ bị tràn số int

    // Constructor để truyền mảng và đoạn cần tính vào cho luồng này phụ trách
    SquareThread(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    // Hàm run() chứa công việc mà luồng này sẽ tự động chạy khi kích hoạt
    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            sum += (long) arr[i] * arr[i]; // Ép kiểu sang long để nhân không bị lỗi
        }
    }

    // Hàm lấy ra kết quả sau khi luồng tính xong
    public long getSum() {
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Đọc dữ liệu đầu vào
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int K = sc.nextInt();
        
        // Tạo một mảng quản lý K luồng
        SquareThread[] threads = new SquareThread[K];
        
        // Tính toán kích thước mỗi đoạn mà 1 luồng cần xử lý
        int chunkSize = N / K;
        
        // Chia việc cho từng luồng
        for (int i = 0; i < K; i++) {
            int start = i * chunkSize;
            // Luồng cuối cùng ôm nốt phần dư nếu N không chia hết cho K
            int end = (i == K - 1) ? N : (start + chunkSize);
            
            threads[i] = new SquareThread(arr, start, end);
            threads[i].start(); // Lệnh start() sẽ kích hoạt hàm run() chạy ngầm song song
        }
        
        long totalSum = 0;
        
        // Chờ tất cả các luồng tính xong rồi gom kết quả lại
        for (int i = 0; i < K; i++) {
            try {
                threads[i].join(); // Lệnh join() bắt hàm main phải đợi luồng này làm xong mới được đi tiếp
                totalSum += threads[i].getSum(); // Cộng dồn kết quả
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // In kết quả cuối cùng
        System.out.println(totalSum);
        
        sc.close();
    }
}
// cau 5
import java.util.*;

// 1. Tạo lớp Luồng kế thừa từ lớp Thread để tính tổng bình phương của một đoạn
class SquareThread extends Thread {
    private int[] arr;
    private int start, end;
    private long sum = 0; // Dùng kiểu long vì bình phương số lớn rất dễ bị tràn số int

    // Constructor để truyền mảng và đoạn cần tính vào cho luồng này phụ trách
    SquareThread(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    // Hàm run() chứa công việc mà luồng này sẽ tự động chạy khi kích hoạt
    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            sum += (long) arr[i] * arr[i]; // Ép kiểu sang long để nhân không bị lỗi
        }
    }

    // Hàm lấy ra kết quả sau khi luồng tính xong
    public long getSum() {
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Đọc dữ liệu đầu vào
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int K = sc.nextInt();
        
        // Tạo một mảng quản lý K luồng
        SquareThread[] threads = new SquareThread[K];
        
        // Tính toán kích thước mỗi đoạn mà 1 luồng cần xử lý
        int chunkSize = N / K;
        
        // Chia việc cho từng luồng
        for (int i = 0; i < K; i++) {
            int start = i * chunkSize;
            // Luồng cuối cùng ôm nốt phần dư nếu N không chia hết cho K
            int end = (i == K - 1) ? N : (start + chunkSize);
            
            threads[i] = new SquareThread(arr, start, end);
            threads[i].start(); // Lệnh start() sẽ kích hoạt hàm run() chạy ngầm song song
        }
        
        long totalSum = 0;
        
        // Chờ tất cả các luồng tính xong rồi gom kết quả lại
        for (int i = 0; i < K; i++) {
            try {
                threads[i].join(); // Lệnh join() bắt hàm main phải đợi luồng này làm xong mới được đi tiếp
                totalSum += threads[i].getSum(); // Cộng dồn kết quả
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // In kết quả cuối cùng
        System.out.println(totalSum);
        
        sc.close();
    }
}