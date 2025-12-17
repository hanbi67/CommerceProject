import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        products.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10));
        products.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 5));
        products.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 6));
        products.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 7));


        //product list를 탐색하며 인덱스별로 번호를 붙여주는 메뉴 형식의 반복문
        printProductList(products);
        Scanner scanner = new Scanner(System.in);
        int select = scanner.nextInt();

        //입력받은 select에 따라 제품 출력
        if (select == 0) {
            System.out.println();
            System.out.println("커머스 플랫폼을 종료합니다.");
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