package store;

import java.util.Scanner;

import store.controller.backend.BackendController;
import store.controller.backend.CategoryController;
import store.controller.backend.CustomerController;
import store.controller.backend.OrderController;
import store.controller.backend.ProductController;
import store.controller.backend.ProductInOrderController;
import store.controller.frontend.FrontendController;

/* Bảng category (Danh mục sản phẩm)                Bảng customer (Khách hàng)
 * ID   | CODE   | Name                              ID   | CODE   | Name              | Phone
 * 1    | 10001  | Tai nghe                          1    | 10001  | Nguyễn Văn Tài     | 0384490724
 * 2    | 10002  | Điện thoại                        2    | 10002  | Lê Văn Đại         | 0384493724
 * 3    | 10003  | Máy tính                          3    | 10003  | Nguyễn Thái Huy    | 0384490725
 * 4    | 10004  | Sạc                               4    | 10004  | Đặng Tiến Việt     | 0384490726
 * 5    | 10005  | Chuột và bàn phím                 5    | 10005  | Trương Tam Phong   | 0384490727
 */

/* Bảng product (Sản phẩm)                         Bảng productInOrder (Sản phẩm trong đơn hàng)           Bảng order (Đơn hàng)
 * ID   | categoryId | CODE   | Name      | Price   ID   | productId  | orderId  | Quantity                 ID   | customerId | CODE
 * 1    | 1          | 10001  |Tai nghe Z | 120000  1    | 1          | 1        | 100                      1    | 1          | 10001
 * 2    | 2          | 10002  | Samsung   | 420000  2    | 2          | 1        | 100                      2    | 2          | 10002
 * 3    | 2          | 10003  | iP 15     | 130000  3    | 3          | 2        | 100                      3    | 3          | 10003
 * 4    | 3          | 10004  | Asus      | 150000  4    | 4          | 2        | 100                      4    | 4          | 10004
 * 5    | 3          | 10005  | Lenovo    | 1200000 5    | 4          | 3        | 100
 *(*Chưa hiểu cột total trong bảng order dùng để làm j)
 */

public class Main {
    public static void main(String[] args) {
        CategoryController.init();
        CustomerController.init();
        ProductController.init();
        ProductInOrderController.init();
        OrderController.init();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n---------------Chuong trinh quan ly ban hang----------------");
            System.out.println("Chon tinh nang nguoi dung");
            System.out.println("1.Cap nhat thong tin he thong");
            System.out.println("2.Quan ly phien giao dich cua khach hang(quan ly gio hang, tao don hang)");
            System.out.println("3.Quan ly don hang va doanh thu");
            System.out.println("0.Dong chuong trinh");
            System.out.print("Nhap lua chon: ");
            int choose = Integer.parseInt(sc.nextLine());

            switch (choose) {
                case 1: {
                    BackendController.menuUpdateSystemInformation();
                    break;
                }
                case 2: {
                    FrontendController.menu();
                    break;
                }
                case 3: {
                    OrderController.menuManageOrdersAndRevenue();
                    break;
                }
                case 0: {
                    return;
                }
                default: {
                    System.out.println("Khong co lua chon nao phu hop.");
                }
            }
        } while (true);
    }
}
