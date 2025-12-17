import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    //커머스 플랫폼의 상품을 관리하고 사용자 입력을 처리하는 클래스

    //Product를 관리하는 리스트
    private List<Product> products;

    //생성자 - main에서 products를 전달받음
    public CommerceSystem(List<Product> products){
        this.products = products;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);

        //product list를 탐색하며 인덱스별로 번호를 붙여주는 메뉴 형식의 반복문
        printProductList(products);

        int select = scanner.nextInt();

        //입력받은 select에 따라 제품 출력
        if (select == 0) {
            System.out.println();
            System.out.println("커머스 플랫폼을 종료합니다.");
            System.exit(0);
        }else if(select < 1 || select > products.size()) {
            System.out.println("잘못된 번호입니다.");
            System.exit(0);
        }
        else {
            System.out.print("선택한 제품: ");
            products.get(select - 1).printInfo();
        }
    }

    //main 실행 시 바로 출력되는 메뉴
    public static void printProductList(List<Product> products) {
        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        for (int i = 0; i < products.size(); i++) {
            System.out.print((i + 1) + ". ");
            products.get(i).printInfo();
        }
        System.out.println("0. 종료             | 프로그램 종료");
    }
}
