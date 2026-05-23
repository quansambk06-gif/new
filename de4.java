import java.util.*;

class Account {
    String id;
    double balance;

    Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    // 1. Hoàn thiện phương thức nạp tiền vào tài khoản
    void deposit(double amt) {
        this.balance += amt;
    }

    double getBalance() {
        return balance;
    }
}

class SavingsAccount extends Account {
    double rate;

    SavingsAccount(String id, double balance, double rate) {
        super(id, balance); // Gọi constructor của lớp cha Account
        this.rate = rate;
    }

    // 2. Hoàn thiện phương thức tính lãi kép: balance * (1 + rate)^years
    double balanceAfterYears(int years) {
        return this.balance * Math.pow(1 + this.rate, years);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Đọc số lượng tài khoản N
        int n = sc.nextInt();
        
        // Sử dụng HashMap để quản lý tài khoản theo dạng: <Mã_ID, Đối_tượng_tài_khoản>
        HashMap<String, SavingsAccount> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            String id = sc.next();
            double balance = sc.nextDouble();
            double rate = sc.nextDouble();
            
            SavingsAccount acc = new SavingsAccount(id, balance, rate);
            map.put(id, acc); // Cất tài khoản vào HashMap để tìm kiếm bằng ID cho nhanh
        }
        
        // Đọc số lượng lệnh M
        int m = sc.nextInt();
        
        // Xử lý từng lệnh
        for (int i = 0; i < m; i++) {
            String command = sc.next();
            
            if (command.equals("DEPOSIT")) {
                String id = sc.next();
                double amt = sc.nextDouble();
                
                // Lấy tài khoản ra từ Map bằng ID
                SavingsAccount acc = map.get(id);
                acc.deposit(amt); // Thực hiện cộng tiền
                
                // In kết quả sau khi nạp tiền thành công
                System.out.printf(Locale.US, "%s %.2f\n", acc.id, acc.getBalance());
                
            } else if (command.equals("FORECAST")) {
                String id = sc.next();
                int years = sc.nextInt();
                
                // Lấy tài khoản ra từ Map bằng ID
                SavingsAccount acc = map.get(id);
                
                // Tính lãi kép dự đoán (Lệnh này chỉ xem trước, không làm thay đổi số dư thực tế)
                double futureBalance = acc.balanceAfterYears(years);
                System.out.printf(Locale.US, "%s %.2f\n", acc.id, futureBalance);
            }
        }
        
        sc.close();
    }
}
//cau 2
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        HashMap<String, Integer> map = new HashMap<>();
        
        // 1. Đọc dữ liệu và nạp vào HashMap
        for (int i = 0; i < n; i++) {
            String key = sc.next();
            int value = sc.nextInt();
            map.put(key, value);
        }
        
        // Biến dùng để lưu kết quả "tốt nhất" tìm được
        String bestKey = null;
        int maxValue = Integer.MIN_VALUE; // Khởi tạo max với giá trị nhỏ nhất có thể
        
        // 2. Duyệt qua toàn bộ các cặp dữ liệu bằng entrySet()
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String currentKey = entry.getKey();
            int currentValue = entry.getValue();
            
            // Trường hợp 1: Tìm thấy value lớn hơn hẳn maxValue hiện tại
            if (currentValue > maxValue) {
                maxValue = currentValue;
                bestKey = currentKey;
            } 
            // Trường hợp 2: Điểm số bằng nhau (Hòa) -> So sánh Alphabet bằng compareTo
            else if (currentValue == maxValue) {
                // currentKey.compareTo(bestKey) < 0 nghĩa là currentKey đứng trước trong từ điển
                if (currentKey.compareTo(bestKey) < 0) {
                    bestKey = currentKey;
                }
            }
        }
        
        // 3. In ra kết quả cuối cùng theo đúng định dạng
        System.out.println(bestKey + " " + maxValue);
        
        sc.close();
    }
}
//cau 3
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        // Đọc chunkSize và số lượng byte N
        int chunkSize = Integer.parseInt(sc.nextLine().trim());
        int n = Integer.parseInt(sc.nextLine().trim());
        
        // --- BƯỚC 1: GHI FILE BIG.BIN ---
        FileOutputStream fosBig = new FileOutputStream("big.bin");
        for (int i = 0; i < n; i++) {
            int b = sc.nextInt();
            fosBig.write(b); // Ghi từng byte (0..255) vào file tổng
        }
        fosBig.close();
        
        // --- BƯỚC 2: TÁCH FILE THÀNH CÁC CHUNK_I.BIN ---
        FileInputStream fisBig = new FileInputStream("big.bin");
        byte[] buffer = new byte[chunkSize];
        int bytesRead;
        int chunkIndex = 0;
        
        // Đọc liên tục từ file tổng vào mảng đệm buffer với kích thước chunkSize
        while ((bytesRead = fisBig.read(buffer)) != -1) {
            chunkIndex++; // Tăng chỉ số file chunk (bắt đầu từ 1)
            String chunkName = "chunk_" + chunkIndex + ".bin";
            
            // Mở file output tương ứng cho chunk này và ghi đúng số byte thực tế đọc được
            FileOutputStream fosChunk = new FileOutputStream(chunkName);
            fosChunk.write(buffer, 0, bytesRead);
            fosChunk.close(); // Đóng stream ngay sau khi ghi xong chunk này
        }
        fisBig.close();
        
        // --- BƯỚC 3: ĐỌC LẠI TỪNG CHUNK ĐỂ KIỂM TRA VÀ TÍNH TỔNG ---
        int totalBytesCount = 0;
        long totalBytesSum = 0; // Dùng long đề phòng tổng giá trị vượt giới hạn int
        
        for (int i = 1; i <= chunkIndex; i++) {
            File chunkFile = new File("chunk_" + i + ".bin");
            if (chunkFile.exists()) {
                FileInputStream fisChunk = new FileInputStream(chunkFile);
                int b;
                // Đọc từng byte đơn lẻ từ file chunk này
                while ((b = fisChunk.read()) != -1) {
                    totalBytesCount++;
                    totalBytesSum += b; // Cộng dồn giá trị unsigned (0..255)
                }
                fisChunk.close();
            }
        }
        
        // --- BƯỚC 4: IN KẾT QUẢ THEO MẪU ĐỀ BÀI ---
        System.out.println("chunks: " + chunkIndex);
        System.out.println("bytes: " + totalBytesCount);
        System.out.println("sum: " + totalBytesSum);
        
        sc.close();
    }
}
//cau 4
import java.util.*;

// 1. Định nghĩa lớp VarThread kế thừa từ Thread
class VarThread extends Thread {
    int[] arr; 
    int from, to;
    long localSum = 0, localSumSq = 0; // Dùng kiểu long để tránh tràn số khi cộng dồn số lớn

    // Constructor để nhận mảng và phạm vi đoạn xử lý
    VarThread(int[] arr, int from, int to) {
        this.arr = arr;
        this.from = from;
        this.to = to;
    }

    // Hàm run() thực hiện tính toán trên đoạn được giao
    @Override
    public void run() {
        for (int i = from; i < to; i++) {
            long val = arr[i];
            localSum += val;         // Cộng dồn vào tổng các số
            localSumSq += val * val; // Cộng dồn vào tổng bình phương các số
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int k = sc.nextInt();

        // 2. Tạo mảng quản lý K luồng
        VarThread[] threads = new VarThread[k];
        int chunkSize = n / k;

        // 3. Phân chia đoạn công việc và kích hoạt các luồng chạy song song
        for (int i = 0; i < k; i++) {
            int from = i * chunkSize;
            // Luồng cuối gánh nốt phần dư nếu n không chia hết cho k
            int to = (i == k - 1) ? n : (from + chunkSize);

            threads[i] = new VarThread(arr, from, to);
            threads[i].start(); // Kích hoạt luồng chạy ngầm
        }

        // Khởi tạo các biến tổng chung của cả mảng
        long totalSum = 0;
        long totalSumSq = 0;

        // 4. Chờ tất cả các luồng làm việc xong và gom kết quả lại
        for (int i = 0; i < k; i++) {
            threads[i].join(); // Đợi luồng i hoàn thành công việc
            
            totalSum += threads[i].localSum;     // Gom tổng các số
            totalSumSq += threads[i].localSumSq; // Gom tổng bình phương
        }

        // 5. Áp dụng công thức tính phương sai (Dùng kiểu double để tính toán số thập phân)
        double meanSq = (double) totalSumSq / n;
        double mean = (double) totalSum / n;
        double variance = meanSq - (mean * mean);

        // 6. In kết quả với đúng 4 chữ số thập phân (Locale.US để hiển thị dấu chấm)
        System.out.printf(Locale.US, "%.4f\n", variance);

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
        List<String> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            if (s.equals("_")) s = "";
            list.add(s);
        }
        
        // --- PHẦN CODE XỬ LÝ STREAM THEO YÊU CẦU ---
        // 1. Chuyển list thành stream
        // 2. Lọc bỏ các chuỗi rỗng bằng !s.isEmpty()
        // 3. Nối các chuỗi lại với nhau bằng dấu phẩy thông qua Collectors.joining(",")
        String result = list.stream()
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.joining(","));
        
        // 4. In ra chuỗi kết quả cuối cùng
        System.out.println(result);
        
        sc.close();
    }
}