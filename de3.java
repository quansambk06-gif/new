import java.util.*;

class MyDate {
    int day, month, year;

    MyDate(int d, int m, int y) {
        this.day = d;
        this.month = m;
        this.year = y;
    }

    // Kiểm tra năm nhuận theo quy tắc đề bài
    private boolean isLeapYear(int y) {
        return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
    }

    // Trả về số ngày của một tháng trong năm cụ thể
    private int getDaysInMonth(int m, int y) {
        switch (m) {
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(y) ? 29 : 28;
            default:
                return 31;
        }
    }

    // Đổi ngày/tháng/năm hiện tại thành số ngày tích lũy tuyệt đối tính từ năm 0
    private int toAbsoluteDays() {
        int totalDays = this.day;
        
        // Cộng dồn ngày của các năm trước đó
        for (int y = 1; y < this.year; y++) {
            totalDays += isLeapYear(y) ? 366 : 365;
        }
        
        // Cộng dồn ngày của các tháng trước đó trong năm hiện tại
        for (int m = 1; m < this.month; m++) {
            totalDays += getDaysInMonth(m, this.year);
        }
        
        return totalDays;
    }

    // Phương thức so sánh 2 mốc thời gian
    int compareTo(MyDate o) {
        if (this.year != o.year) {
            return this.year > o.year ? 1 : -1;
        }
        if (this.month != o.month) {
            return this.month > o.month ? 1 : -1;
        }
        if (this.day != o.day) {
            return this.day > o.day ? 1 : -1;
        }
        return 0; // Bằng nhau
    }

    // Tính số ngày tuyệt đối giữa 2 ngày
    int daysBetween(MyDate o) {
        return Math.abs(this.toAbsoluteDays() - o.toAbsoluteDays());
    }

    // Trả về định dạng chuỗi dd/mm/yyyy (có pad số 0 ở đầu)
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", this.day, this.month, this.year);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        
        int q = sc.nextInt();
        
        for (int i = 0; i < q; i++) {
            int d1 = sc.nextInt();
            int m1 = sc.nextInt();
            int y1 = sc.nextInt();
            int d2 = sc.nextInt();
            int m2 = sc.nextInt();
            int y2 = sc.nextInt();
            String op = sc.next();
            
            MyDate date1 = new MyDate(d1, m1, y1);
            MyDate date2 = new MyDate(d2, m2, y2);
            
            if (op.equals("CMP")) {
                int res = date1.compareTo(date2);
                if (res < 0) System.out.println("LESS");
                else if (res > 0) System.out.println("GREATER");
                else System.out.println("EQUAL");
            } else if (op.equals("DIFF")) {
                System.out.println(date1.daysBetween(date2));
            } else if (op.equals("FMT1")) {
                System.out.println(date1.toString());
            } else if (op.equals("FMT2")) {
                System.out.println(date2.toString());
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
        String s = sc.next();
        
        // Khởi tạo một TreeMap để vừa đếm vừa tự động sắp xếp alphabet
        TreeMap<Character, Integer> tm = new TreeMap<>();
        
        // 1. Duyệt qua từng ký tự của chuỗi s để tiến hành đếm số lần xuất hiện
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            // Nếu ký tự ch đã tồn tại trong TreeMap, tăng số lượng lên 1
            if (tm.containsKey(ch)) {
                tm.put(ch, tm.get(ch) + 1);
            } else {
                // Nếu ký tự ch chưa từng xuất hiện, đặt số lượng ban đầu là 1
                tm.put(ch, 1);
            }
        }
        
        // 2. Duyệt qua TreeMap để in kết quả (TreeMap đã tự sắp xếp sẵn a-z)
        for (Map.Entry<Character, Integer> entry : tm.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        
        sc.close();
    }
}
//cau 3
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. Nhập từ bàn phím một chuỗi bất kỳ (đọc cả dòng bao gồm khoảng trắng)
        if (!sc.hasNextLine()) return;
        String inputString = sc.nextLine();
        
        // 2. Ghi chuỗi này vào file input.txt
        try {
            FileWriter fw = new FileWriter("input.txt");
            fw.write(inputString);
            fw.close(); // Ghi xong phải đóng file để dữ liệu thực sự lưu xuống đĩa
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Khởi tạo các biến đếm
        int letters = 0;
        int digits = 0;
        int others = 0;
        
        // 3. Đọc lại toàn bộ nội dung file input.txt và tiến hành thống kê
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            int c;
            // Đọc từng ký tự một dưới dạng số nguyên (mã ASCII) cho đến khi hết file (-1)
            while ((c = br.read()) != -1) {
                char ch = (char) c; // Ép kiểu số nguyên về ký tự char
                
                // 4. Phân loại ký tự bằng các hàm có sẵn của lớp Character
                if (Character.isLetter(ch)) {
                    letters++;
                } else if (Character.isDigit(ch)) {
                    digits++;
                } else {
                    others++;
                }
            }
            br.close(); // Đọc xong đóng file
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // 5. In kết quả theo đúng định dạng yêu cầu
        System.out.println("Letters: " + letters);
        System.out.println("Digits: " + digits);
        System.out.println("Others: " + others);
        
        sc.close();
    }
}
//cau 4
import java.util.*;

// 1. Định nghĩa lớp SquareFinder kế thừa từ Thread
class SquareFinder extends Thread {
    int[] arr; 
    int from, to; 
    long limit;
    long localBest = -1; // Mặc định ban đầu là -1 nếu không tìm thấy số nào thỏa mãn

    // Constructor để nhận các tham số cần thiết
    SquareFinder(int[] arr, int from, int to, long limit) {
        this.arr = arr;
        this.from = from;
        this.to = to;
        this.limit = limit;
    }

    // Hàm kiểm tra một số có phải là số chính phương hay không
    private boolean isSquare(long x) {
        if (x < 0) return false;
        long r = (long) Math.sqrt(x);
        return (r * r == x) || ((r + 1) * (r + 1) == x);
    }

    // Hàm run() thực thi công việc quét đoạn của luồng
    @Override
    public void run() {
        for (int i = from; i < to; i++) {
            long x = arr[i];
            // Điều kiện: số đó phải <= limit và phải là số chính phương
            if (x <= limit && isSquare(x)) {
                // Nếu tìm thấy số lớn hơn số tốt nhất hiện tại của luồng, ta cập nhật lại
                if (x > localBest) {
                    localBest = x;
                }
            }
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
        long L = sc.nextLong();

        // 2. Tạo mảng quản lý K luồng
        SquareFinder[] threads = new SquareFinder[k];
        int chunkSize = n / k;

        // 3. Phân chia đoạn và kích hoạt các luồng
        for (int i = 0; i < k; i++) {
            int from = i * chunkSize;
            // Luồng cuối gánh nốt phần dư nếu n không chia hết cho k
            int to = (i == k - 1) ? n : (from + chunkSize);

            threads[i] = new SquareFinder(arr, from, to, L);
            threads[i].start(); // Kích hoạt luồng chạy ngầm
        }

        // 4. Chờ tất cả các luồng làm việc xong và thu hoạch kết quả lớn nhất
        long globalBest = -1;
        for (int i = 0; i < k; i++) {
            threads[i].join(); // Đợi luồng i hoàn thành công việc
            
            // Lấy max của tất cả các localBest thu được từ các luồng
            if (threads[i].localBest > globalBest) {
                globalBest = threads[i].localBest;
            }
        }

        // 5. In ra kết quả cuối cùng
        System.out.println(globalBest);

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
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        
        // --- PHẦN CODE SỬ DỤNG INTSTREAM ĐỂ GIẢI QUYẾT ---
        
        // 1. Dùng reduce để tìm giá trị lớn nhất (bắt đầu từ số nhỏ nhất có thể)
        int max = IntStream.of(arr).reduce(Integer.MIN_VALUE, Math::max);
        
        // 2. Dùng reduce để tìm giá trị nhỏ nhất (bắt đầu từ số lớn nhất có thể)
        int min = IntStream.of(arr).reduce(Integer.MAX_VALUE, Math::min);
        
        // 3. Tính tổng MAX + MIN (Dùng kiểu long đề phòng trường hợp max + min vượt quá giới hạn int)
        long sum = (long) max + min;
        
        // 4. In kết quả theo đúng định dạng đầu ra
        System.out.println("MAX " + max);
        System.out.println("MIN " + min);
        System.out.println("SUM " + sum);
        
        sc.close();
    }
}